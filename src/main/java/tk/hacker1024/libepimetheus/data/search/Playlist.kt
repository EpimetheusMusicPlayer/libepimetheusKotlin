package tk.hacker1024.libepimetheus.data.search

import android.net.Uri
import androidx.recyclerview.widget.DiffUtil
import tk.hacker1024.libepimetheus.data.PandoraData

/**
 * A data class to hold information about a playlist.
 *
 * @property [name] The name of the playlist.
 * @property [totalTracks] The total amount of tracks in the playlist.
 */
data class Playlist(
    override val name: String,
    val totalTracks: Int,
    internal val pandoraId: String,
    override val artUrls: HashMap<Int, String>
) : PandoraData() {
    /**
     * A [DiffUtil.ItemCallback] implementation.
     */
    open class DiffUtilItemCallback : DiffUtil.ItemCallback<Playlist>() {
        override fun areItemsTheSame(oldItem: Playlist, newItem: Playlist) =
            oldItem.pandoraId == newItem.pandoraId

        override fun areContentsTheSame(oldItem: Playlist, newItem: Playlist): Boolean {
            return oldItem.name == newItem.name && oldItem.totalTracks == newItem.totalTracks
        }
    }

    companion object {
        fun getGridUrl(thorLayers: String, artSize: Int) =
            "https://dyn-images.p-cdn.com/?l=${Uri.encode(thorLayers)}&w=$artSize&h=$artSize"
    }
}