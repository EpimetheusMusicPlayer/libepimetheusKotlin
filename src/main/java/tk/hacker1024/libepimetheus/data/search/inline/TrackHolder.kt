package tk.hacker1024.libepimetheus.data.search.inline

import tk.hacker1024.libepimetheus.data.Song
import tk.hacker1024.libepimetheus.data.search.Track
import java.io.Serializable

/**
 * This inline class exists to serialize a track object into an array.
 * This is needed to put a track object in a media session queue description.
 *
 * A bug in the Android bluetooth package will try to unparcel/unserialize all objects in the
 * description, and will crash if it's not a built-in Java class.
 *
 * @property [array] The serializable array containing track data. Use this in queue item description bundles. DO NOT MODIFY.
 * @property [track] The [Track] object being held.
 */
inline class TrackHolder(val array: Array<Serializable>) : Serializable {
    val track
        get() =
            try {
                @Suppress("UNCHECKED_CAST")
                Track(
                    array[0] as String,
                    array[1] as String,
                    array[2] as String,
                    array[3] as HashMap<Int, String>
                )
            } catch (e: ClassCastException) {
                throw IllegalArgumentException("This TrackHolder contains an invalid track array!")
            }
}

fun Song.toTrackHolder() = TrackHolder(arrayOf(name, artist, id, artUrls))
fun Track.toTrackHolder() = TrackHolder(arrayOf(name, artistName, pandoraId, artUrls))