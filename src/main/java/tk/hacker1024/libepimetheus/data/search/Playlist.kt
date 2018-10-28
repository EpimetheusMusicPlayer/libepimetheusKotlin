package tk.hacker1024.libepimetheus.data.search

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
) : PandoraData()