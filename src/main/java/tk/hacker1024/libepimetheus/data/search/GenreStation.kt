package tk.hacker1024.libepimetheus.data.search

import androidx.recyclerview.widget.DiffUtil
import org.json.JSONArray
import org.json.JSONObject
import tk.hacker1024.libepimetheus.data.PandoraData
import tk.hacker1024.libepimetheus.data.trimRegex

/**
 * A data class to hold information about a genre station.
 *
 * @property [name] The name of the genre station.
 * @property [description] The genre station's description.
 */
data class GenreStation(
    override val name: String,
    internal val token: String? = null,
    internal val musicId: String? = null,
    override val pandoraId: String,
    val description: String? = null,
    override val listenerCount: Int? = null,
    override val artUrls: HashMap<Int, String>
) : Listenable() {
    private constructor(genreStationJSON: JSONObject) : this(
        name = genreStationJSON.getString("name").trim().replace(trimRegex, " "),
        token = genreStationJSON.getString("token"),
        musicId = genreStationJSON.getString("musicId"),
        pandoraId = genreStationJSON.getString("pandoraId"),
        description = if (genreStationJSON.has("description")) genreStationJSON.getString("description") else null,
        listenerCount = genreStationJSON.getInt("listenerCount"),
        artUrls = artJSONtoMap(genreStationJSON.getJSONArray("art"))
    )

    /**
     * A [DiffUtil.ItemCallback] implementation.
     */
    open class DiffUtilItemCallback : DiffUtil.ItemCallback<GenreStation>() {
        override fun areItemsTheSame(oldItem: GenreStation, newItem: GenreStation) =
            oldItem.pandoraId == newItem.pandoraId && oldItem.token == newItem.token && oldItem.musicId == newItem.musicId

        override fun areContentsTheSame(oldItem: GenreStation, newItem: GenreStation): Boolean {
            return oldItem.name == newItem.name && oldItem.listenerCount == newItem.listenerCount && oldItem.description == newItem.description
        }
    }

    internal companion object {
        /**
         * This function creates a list of [GenreStation] objects from a JSON array from the Pandora API response.
         *
         * @param [jsonArray] The JSON array from the Pandora API response.
         * @return A list of [GenreStation] objects.
         */
        internal fun createListFromJSONArray(jsonArray: JSONArray): List<GenreStation> {
            return List(jsonArray.length()) {
                GenreStation(jsonArray.getJSONObject(it))
            }
        }
    }
}