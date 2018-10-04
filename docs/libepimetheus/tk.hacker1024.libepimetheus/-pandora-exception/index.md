[libepimetheus](../../index.md) / [tk.hacker1024.libepimetheus](../index.md) / [PandoraException](./index.md)

# PandoraException

`abstract class PandoraException : `[`Exception`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-exception/index.html)

A generic exception to be used when the reason for the error is unknown.

### Properties

| Name | Summary |
|---|---|
| [code](code.md) | `val code: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The error code. |
| [description](description.md) | `val description: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The error name. |
| [message](message.md) | `open val message: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The error message. |

### Inheritors

| Name | Summary |
|---|---|
| [InvalidAuthTokenException](../-invalid-auth-token-exception.md) | `class InvalidAuthTokenException : `[`PandoraException`](./index.md)<br>Thrown when the authentication token in invalid. |
| [InvalidLoginException](../-invalid-login-exception.md) | `class InvalidLoginException : `[`PandoraException`](./index.md)<br>Thrown when a login is invalid. |
| [InvalidRequestException](../-invalid-request-exception.md) | `class InvalidRequestException : `[`PandoraException`](./index.md)<br>Thrown when an "invalid request" response is received from Pandora. |
| [LocationException](../-location-exception.md) | `class LocationException : `[`PandoraException`](./index.md)<br>Thrown when Pandora can't be accessed because of location restrictions. |
