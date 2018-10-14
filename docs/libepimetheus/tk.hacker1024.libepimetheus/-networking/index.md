[libepimetheus](../../index.md) / [tk.hacker1024.libepimetheus](../index.md) / [Networking](./index.md)

# Networking

`object Networking`

This singleton contains networking functions, used to call Pandora API methods.
The Pandora REST API is documented [here](https://6xq.net/pandora-apidoc/rest/).

### Functions

| Name | Summary |
|---|---|
| [makeApiRequest](make-api-request.md) | `fun makeApiRequest(version: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, endpoint: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, requestJSON: `[`JSONObject`](https://developer.android.com/reference/org/json/JSONObject.html)` = JSONObject(), user: `[`User`](../-user/index.md)`? = if (endpoint.contains("auth")) null else throw IllegalArgumentException("The user must not be null, unless you're authenticating!"), usePortaller: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = user?.usePortaller ?: false): `[`JSONObject`](https://developer.android.com/reference/org/json/JSONObject.html)<br>Makes and sends an API request to Pandora. [The REST API is documented here.](https://6xq.net/pandora-apidoc/rest/) |
