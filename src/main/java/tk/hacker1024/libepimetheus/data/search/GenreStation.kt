package tk.hacker1024.libepimetheus.data.search

import org.json.JSONArray
import org.json.JSONObject
import tk.hacker1024.libepimetheus.data.PandoraData

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
    internal val pandoraId: String,
    val description: String? = null,
    override val listenerCount: Int? = null,
    override val artUrls: HashMap<Int, String>
) : Listenable() {
    private constructor(genreStationJSON: JSONObject) : this(
        name = genreStationJSON.getString("name"),
        token = genreStationJSON.getString("token"),
        musicId = genreStationJSON.getString("musicId"),
        pandoraId = genreStationJSON.getString("pandoraId"),
        description = if (genreStationJSON.has("description")) genreStationJSON.getString("description") else null,
        listenerCount = genreStationJSON.getInt("listenerCount"),
        artUrls = artJSONtoMap(genreStationJSON.getJSONArray("art"))
    )

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