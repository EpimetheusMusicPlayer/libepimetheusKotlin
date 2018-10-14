package tk.hacker1024.libepimetheus.data.search

import org.json.JSONArray
import org.json.JSONObject
import tk.hacker1024.libepimetheus.data.PandoraData

/**
 * A data class to hold information about a genre category.
 *
 * @property [name] The name of the category.
 */
data class GenreCategory(
    override val name: String,
    internal val token: String,
    override val artUrls: HashMap<Int, String>
) : PandoraData() {
    private constructor(genreCategoryJSON: JSONObject) : this(
        name = genreCategoryJSON.getString("name"),
        token = genreCategoryJSON.getString("token"),
        artUrls = artJSONtoMap(genreCategoryJSON.getJSONArray("art"))
    )

    internal companion object {
        /**
         * This function creates a list of [GenreCategory] objects from a JSON array from the Pandora API response.
         *
         * @param [jsonArray] The JSON array from the Pandora API response.
         * @return A list of [GenreCategory] objects.
         */
        internal fun createListFromJSONArray(jsonArray: JSONArray): List<GenreCategory> {
            return List(jsonArray.length()) {
               GenreCategory(jsonArray.getJSONObject(it))
            }
        }
    }
}