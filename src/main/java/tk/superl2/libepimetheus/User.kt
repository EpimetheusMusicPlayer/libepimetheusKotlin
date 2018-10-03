package tk.superl2.libepimetheus

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

/**
 * This class holds the authentication token and user information.
 * All other functions in this library that call Pandora APIs will need an instance of this class.
 * The Pandora authentication API is documented [here](https://6xq.net/pandora-apidoc/rest/authentication/).
 *
 * @constructor Signs in to Pandora, and retrieves the authentication token.
 * @property [email] The email address of the user to sign in with.
 * @param [password] The user's password.
 * @property [usePortaller] Whether to use the free Portaller smart DNS service or not, to access
 *                          Pandora outside the US.
 * @param [authToken] An existing authentication token to use. Don't set this unless you need to.
 *                    This paramater exists mainly for the Parcelable implementation.
 */
class User(val email: String, password: String? = null, var usePortaller: Boolean, authToken: String? = null) : Parcelable {
    internal val authToken: String

    constructor(parcel: Parcel) : this(
        email = parcel.readString()!!,
        authToken = parcel.readString()!!,
        usePortaller = parcel.readByte() != 0.toByte()
    )

    init {
        if (email.isEmpty() || password.isNullOrEmpty() && authToken.isNullOrEmpty()) {
            throw InvalidLoginException("AUTH_INVALID_USERNAME_PASSWORD", "Invalid username and/or password")
        }
        if (authToken == null && password != null) {
            // Make the API request, and save the authentication token
            Networking.makeApiRequest(
                "auth/login",
                JSONObject()
                    .put("existingAuthToken", null)
                    .put("keepLoggedIn", true)
                    .put("username", email)
                    .put("password", password),
                usePortaller = usePortaller
            ).apply {
                this@User.authToken = getString("authToken")
            }
        } else {
            this.authToken = authToken!!
        }
    }

    override fun equals(other: Any?) =
        if (other is User) {
            email == other.email && authToken == other.authToken
        } else super.equals(other)

    override fun hashCode() = 31 * email.hashCode() + authToken.hashCode()

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(email)
        parcel.writeString(authToken)
        parcel.writeByte(if (usePortaller) 1 else 0)
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