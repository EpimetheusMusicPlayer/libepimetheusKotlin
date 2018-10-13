package tk.hacker1024.libepimetheus.data

import org.json.JSONArray
import org.json.JSONObject

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
        artUrls = HashMap<Int, String>().also { artMap ->
            if (genreCategoryJSON.has("art")) {
                genreCategoryJSON.getJSONArray("art").also { artJSONArray ->
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