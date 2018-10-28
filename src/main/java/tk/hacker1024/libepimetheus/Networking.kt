package tk.hacker1024.libepimetheus

import okhttp3.*
import org.json.JSONObject
import java.net.InetAddress

/**
 * This singleton contains networking functions, used to call Pandora API methods.
 * The Pandora REST API is documented [here](https://6xq.net/pandora-apidoc/rest/).
 */
object Networking {
    /**
     * The Pandora REST API needs a cookie (csrftoken) and header to be equal and present in each request.
     * This class stores and retrieves the cookie.
     */
    private class PandoraCookieJar : CookieJar {
        private val cookieStore = HashMap<String, List<Cookie>>()
        private lateinit var csrfToken: String

        override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
            cookieStore[url.host()] = cookies
        }

        override fun loadForRequest(url: HttpUrl) = emptyList<Cookie>()

        internal fun getCSRFToken(usePortaller: Boolean): String {
            if (!::csrfToken.isInitialized) {
                // Get the csrftoken cookie from Pandora
                (if (usePortaller) portallerClient else client).newCall(
                    Request.Builder()
                        .url("https://www.pandora.com/")
                        .head()
                        .build()
                ).execute()
                csrfToken = cookieStore["www.pandora.com"]?.find { it.name() == "csrftoken" }?.value() ?: throw LocationException()
            }
            return csrfToken
        }
    }

    internal val client by lazy {
        OkHttpClient.Builder()
            .cookieJar(PandoraCookieJar())
            .build()!!
    }

    internal val portallerClient by lazy {
        OkHttpClient.Builder()
            .dns {
                listOf(InetAddress.getByAddress(byteArrayOf(107.toByte(), 170.toByte(), 15.toByte(), 247.toByte())))
            }
            .cookieJar(PandoraCookieJar())
            .build()!!
    }

    /**
     * Makes and sends an API request to Pandora.
     * [The REST API is documented here.](https://6xq.net/pandora-apidoc/rest/)
     *
     * @throws PandoraException When an error occurs communicating with Pandora.
     * @throws IllegalArgumentException When the user object is null, and the request isn't an
     *                                  authentication request.
     *
     * @param [version] The version of the API to use.
     * @param [endpoint] The endpoint of the API to call
     * @param [requestJSON] The JSON request body to send.
     * @param [user] The [User] object to authenticate with. Can be null when authenticating.
     * @param [usePortaller] Manual option to use portaller. Overrides the value set in the [User]
     *                       object. If the [User] object is null, it's false by default.
     * @return The JSON response from Pandora.
     */
    fun makeApiRequest(
        version: String,
        endpoint: String,
        requestJSON: JSONObject = JSONObject(),
        user: User? = if (endpoint.contains("auth")) null else throw IllegalArgumentException("The user must not be null, unless you're authenticating!"),
        usePortaller: Boolean = user?.usePortaller ?: false
    ): JSONObject {
        // Build the request URL
        val url = HttpUrl.Builder()
            .scheme("https")
            .host("www.pandora.com")
            .addPathSegment("api")
            .addPathSegment(version)
            .addPathSegments(endpoint)
            .build()

        // Build the request
        val request = Request.Builder()
            .url(url)
            .header("Cookie", "csrftoken=${((if (usePortaller) portallerClient else client).cookieJar() as PandoraCookieJar).getCSRFToken(usePortaller)}")
            .header("X-CsrfToken", ((if (usePortaller) portallerClient else client).cookieJar() as PandoraCookieJar).getCSRFToken(usePortaller))
            .header("X-AuthToken", user?.authToken ?: "")
            .post(RequestBody.create(MediaType.get("application/json; charset=utf-8"), requestJSON.toString()))
            .build()

        // Execute the request, and return the response
        return JSONObject(
            (if (usePortaller) portallerClient else client)
                .newCall(request)
                .execute()
                .body()!!
                .string()
        ).apply {
            if (has("errorCode")) {
                throwError(
                    getInt("errorCode"),
                    if (has("errorString")) getString("errorString") else "No error string",
                    if (has("message")) getString("message") else "No message"
                )
            }
        }
    }

    internal fun makeAutocompleteRequest(
        query: String,
        artSize: Int, user: User,
        usePortaller: Boolean = user.usePortaller
    ) = (if (usePortaller) Networking.portallerClient else Networking.client).newCall(
        Request.Builder()
            .url(
                HttpUrl.Builder()
                    .scheme("https")
                    .host("www.pandora.com")
                    .addPathSegment("autocomplete")
                    .addQueryParameter("q", query)
                    .addQueryParameter("listenercount", "yes")
                    .addQueryParameter("artSize", "Square$artSize")
                    .addQueryParameter("sendquery", "no")
                    .addQueryParameter("seotoken", "yes")
                    .build()
            )
            .header("Cookie", "csrftoken=${((if (usePortaller) portallerClient else client).cookieJar() as PandoraCookieJar).getCSRFToken(usePortaller)}"
            )
            .header("X-CsrfToken", ((if (usePortaller) portallerClient else client).cookieJar() as PandoraCookieJar).getCSRFToken(usePortaller))
            .header("X-AuthToken", user.authToken)
            .build()
    ).execute().body()!!.string()
}