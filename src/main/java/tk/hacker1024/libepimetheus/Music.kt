package tk.hacker1024.libepimetheus

import android.support.v4.media.RatingCompat
import org.json.JSONObject
import tk.hacker1024.libepimetheus.data.Rateable
import tk.hacker1024.libepimetheus.data.Song

/**
 * Sets the [Song] as tired (won't play for a while).
 *
 * @receiver The [Song] object to set as tired.
 * @param [user] The [User] object to authenticate with.
 */
fun Song.addTired(user: User) {
    Networking.makeApiRequest(
        "v1",
        "listener/addTiredSong",
        JSONObject().put("trackToken", trackToken),
        user
    )
}

/**
 * Adds feedback for the [Song].
 *
 * @receiver The [Song] object to add feedback on.
 * @param [thumbsUp] true = rate thumbs up, false = rate thumbs down.
 * @param [user] The [User] object to authenticate with.
 */
fun Song.addFeedback(thumbsUp: Boolean, user: User) {
    settingFeedback = RatingCompat.newThumbRating(thumbsUp)
    try {
        Networking.makeApiRequest(
            "v1",
            "station/addFeedback",
            JSONObject()
                .put("trackToken", trackToken)
                .put("isPositive", thumbsUp),
            user
        ).apply {
            feedbackId = getString("feedbackId")
            rating = RatingCompat.newThumbRating(getBoolean("isPositive"))
            settingFeedback = RatingCompat.newUnratedRating(RatingCompat.RATING_THUMB_UP_DOWN)
        }
    } catch (e: Exception) {
        settingFeedback = RatingCompat.newUnratedRating(RatingCompat.RATING_THUMB_UP_DOWN)
        throw e
    }
}

/**
 * Removes feedback from a [Rateable].
 *
 * @receiver The [Rateable] object to remove feedback on.
 * @param [user] The [User] object to authenticate with.
 */
fun Rateable.deleteFeedback(user: User) {
    if (!rating.isRated) throw IllegalStateException("Song is not rated yet!")
    settingFeedback = rating
    try {
        if (this is Song) {
            if (!isFeedbackIDSet()) {
                feedbackId = getFeedbackId(user)
            }
        }
        Networking.makeApiRequest(
            "v1",
            "station/deleteFeedback",
            JSONObject()
                .put("feedbackId", feedbackId)
                .put("isPositive", rating.isThumbUp),
            user
        ).apply {
            feedbackId = getString("feedbackId")
            rating = RatingCompat.newUnratedRating(RatingCompat.RATING_THUMB_UP_DOWN)
            settingFeedback = rating
        }
    } catch (e: Exception) {
        settingFeedback = RatingCompat.newUnratedRating(RatingCompat.RATING_THUMB_UP_DOWN)
        throw e
    }
}

/**
 * Retrieves the feedback ID for a song.
 *
 * This function gets the feedback ID by rating the song again, and returning the feedback ID from
 * the response. Another way to do it would be to parse the feedback list from the station details,
 * but that requires processing a lot of data. This is the most efficient way, and it's how most
 * third-party Pandora clients do it.
 *
 * @receiver The [Song] object to retrieve the ID for.
 * @param [user] The [User] object to authenticate with.
 * @return The feedback ID.
 */
private fun Song.getFeedbackId(user: User): String {
    if (!rating.isRated) throw IllegalStateException("Song is not rated yet!")

    return Networking.makeApiRequest(
        "v1",
        "station/addFeedback",
        JSONObject()
            .put("trackToken", trackToken)
            .put("isPositive", rating.isThumbUp),
        user
    ).getString("feedbackId")
}