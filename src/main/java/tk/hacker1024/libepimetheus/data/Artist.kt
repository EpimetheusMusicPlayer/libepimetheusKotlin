package tk.hacker1024.libepimetheus.data

import org.json.JSONArray
import org.json.JSONObject

/**
 * A data class to hold information about an artist.
 *
 * @property [name] The name of the artist.
 * @property [detailUrl] The details URL of the artist.
 */
data class Artist(
    override val name: String,
    internal val musicId: String,
    internal val pandoraId: String,
    val detailUrl: String,
    override val artUrls: HashMap<Int, String>
) : PandoraData("https://www.pandora.com/web-version/1.34.2/images/artist_500.png") {
    private constructor(genreStationJSON: JSONObject) : this(
        name = genreStationJSON.getString("name"),
        musicId = genreStationJSON.getString("musicId"),
        pandoraId = genreStationJSON.getString("pandoraId"),
        detailUrl = genreStationJSON.getString("detailUrl"),
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
         * This function creates a list of [Artist] objects from a JSON array from the Pandora API response.
         *
         * @param [jsonArray] The JSON array from the Pandora API response.
         * @return A list of [Artist] objects.
         */
        internal fun createListFromJSONArray(jsonArray: JSONArray): List<Artist> {
            return List(jsonArray.length()) {
                Artist(jsonArray.getJSONObject(it))
            }
        }
    }
}