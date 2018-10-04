[libepimetheus](../../index.md) / [tk.superl2.libepimetheus](../index.md) / [Networking](index.md) / [makeApiRequest](./make-api-request.md)

# makeApiRequest

`fun makeApiRequest(endpoint: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, requestJSON: `[`JSONObject`](https://developer.android.com/reference/org/json/JSONObject.html)` = JSONObject(), user: `[`User`](../-user/index.md)`? = if (endpoint.contains("auth")) null else throw IllegalArgumentException("The user must not be null, unless you're authenticating!"), usePortaller: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = user?.usePortaller ?: false): `[`JSONObject`](https://developer.android.com/reference/org/json/JSONObject.html)

Makes and sends an API request to Pandora.
[The REST API is documented here.](https://6xq.net/pandora-apidoc/rest/)

### Exceptions

`PandoraException` - When an error occurs communicating with Pandora.

### Parameters

`endpoint` - The endpoint of the API to call

`requestJSON` - The JSON request body to send.

`user` - The [User](../-user/index.md) object to authenticate with. Can be null when authenticating.

`usePortaller` - Manual option to use portaller. Overrides the value set in the [User](../-user/index.md)
    object. If the [User](../-user/index.md) object is null, it's false by default.

**Return**
The JSON response from Pandora.

