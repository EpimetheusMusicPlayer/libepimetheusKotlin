package tk.hacker1024.libepimetheus

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

/**
 * This class holds the authentication token and user information.
 * All other functions in this library that call Pandora APIs will need an instance of this class.
 * The Pandora authentication API is documented [here](https://6xq.net/pandora-apidoc/rest/authentication/).
 */
class User : Parcelable {
    val usePortaller: Boolean
    internal val authToken: String
    val email: String
    val username: String

    /**
     * @constructor Signs in to Pandora, and retrieves the authentication token.
     * @property [email] The email address of the user to sign in with.
     * @param [password] The user's password.
     * @property [usePortaller] Whether to use the free Portaller smart DNS service or not, to access
     *                          Pandora outside the US.
     */
    constructor(email: String, password: String, usePortaller: Boolean) {
        if (email.isEmpty() || password.isEmpty()) {
            throw InvalidLoginException("AUTH_INVALID_USERNAME_PASSWORD", "Invalid username and/or password")
        }
        this.usePortaller = usePortaller
        this.email = email
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
            username = getString("webname")
        }
    }

    private constructor(parcel: Parcel) {
        usePortaller = parcel.readByte() != 0.toByte()
        authToken = parcel.readString()!!
        email = parcel.readString()!!
        username = parcel.readString()!!
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