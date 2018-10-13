package tk.hacker1024.libepimetheus.data

import android.net.Uri
import android.support.v4.media.RatingCompat
import org.json.JSONArray
import org.json.JSONObject

/**
 * A data class to hold information about a song.
 *
 * @property [name] The name of the song.
 * @property [artist] The song artist.
 * @property [album] The song album.
 * @property [audioUri] The song's audio URI.
 * @property [rating] The song rating.
 * @property [settingFeedback] The feedback that's being sent to Pandora at the time. If it's
 *                             unrated, no feedback is being sent.
 */
class Song(
    override val name: String,
    val artist: String,
    val album: String,
    rating: RatingCompat,
    internal val id: String,
    internal val trackToken: String,
    val audioUri: Uri,
    override val artUrls: HashMap<Int, String>
) : PandoraData() {
    internal lateinit var feedbackId: String
    var rating: RatingCompat = rating; internal set
    var settingFeedback: RatingCompat = RatingCompat.newUnratedRating(RatingCompat.RATING_THUMB_UP_DOWN); internal set

    /**
     * This constructor creates a [Song] object from a JSON station entry from the Pandora API response.
     *
     * @param [songJSON] The JSON object from the Pandora API response.
     */
    private constructor(songJSON: JSONObject) : this(
        name = songJSON.getString("songTitle"),
        artist = songJSON.getString("artistName"),
        album = songJSON.getString("albumTitle"),
        rating = if (songJSON.getInt("rating") == 1) RatingCompat.newThumbRating(true) else RatingCompat.newUnratedRating(
            RatingCompat.RATING_THUMB_UP_DOWN),
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