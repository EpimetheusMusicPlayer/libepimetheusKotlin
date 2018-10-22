package tk.hacker1024.libepimetheus.data.feedback

import android.support.v4.media.RatingCompat
import androidx.recyclerview.widget.DiffUtil
import org.json.JSONArray
import org.json.JSONObject
import tk.hacker1024.libepimetheus.data.PandoraData
import tk.hacker1024.libepimetheus.data.Rateable
import java.util.*

/**
 * An [ArrayList] with information about the feedback items in it.
 *
 * @property [isPositive] true = thumbs up, false = thumbs down.
 * @property [totalSize] The total amount of feedback in the station.
 */
class FeedbackList(
    val isPositive: Boolean,
    val totalSize: Int,
    internal val stationId: String
) : ArrayList<FeedbackItem>() {
    constructor(
        isPositive: Boolean,
        totalSize: Int,
        stationId: String,
        size: Int,
        init: (Int) -> FeedbackItem
    ) : this(
        isPositive,
        totalSize,
        stationId
    ) {
        repeat(size) { index -> add(init(index)) }
    }

    override fun toString() =
        "isPositive=$isPositive, totalSize=$totalSize, stationId=$stationId, feedback=${super.toString()}"
}

/**
 * A data class to hold information about a feedback item.
 *
 * @property [rating] The feedback item rating.
 * @property [name] The name of the song.
 * @property [artist] The name of the artist.
 * @property [album] The name of the album.
 * @property [sampleUrl] The sample audio URL ( ~ 30s of the song) from iTunes.
 */
data class FeedbackItem(
    override var rating: RatingCompat,
    override val name: String,
    val artist: String,
    val album: String,
    val sampleUrl: String?, // Ew an Apple service
    override var feedbackId: String,
    override val artUrls: HashMap<Int, String>
): PandoraData(), Rateable {
    override var settingFeedback: RatingCompat = RatingCompat.newUnratedRating(RatingCompat.RATING_THUMB_UP_DOWN)

    internal constructor(feedbackItemJSON: JSONObject) : this(
            rating = RatingCompat.newThumbRating(feedbackItemJSON.getBoolean("isPositive")),
              name = feedbackItemJSON.getString("songTitle"),
            artist = feedbackItemJSON.getString("artistName"),
             album = feedbackItemJSON.getString("albumTitle"),
         sampleUrl = if (feedbackItemJSON.has("sampleUrl")) feedbackItemJSON.getString("sampleUrl") else null,
        feedbackId = feedbackItemJSON.getString("feedbackId"),
           artUrls = artJSONtoMap(feedbackItemJSON.getJSONArray("albumArt"))
    )

    internal companion object {
        /**
         * This function creates a list of [FeedbackItem] objects from a JSON array from the Pandora API response.
         *
         * @param [isPositive] true = thumbs up, false = thumbs down.
         * @param [totalSize] The total amount of feedback in the station.
         * @param [jsonArray] The JSON array from the Pandora API response.
         * @return A list of [FeedbackItem] objects.
         */
        internal fun createListFromJSONArray(isPositive: Boolean, totalSize: Int, jsonArray: JSONArray, stationId: String): FeedbackList {
            return FeedbackList(isPositive, totalSize, stationId, jsonArray.length()) {
                FeedbackItem(jsonArray.getJSONObject(it))
            }
        }
    }

    /**
     * A [DiffUtil.ItemCallback] implementation.
     */
    open class DiffUtilItemCallback : DiffUtil.ItemCallback<FeedbackItem>() {
        override fun areItemsTheSame(oldItem: FeedbackItem, newItem: FeedbackItem) =
            oldItem.feedbackId == newItem.feedbackId

        override fun areContentsTheSame(oldItem: FeedbackItem, newItem: FeedbackItem) =
            oldItem.name == newItem.name &&
            oldItem.artist == newItem.artist &&
            oldItem.album == newItem.album &&
            oldItem.rating.isRated == newItem.rating.isRated &&
            oldItem.rating.isThumbUp == newItem.rating.isThumbUp
    }
}