package tk.hacker1024.libepimetheus.data

import android.net.Uri
import android.support.v4.media.RatingCompat
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import tk.hacker1024.libepimetheus.data.Song.TrackType.ARTIST_MESSAGE
import tk.hacker1024.libepimetheus.data.Song.TrackType.Companion.toTrackType
import tk.hacker1024.libepimetheus.data.Song.TrackType.TRACK

/**
 * A data class to hold information about a song.
 *
 * @property [trackType] The track type.
 * @property [name] The name of the song.
 * @property [artist] The song artist.
 * @property [album] The song album.
 * @property [audioUri] The song's audio URI.
 * @property [rating] The song rating.
 * @property [settingFeedback] The feedback that's being sent to Pandora at the time. If it's
 *                             unrated, no feedback is being sent.
 */
data class Song(
    val trackType: TrackType,
    override val name: String,
    val artist: String,
    val album: String,
    override var rating: RatingCompat,
    internal val id: String,
    internal val trackToken: String,
    val audioUri: Uri,
    override val artUrls: HashMap<Int, String>
) : Rateable() {
    override lateinit var feedbackId: String
    override var settingFeedback: RatingCompat = RatingCompat.newUnratedRating(RatingCompat.RATING_THUMB_UP_DOWN)

    /**
     * This constructor creates a [Song] object from a JSON station entry from the Pandora API response.
     *
     * @param [songJSON] The JSON object from the Pandora API response.
     */
    private constructor(songJSON: JSONObject) : this(
        trackType = songJSON.getString("trackType").toTrackType(),
        name = songJSON.getString("songTitle"),
        artist = songJSON.getString("artistName"),
        album = songJSON.getString("albumTitle"),
        rating = if (songJSON.getInt("rating") == 1) RatingCompat.newThumbRating(true) else RatingCompat.newUnratedRating(RatingCompat.RATING_THUMB_UP_DOWN),
        id = songJSON.getString("musicId"),
        trackToken = songJSON.getString("trackToken"),
        audioUri = Uri.parse(songJSON.getString("audioURL")),
        artUrls = artJSONtoMap(songJSON.getJSONArray("albumArt"))
    )

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

    /**
     * Enum class to represent song track type.
     *
     * @property [TRACK] A regular track.
     * @property [ARTIST_MESSAGE] An artist message.
     */
    enum class TrackType {
        TRACK,
        ARTIST_MESSAGE;

        companion object {
            internal fun String.toTrackType(): TrackType {
                return when (this) {
                    "Track" -> TRACK
                    "ArtistMessage" -> ARTIST_MESSAGE
                    else -> {
                        Log.w("libepimetheus", "Unknown track type found!")
                        TRACK
                    }
                }
            }
        }
    }
}