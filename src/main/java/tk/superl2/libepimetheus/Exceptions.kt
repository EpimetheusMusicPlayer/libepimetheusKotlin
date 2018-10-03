package tk.superl2.libepimetheus

/**
 * This file contains various exceptions to represent errors received from Pandora.
 * A somewhat incomplete list of error codes can be found [here](https://6xq.net/pandora-apidoc/rest/errorcodes/).
 */

/**
 * A generic exception to be used when the reason for the error is unknown.
 *
 * @property [code] The error code.
 * @property [description] The error name.
 * @property [message] The error message.
 */
abstract class PandoraException internal constructor(val code: Int, val description: String, override val message: String) : Exception("Pandora error $code: $description. $message.")

/**
 * Thrown when an "invalid request" response is received from Pandora.
 */
class InvalidRequestException internal constructor(code: Int, description: String, message: String) : PandoraException(code, description, message)

/**
 * Thrown when the authentication token in invalid.
 */
class InvalidAuthTokenException internal constructor(description: String, message: String) : PandoraException(1001, description, message)

/**
 * Thrown when Pandora can't be accessed because of location restrictions.
 */
class LocationException internal constructor() : PandoraException(0, "", "")

/**
 * Thrown when a login is invalid.
 */
class InvalidLoginException internal constructor(description: String, message: String) : PandoraException(0, description, message)

/**
 * Throws the error relating to the given error code.
 *
 * @param [code] The error code.
 * @param [description] The error name.
 * @param [message] The error message.
 */
internal fun throwError(code: Int, description: String, message: String): Nothing {
    when (code) {
        0 -> when (description) {
            "AUTH_INVALID_USERNAME_PASSWORD" -> throw InvalidLoginException(description, message)
            else -> throw InvalidRequestException(code, description, message)
        }
        1001 -> throw InvalidAuthTokenException(description, message)
        else -> throw InvalidRequestException(code, description, message)
    }
}