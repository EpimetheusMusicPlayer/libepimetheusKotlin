package tk.hacker1024.libepimetheus.data.search

import android.os.Parcel
import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import org.json.JSONObject
import tk.hacker1024.libepimetheus.data.PandoraData
import tk.hacker1024.libepimetheus.data.Song
import tk.hacker1024.libepimetheus.data.feedback.FeedbackItem
import java.io.Serializable

/**
 * A data class to hold information about a track.
 *
 * @property [name] The name of the track.
 * @property [artistName] The name of the artist.
 */
data class Track(
    override val name: String,
    val artistName: String,
    internal val pandoraId: String,
    override val artUrls: HashMap<Int, String>
) : PandoraData(), Parcelable {
    /**
     * A [DiffUtil.ItemCallback] implementation.
     */
    open class DiffUtilItemCallback : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(oldItem: Track, newItem: Track) =
            oldItem.pandoraId == newItem.pandoraId

        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem.name == newItem.name && oldItem.artistName == newItem.artistName
        }
    }

    @Suppress("UNCHECKED_CAST")
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readSerializable() as HashMap<Int, String>
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(artistName)
        parcel.writeString(pandoraId)
        parcel.writeSerializable(artUrls)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Track> {
        override fun createFromParcel(parcel: Parcel): Track {
            return Track(parcel)
        }

        override fun newArray(size: Int): Array<Track?> {
            return arrayOfNulls(size)
        }
    }
}

fun FeedbackItem.toTrack() = Track(name, artist, pandoraId, artUrls)