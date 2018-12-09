package tk.hacker1024.libepimetheus

import org.json.JSONArray
import org.json.JSONObject
import tk.hacker1024.libepimetheus.data.search.*
import tk.hacker1024.libepimetheus.data.search.details.TrackDetails

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
     * @param [user] The [User] object to authenticate with.
     * @param [query] The query to send.
     * @param [start] The index to start at.
     * @param [count] The page size.
     * @param [artSize] The art size. Note that this is preferred, and many results may have a different size. Should be one of the following: 90, 130, 500, 640, 1080
     * @param [types] The types of items to search for.
     *
     * @return [SearchResults].
     */
    fun search(user: User, query: String, start: Int, count: Int, artSize: Int, vararg types: SearchType) =
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
            ),
            artSize
        )
}

/**
 * Get track details.
 *
 * @param [user] the [User] object to authenticate with.
 * @return A [TrackDetails] object.
 */
fun Track.getDetails(user: User) =
    TrackDetails(
        Networking.makeApiRequest(
            "v1",
            "music/track",
            JSONObject().put("token", pandoraId.replace("TR:", "S")),
            user
        )
    )


/**
 * Gets the lyrics of a track.
 *
 * @param [user] The [User] object to authenticate with.
 * @param [explicit] Whether to censor explicit language or not (asterisks replacing middle characters of words)
 * @return The lyrics string.
 */
fun TrackDetails.getLyrics(explicit: Boolean = true, user: User) =
    TrackDetails.lyricsArrayToString(
        Networking.makeApiRequest(
            "v1",
            "music/fullLyrics",
            JSONObject()
                .put("nonExplicit", !explicit)
                .put("trackUid", musicId),
            user
        ).getJSONArray("lines")
    )