package tk.hacker1024.libepimetheus.data

import org.json.JSONArray
import org.json.JSONObject

/**
 * A data class to hold information about a genre station.
 *
 * @property [name] The name of the genre station.
 * @property [description] The genre station's description.
 */
data class GenreStation(
    override val name: String,
    internal val token: String,
    internal val musicId: String,
    internal val pandoraId: String,
    val description: String,
    override val artUrls: HashMap<Int, String>
) : PandoraData() {
    private constructor(genreStationJSON: JSONObject) : this(
        name = genreStationJSON.getString("name"),
        token = genreStationJSON.getString("token"),
        musicId = genreStationJSON.getString("musicId"),
        pandoraId = genreStationJSON.getString("pandoraId"),
        description = genreStationJSON.getString("description"),
        artUrls = HashMap<Int, String>().also { artMap ->
            if (genreStationJSON.has("art")) {
                genreStationJSON.getJSONArray("art").also { artJSONArray ->
                    for (i in 0 until artJSONArray.length()) {
                        artJSONArray.getJSONObject(i).apply {
                            artMap[getInt("size")] = getString("url")
                        }
                    }
                }
            }
        }
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