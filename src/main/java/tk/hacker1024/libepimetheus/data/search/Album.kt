package tk.hacker1024.libepimetheus.data.search

import org.json.JSONArray
import org.json.JSONObject
import tk.hacker1024.libepimetheus.data.PandoraData
import tk.hacker1024.libepimetheus.data.trimRegex

/**
 * A data class to hold information about an album.
 *
 * @property [name] The name of the album.
 * @property [artistName] The name of the artist.
 */
data class Album(
    override val name: String,
    val artistName: String,
    internal val pandoraId: String,
    override val artUrls: HashMap<Int, String>
) : PandoraData()