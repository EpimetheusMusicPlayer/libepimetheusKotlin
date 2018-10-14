package tk.hacker1024.libepimetheus.data

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONArray
import org.json.JSONObject

/**
 * A data class to hold information about a station.
 *
 * @constructor Creates the object with the given name, id, and art URLs.
 * @property [name] The name of the station.
 * @property [isShuffle] Boolean indicating if the station is the Shuffle/QuickMix station.
 * @property [isThumbprint] Boolean indicating if the station is the thumbprint station.
 * @property [canDelete] Boolean indicating if the station can be deleted.
 * @property [canRename] Boolean indication if the station can be renamed.
 * @property [artUrls] A [HashMap] containing art URLs values with their sizes as keys.
 */
data class Station(
    override val name: String,
    internal val id: String,
    val isShuffle: Boolean,
    val isThumbprint: Boolean,
    val canDelete: Boolean,
    val canRename: Boolean,
    override val artUrls: HashMap<Int, String>
) : PandoraData(), Parcelable {
    /**
     * This constructor creates a [Station] object from a JSON station entry from the Pandora API response.
     *
     * @param [stationJSON] The JSON object from the Pandora API response.
     */
   internal constructor(stationJSON: JSONObject) : this(
        name = stationJSON.getString("name"),
        id = stationJSON.getString("stationId"),
        isShuffle = stationJSON.getBoolean("isShuffle"),
        isThumbprint = stationJSON.getBoolean("isThumbprint"),
        canDelete = stationJSON.getBoolean("allowDelete"),
        canRename = stationJSON.getBoolean("allowRename"),
        artUrls = if (stationJSON.has("art")) artJSONtoMap(stationJSON.getJSONArray("art")) else HashMap()
    )

    companion object CREATOR : Parcelable.Creator<Station> {
        /**
         * This function creates a list of [Station] objects from a JSON array from the Pandora API response.
         *
         * @param [jsonArray] The JSON array from the Pandora API response.
         * @return A list of [Station] objects.
         */
        internal fun createListFromJSONArray(jsonArray: JSONArray): MutableList<Station> {
            return MutableList(jsonArray.length()) {
                Station(jsonArray.getJSONObject(it))
            }
        }

        override fun createFromParcel(parcel: Parcel): Station {
            return Station(parcel)
        }

        override fun newArray(size: Int): Array<Station?> {
            return arrayOfNulls(size)
        }
    }

    @Suppress("UNCHECKED_CAST")
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readSerializable() as HashMap<Int, String>
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(id)
        parcel.writeByte(if (isShuffle) 1 else 0)
        parcel.writeByte(if (isThumbprint) 1 else 0)
        parcel.writeByte(if (canDelete) 1 else 0)
        parcel.writeByte(if (canRename) 1 else 0)
        parcel.writeSerializable(artUrls)
    }

    override fun describeContents(): Int {
        return 0
    }
}