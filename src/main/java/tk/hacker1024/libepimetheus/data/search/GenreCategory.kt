package tk.hacker1024.libepimetheus.data.search

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONArray
import org.json.JSONObject
import tk.hacker1024.libepimetheus.data.PandoraData
import tk.hacker1024.libepimetheus.data.trimRegex

/**
 * A data class to hold information about a genre category.
 *
 * @property [name] The name of the category.
 */
data class GenreCategory(
    override val name: String,
    internal val token: String,
    override val artUrls: HashMap<Int, String>
) : PandoraData(), Parcelable {
    private constructor(genreCategoryJSON: JSONObject) : this(
        name = genreCategoryJSON.getString("name").trim().replace(trimRegex, " "),
        token = genreCategoryJSON.getString("token"),
        artUrls = artJSONtoMap(genreCategoryJSON.getJSONArray("art"))
    )

    @Suppress("UNCHECKED_CAST")
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readSerializable() as HashMap<Int, String>
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(token)
        parcel.writeSerializable(artUrls)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GenreCategory> {
        /**
         * This function creates a list of [GenreCategory] objects from a JSON array from the Pandora API response.
         *
         * @param [jsonArray] The JSON array from the Pandora API response.
         * @return A list of [GenreCategory] objects.
         */
        internal fun createListFromJSONArray(jsonArray: JSONArray): List<GenreCategory> {
            return List(jsonArray.length()) {
                GenreCategory(jsonArray.getJSONObject(it))
            }
        }

        override fun createFromParcel(parcel: Parcel): GenreCategory {
            return GenreCategory(parcel)
        }

        override fun newArray(size: Int): Array<GenreCategory?> {
            return arrayOfNulls(size)
        }
    }
}