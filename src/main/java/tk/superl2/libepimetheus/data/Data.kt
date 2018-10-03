package tk.superl2.libepimetheus.data

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import android.support.v4.media.RatingCompat
import org.json.JSONArray
import org.json.JSONObject

/**
 * A data class to hold information about a station.
 *
 * @constructor Creates the object with the given name, id, and art URLs.
 * @property [name] The name of the station.
 * @property [id] The station ID.
 * @property [isShuffle] Boolean indicating if the station is the Shuffle/QuickMix station.
 * @property [isThumbprint] Boolean indicating if the station is the thumbprint station.
 * @property [artUrls] A [HashMap] containing art URLs values with their sizes as keys.
 */
data class Station(
    val name: String,
    internal val id: String,
    val isShuffle: Boolean,
    val isThumbprint: Boolean,
    private val artUrls: HashMap<Int, String>
) : Parcelable {
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
        artUrls = HashMap<Int, String>().also { artMap ->
            if (stationJSON.has("art")) {
                stationJSON.getJSONArray("art").also { artJSONArray ->
                    for (i in 0 until artJSONArray.length()) {
                        artJSONArray.getJSONObject(i).apply {
                            artMap[getInt("size")] = getString("url")
                        }
                    }
                }
            }
        }
    )

    /**
     * This function will return the station's art URL for the given size. If the given size
     * doesn't exist, it will return the URL for the nearest larger size.
     *
     * @param [preferredSize] The preferred size of the art.
     * @return The art URL for the nearest larger or equal size of the size specified.
     */
    fun getArtUrl(preferredSize: Int): String? {
        if (artUrls.isNotEmpty()) {
            artUrls.keys.sorted().apply {
                forEach { size ->
                    if (preferredSize <= size) return artUrls[size]!!
                }
                return artUrls[this.last()]!!
            }
        } else {
            return null
        }
    }

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
        parcel.readSerializable() as HashMap<Int, String>
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(id)
        parcel.writeByte(if (isShuffle) 1 else 0)
        parcel.writeByte(if (isThumbprint) 1 else 0)
        parcel.writeSerializable(artUrls)
    }

    override fun describeContents(): Int {
        return 0
    }
}

/**
 * A data class to hold information about a song.
 *
 * @constructor Creates the object with the given name, id, and art URLs.
 * @property [name] The name of the song.
 * @property [artist] The song artist.
 * @property [album] The song album.
 * @param [rating] The song rating.
 * @property [id] The song ID.
 * @property [trackToken] The track token, used for adding feedback.
 * @property [artUrls] A [HashMap] containing art URLs values with their sizes as keys.
 */
class Song(
    val name: String,
    val artist: String,
    val album: String,
    rating: RatingCompat,
    internal val id: String,
    internal val trackToken: String,
    val audioUri: Uri,
    private val artUrls: HashMap<Int, String>
) {
    internal lateinit var feedbackId: String

    /**
     * @property [rating] The song rating.
     */
    var rating: RatingCompat = rating; internal set

    /**
     * @property [settingFeedback] The feedback that's being sent to Pandora at the time. If it's
     *                             unrated, no feedback is being sent.
     */
    var settingFeedback: RatingCompat = RatingCompat.newUnratedRating(RatingCompat.RATING_THUMB_UP_DOWN); internal set

    /**
     * This constructor creates a [Song] object from a JSON station entry from the Pandora API response.
     *
     * @param [songJSON] The JSON object from the Pandora API response.
     */
    internal constructor(songJSON: JSONObject) : this(
        name = songJSON.getString("songTitle"),
        artist = songJSON.getString("artistName"),
        album = songJSON.getString("albumTitle"),
        rating = if (songJSON.getInt("rating") == 1) RatingCompat.newThumbRating(true) else RatingCompat.newUnratedRating(RatingCompat.RATING_THUMB_UP_DOWN),
        id = songJSON.getString("musicId"),
        trackToken = songJSON.getString("trackToken"),
        audioUri = Uri.parse(songJSON.getString("audioURL")),
        artUrls = HashMap<Int, String>().also { artMap ->
            if (songJSON.has("albumArt")) {
                songJSON.getJSONArray("albumArt").also { artJSONArray ->
                    for (i in 0 until artJSONArray.length()) {
                        artJSONArray.getJSONObject(i).apply {
                            artMap[getInt("size")] = getString("url")
                        }
                    }
                }
            }
        }
    )

    /**
     * This function will return the song's art URL for the given size. If the given size
     * doesn't exist, it will return the URL for the nearest larger size.
     *
     * @param [preferredSize] The preferred size of the art.
     * @return The art URL for the nearest larger or equal size of the size specified.
     */
    fun getArtUrl(preferredSize: Int): String {
        if (artUrls.isNotEmpty()) {
            artUrls.keys.sorted().apply {
                forEach { size ->
                    if (preferredSize <= size) return artUrls[size]!!
                }
                return artUrls[this.last()]!!
            }
        } else return "https://www.pandora.com/web-version/1.25.1/images/album_500.png"
    }

    internal fun isFeedbackIDSet() = ::feedbackId.isInitialized

    internal companion object {
        /**
         * This function creates a list of [Song] objects from a JSON array from the Pandora API response.
         *
         * @param [jsonArray] The JSON array from the Pandora API response.
         * @return A list of [Song] objects.
         */
        internal fun createListFromJSONArray(jsonArray: JSONArray): List<Song> {
            return List(jsonArray.length()) {
                Song(jsonArray.getJSONObject(it))
            }
        }
    }
}
