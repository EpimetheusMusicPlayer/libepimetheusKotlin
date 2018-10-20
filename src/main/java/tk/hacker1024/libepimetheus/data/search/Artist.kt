package tk.hacker1024.libepimetheus.data.search

import org.json.JSONArray
import org.json.JSONObject
import tk.hacker1024.libepimetheus.data.trimRegex

/**
 * A data class to hold information about an artist.
 *
 * @property [name] The name of the artist.
 * @property [detailUrl] The details URL of the artist.
 */
data class Artist(
    override val name: String,
    internal val musicId: String? = null,
    override val pandoraId: String,
    val detailUrl: String? = null,
    override val listenerCount: Int? = null,
    override val artUrls: HashMap<Int, String>
) : Listenable("https://www.pandora.com/web-version/1.34.2/images/artist_500.png") {
    private constructor(genreStationJSON: JSONObject) : this(
        name = genreStationJSON.getString("name").trim().replace(trimRegex, " "),
        musicId = genreStationJSON.getString("musicId"),
        pandoraId = genreStationJSON.getString("pandoraId"),
        detailUrl = genreStationJSON.getString("detailUrl"),
        listenerCount = if (genreStationJSON.has("listenerCount")) genreStationJSON.getInt("listenerCount") else null,
        artUrls = artJSONtoMap(genreStationJSON.getJSONArray("art"))
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