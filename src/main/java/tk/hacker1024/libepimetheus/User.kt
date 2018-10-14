package tk.hacker1024.libepimetheus

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

/**
 * This class holds the authentication token and user information.
 * All other functions in this library that call Pandora APIs will need an instance of this class.
 * The Pandora authentication API is documented [here](https://6xq.net/pandora-apidoc/rest/authentication/).
 *
 * @property [usePortaller] Whether to use the free Portaller smart DNS service or not, to access
 *                          Pandora outside the US.
 * @property [email] The email address of the user.
 * @property [username] The user's username
 * @property [profilePicUri] The [Uri] of the user's profile picture.
 */
class User : Parcelable {
    val usePortaller: Boolean
    internal val authToken: String
    val email: String
    val username: String
    private val webname: String
    val profilePicUri: Uri

    /**
     * @constructor Signs in to Pandora, and retrieves the authentication token.
     * @param [email] The email address of the user to sign in with.
     * @param [password] The user's password.
     * @param [usePortaller] Whether to use the free Portaller smart DNS service or not, to access
     *                       Pandora outside the US.
     */
    constructor(email: String, password: String, usePortaller: Boolean) {
        if (email.isEmpty() || password.isEmpty()) {
            throw InvalidLoginException("AUTH_INVALID_USERNAME_PASSWORD", "Invalid username and/or password")
        }
        this.usePortaller = usePortaller
        this.email = email
        // Make the API request, and save the authentication token
        Networking.makeApiRequest(
            "v1",
            "auth/login",
            JSONObject()
                .put("existingAuthToken", null)
                .put("keepLoggedIn", true)
                .put("username", email)
                .put("password", password),
            usePortaller = usePortaller
        ).apply {
            this@User.authToken = getString("authToken")
            webname = getString("webname")
            username = retrieveUsername()
            profilePicUri = getProfilePicUri(this)
        }
    }

    private fun retrieveUsername(): String {
        Networking.makeApiRequest(
            "v1",
            "listener/getProfile",
            JSONObject().put("webname", webname),
            this
        ).apply {
            return if (has("fullName")) {
                getString("fullName")
            } else {
                webname
            }
        }
    }

    /** Pandora profile pictures are either from the user's facebook account, or are a generic letter
     *  icon.
     *  The facebook API for getting profile pictures is documented [here](https://developers.facebook.com/docs/graph-api/reference/v2.2/user/picture).
     *
     *  @param [authenticationResponse] The JSON response from the authentication request.
     *  @return The user's profile picture URI.
     */
    private fun getProfilePicUri(authenticationResponse: JSONObject): Uri {
        return Uri.parse(
            if (authenticationResponse.has("facebookData")) {
                JSONObject(
                    OkHttpClient().newCall(
                        Request.Builder()
                            .url(
                                HttpUrl.Builder()
                                    .scheme("https")
                                    .host("graph.facebook.com")
                                    .addPathSegment(
                                        authenticationResponse
                                            .getJSONObject("facebookData")
                                            .getString("facebookId")
                                    )
                                    .addPathSegment("picture")
                                    .addQueryParameter("type", "large")
                                    .addQueryParameter("redirect", "false")
                                    .build()
                            )
                            .build()
                    ).execute().body()!!.string()
                ).getJSONObject("data").getString("url")
            } else {
                "https://www.pandora.com/static/user/default_images/user_default_${username[0].toLowerCase()}_500x500.png"
            }
        )
    }

    private constructor(parcel: Parcel) {
        usePortaller = parcel.readByte() != 0.toByte()
        authToken = parcel.readString()!!
        email = parcel.readString()!!
        username = parcel.readString()!!
        webname = parcel.readString()!!
        profilePicUri = Uri.parse(parcel.readString()!!)
    }

    override fun equals(other: Any?) =
        if (other is User) {
            email == other.email && authToken == other.authToken
        } else super.equals(other)

    override fun hashCode() = 31 * email.hashCode() + authToken.hashCode()

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (usePortaller) 1 else 0)
        parcel.writeString(authToken)
        parcel.writeString(email)
        parcel.writeString(username)
        parcel.writeString(webname)
        parcel.writeString(profilePicUri.toString())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}