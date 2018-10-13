package tk.hacker1024.libepimetheus

import org.json.JSONObject
import tk.hacker1024.libepimetheus.data.Artist
import tk.hacker1024.libepimetheus.data.GenreCategory
import tk.hacker1024.libepimetheus.data.GenreStation

object Browse {
    data class StationRecommendations(val artists: List<Artist>, val genreStations: List<GenreStation>)

    fun getStationRecommendations(user: User) =
        Networking.makeApiRequest(
            "search/getStationRecommendations",
            JSONObject(),
            user
        ).run {
            StationRecommendations(
                Artist.createListFromJSONArray(getJSONArray("artists")),
                GenreStation.createListFromJSONArray(getJSONArray("genreStations"))
            )
        }

    fun getGenreCategories(user: User) =
        Networking.makeApiRequest(
            "music/genrecategories",
            JSONObject(),
            user
        ).run {
            GenreCategory.createListFromJSONArray(getJSONArray("categories"))
        }

    fun getGenres(genreCategory: GenreCategory, user: User): List<GenreStation> {
        return Networking.makeApiRequest(
            "music/genres",
            JSONObject().put("categoryToken", genreCategory.token),
            user
        ).run {
            GenreStation.createListFromJSONArray(getJSONArray("genres"))
        }
    }
}