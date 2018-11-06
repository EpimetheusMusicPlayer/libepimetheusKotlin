package tk.hacker1024.libepimetheus.data.search

import org.json.JSONObject

internal const val ART_HOST = "https://content-images.p-cdn.com/"

enum class SearchType(internal val identifiers: List<String>) {
    ALBUM(listOf("AL")),
    ARTIST(listOf("AR", "CO")),
    TRACK(listOf("TR")),
    STATION(listOf("SF", "ST")),
    PLAYLIST(listOf("PL")),
    ALL(listOf("AL", "AR", "CO", "TR", "SF", "ST", "PL"))
}

/**
 * Data class to hold search results.
 *
 * @property [artists] The list of artists.
 * @property [albums] The list of albums.
 * @property [tracks] The list of tracks.
 * @property [stations] The list of stations.
 * @property [playlists] The list of playlists.
 */
data class SearchResults(
    val artists: List<Artist>,
    val albums: List<Album>,
    val tracks: List<Track>,
    val stations: List<GenreStation>,
    val playlists: List<Playlist>
) {
    internal companion object {
        internal fun createFromJSON(jsonObject: JSONObject, artSize: Int): SearchResults {
            val artistList = ArrayList<Artist>()
            val albumList = ArrayList<Album>()
            val trackList = ArrayList<Track>()
            val stationList = ArrayList<GenreStation>()
            val playlistList = ArrayList<Playlist>()

            val resultsJSON = jsonObject.getJSONArray("results")
            val annotationsJSON = jsonObject.getJSONObject("annotations")

            for (i in 0 until resultsJSON.length()) {
                resultsJSON.getString(i).apply {
                    annotationsJSON.getJSONObject(this).apply {
                        when (substring(0..1)) {
                            "AR", "CO" -> {
                                artistList.add(
                                    Artist(
                                        name = getString("name"),
                                        pandoraId = getString("pandoraId"),
                                        trackCount = getInt("trackCount"),
                                        albumCount = getInt("albumCount"),
                                        artUrls = HashMap<Int, String>().apply {
                                            getJSONObject("icon").apply {
                                                if (has("artUrl")) put(0, ART_HOST + getString("artUrl"))
                                            }
                                        }
                                    )
                                )
                            }
                            "AL" -> {
                                albumList.add(
                                    Album(
                                        name = getString("name"),
                                        artistName = getString("artistName"),
                                        pandoraId = getString("pandoraId"),
                                        trackCount = getInt("trackCount"),
                                        artUrls = HashMap<Int, String>().apply {
                                            getJSONObject("icon").apply {
                                                if (has("thorId")) put(0, ART_HOST + getString("thorId"))
                                            }
                                        }
                                    )
                                )
                            }
                            "TR" -> {
                                trackList.add(
                                    Track(
                                        name = getString("name"),
                                        artistName = getString("artistName"),
                                        pandoraId = getString("pandoraId"),
                                        artUrls = HashMap<Int, String>().apply {
                                            getJSONObject("icon").apply {
                                                if (has("thorId")) put(0, ART_HOST + getString("thorId"))
                                            }
                                        }
                                    )
                                )
                            }
                            "SF", "ST" -> {
                                stationList.add(
                                    GenreStation(
                                        name = getString("name"),
                                        pandoraId = getString("pandoraId"),
                                        artUrls = HashMap<Int, String>().apply {
                                            getJSONObject("icon").apply {
                                                if (has("artUrl")) put(0, ART_HOST + getString("artUrl"))
                                            }
                                        }
                                    )
                                )
                            }
                            "PL" -> {
                                playlistList.add(
                                    Playlist(
                                        name = getString("name"),
                                        totalTracks = getInt("totalTracks"),
                                        pandoraId = getString("pandoraId"),
                                        artUrls = HashMap<Int, String>().apply {
                                            if (has("thorLayers")) put(0, Playlist.getGridUrl(getString("thorLayers"), artSize))
                                        }
                                    )
                                )
                            }
                        }
                    }
                }
            }

            return SearchResults(
                artists = artistList,
                albums = albumList,
                tracks = trackList,
                stations = stationList,
                playlists = playlistList
            )
        }
    }
}