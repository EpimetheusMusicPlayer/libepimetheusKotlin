package tk.hacker1024.libepimetheus.data.search

import org.json.JSONArray
import org.json.JSONObject
import tk.hacker1024.libepimetheus.data.PandoraData

data class Track(
    override val name: String,
    val artist: String,
    internal val musicId: String? = null,
    internal val pandoraId: String,
    override val artUrls: HashMap<Int, String>
) : PandoraData() {
    private constructor(trackJSON: JSONObject) : this(
        name = trackJSON.getString("name"),
        artist = trackJSON.getString("artistName"),
        musicId = trackJSON.getString("musicId"),
        pandoraId = trackJSON.getString("pandoraId"),
        artUrls = artJSONtoMap(trackJSON.getJSONArray("art"))
    )

    internal companion object {
        /**
         * This function creates a list of [Track] objects from a JSON array from the Pandora API response.
         *
         * @param [jsonArray] The JSON array from the Pandora API response.
         * @return A list of [Track] objects.
         */
        internal fun createListFromJSONArray(jsonArray: JSONArray): List<Track> {
            return List(jsonArray.length()) {
                Track(jsonArray.getJSONObject(it))
            }
        }
    }
}