package tk.hacker1024.libepimetheus

import org.json.JSONArray
import org.json.JSONObject
import tk.hacker1024.libepimetheus.data.search.*

/**
 * Data class holding station recommendations from Pandora.
 *
 * @property [artists] The recommended artists.
 * @property [genreStations] The recommended genre stations.
 */
data class StationRecommendations(val artists: List<Artist>, val genreStations: List<GenreStation>)

/**
 * This singleton contains functions to browse Pandora.
 */
object Browse {
    /**
     * Retrieves station recommendations from Pandora.
     *
     * @param [user] The [User] object to authenticate with.
     * @return A [StationRecommendations] object.
     */
    fun getStationRecommendations(user: User) =
        Networking.makeApiRequest(
            "v1",
            "search/getStationRecommendations",
            JSONObject(),
            user
        ).run {
            StationRecommendations(
                Artist.createListFromJSONArray(getJSONArray("artists")),
                GenreStation.createListFromJSONArray(getJSONArray("genreStations"))
            )
        }

    /**
     * Retrieves all genre categories from Pandora.
     *
     * @param [user] The [User] object to authenticate with.
     * @return A list of [GenreCategory] objects.
     */
    fun getGenreCategories(user: User) =
        Networking.makeApiRequest(
            "v1",
            "music/genrecategories",
            JSONObject(),
            user
        ).run {
            GenreCategory.createListFromJSONArray(getJSONArray("categories"))
        }
}

/**
 * Retrieves all the genres belonging to a category.
 *
 * @receiver The category.
 * @param [user] The [User] object to authenticate with.
 * @return A list of [GenreStation]s.
 */
fun GenreCategory.getGenres(user: User): List<GenreStation> {
    return Networking.makeApiRequest(
        "v1",
        "music/genres",
        JSONObject().put("categoryToken", token),
        user
    ).run {
        GenreStation.createListFromJSONArray(getJSONArray("genres"))
    }
}

/**
 * @see Listenable.add
 */
internal fun add(listenable: Listenable, user: User, name: String) {
    Networking.makeApiRequest(
        "v1",
        "station/createStation",
        JSONObject()
            .put("pandoraId", listenable.pandoraId)
            .put("stationName", name),
        user
    )
}

/**
 * This singleton contains functions to search Pandora.
 */
object Search {
    /**
     * Retrieves autocomplete suggestions from Pandora.
     *
     * @param [query] The search query.
     * @param [artSize] The art size to request.
     * @param [user] The [User] object to authenticate with.
     * @return [AutocompleteResults].
     */
    fun getAutocomplete(query: String, artSize: Int, user: User): AutocompleteResults =
        AutocompleteResults.fromRawData(
            Networking.makeAutocompleteRequest(query, artSize, user),
            artSize
        )

    /**
     * Sends a search request to Pandora.
     *
     * @param [query] The query to send.
     * @param [types] The types of items to search for.
     * @param [start] The index to start at.
     * @param [count] The page size.
     * @param [user] The [User] object to authenticate with.
     *
     * @return [SearchResults].
     */
    fun search(query: String, types: List<SearchType> = listOf(SearchType.ALL), start: Int, count: Int, user: User) =
        SearchResults.createFromJSON(
            Networking.makeApiRequest(
                "v3",
                "sod/search",
                JSONObject()
                    .put("annotate", true)
                    .put("count", count)
                    .put("query", query)
                    .put("start", start)
                    .put(
                        "types",
                        JSONArray()
                            .apply {
                                for (type in types) {
                                    for (id in type.identifiers) {
                                        put(id)
                                    }
                                }
                            }
                    ),
                user
            )
        )
}