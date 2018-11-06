package tk.hacker1024.libepimetheus.data.search

import androidx.recyclerview.widget.DiffUtil
import org.json.JSONArray
import org.json.JSONObject
import tk.hacker1024.libepimetheus.data.PandoraData
import tk.hacker1024.libepimetheus.data.trimRegex

/**
 * A data class to hold information about an album.
 *
 * @property [name] The name of the album.
 * @property [artistName] The name of the artist.
 * @property [trackCount] The amount of published tracks.
 */
data class Album(
    override val name: String,
    val artistName: String,
    val trackCount: Int,
    internal val pandoraId: String,
    override val artUrls: HashMap<Int, String>
) : PandoraData() {
    /**
     * A [DiffUtil.ItemCallback] implementation.
     */
    open class DiffUtilItemCallback : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album) =
            oldItem.pandoraId == newItem.pandoraId

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.name == newItem.name && oldItem.artistName == newItem.artistName && oldItem.trackCount == newItem.trackCount
        }
    }
}