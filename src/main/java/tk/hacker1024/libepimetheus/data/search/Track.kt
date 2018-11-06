package tk.hacker1024.libepimetheus.data.search

import androidx.recyclerview.widget.DiffUtil
import tk.hacker1024.libepimetheus.data.PandoraData

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
) : PandoraData() {
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
}