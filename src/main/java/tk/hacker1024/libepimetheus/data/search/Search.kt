package tk.hacker1024.libepimetheus.data.search

import org.json.JSONObject
import tk.hacker1024.libepimetheus.data.PandoraData

data class SearchResults(
    val artists: List<Artist>,
    val tracks: List<Track>,
    val genres: List<GenreStation>
) {
    internal companion object {
        internal fun createFromJSON(jsonObject: JSONObject): SearchResults {
            jsonObject.getJSONArray("items").run {
                return SearchResults(
                    List(jsonObject.getInt("numArtists")) {
                        getJSONObject(it).run {
                            Artist(
                                name = getString("name"),
                                pandoraId = getString("pandoraId"),
                                listenerCount = getInt("listenerCount"),
                                artUrls = PandoraData.artJSONtoMap(getJSONArray("art"))
                            )
                        }
                    },

                    List(jsonObject.getInt("numTracks")) {
                        getJSONObject(it + jsonObject.getInt("numArtists")).run {
                            Track(
                                name = getString("name"),
                                artist = getString("artistName"),
                                pandoraId = getString("pandoraId"),
                                artUrls = PandoraData.artJSONtoMap(getJSONArray("art"))
                            )
                        }
                    },

                    List(jsonObject.getInt("numGenres")) {
                        getJSONObject(it + jsonObject.getInt("numArtists") + jsonObject.getInt("numTracks")).run {
                            GenreStation(
                                name = getString("name"),
                                pandoraId = getString("pandoraId"),
                                artUrls = PandoraData.artJSONtoMap(getJSONArray("art"))
                            )
                        }
                    }
                )
            }
        }
    }
}