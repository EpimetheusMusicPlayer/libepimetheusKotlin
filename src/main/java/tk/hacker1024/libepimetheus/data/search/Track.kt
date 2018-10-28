package tk.hacker1024.libepimetheus.data.search

import tk.hacker1024.libepimetheus.data.PandoraData

/**
 * A data class to hold information about a track.
 *
 * @property [name] The name of the track.
 * @property [artist] The name of the artist.
 */
data class Track(
    override val name: String,
    val artist: String,
    internal val pandoraId: String,
    override val artUrls: HashMap<Int, String>
) : PandoraData()