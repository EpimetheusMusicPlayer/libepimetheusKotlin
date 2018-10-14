package tk.hacker1024.libepimetheus.data.search

import tk.hacker1024.libepimetheus.data.GENERIC_ART_URL

/**
 * The autocomplete response is a series of lines and values separated with tabs, something like this.
 * The values are different for each data type.
 * | ID | ARTIST (if is a song/album) | NAME | SEO TOKEN | LISTENER COUNT (if is a station) | ART URL
 *
 * If a field is not relevant to the type of item, it is a blank string, e.g. stations do not have artist names.
 */

/**
 * A data class to hold autocomplete results.
 *
 * @property [artists] A list of [Artist]s.
 * @property [tracks] A list of [Track]s.
 * @property [genreStations] A list of [GenreStation]s.
 */
data class AutocompleteResults(
    val artists: List<Artist>,
    val tracks: List<Track>,
    val genreStations: List<GenreStation>
) {
    internal companion object {
        internal fun fromRawData(rawData: String, artSize: Int): AutocompleteResults {
            val artists = mutableListOf<Artist>()
            val tracks = mutableListOf<Track>()
            val genreStations= mutableListOf<GenreStation>()

            rawData.lines().let { lines ->
                List(lines.size - 1) {
                    lines[it].split("\t")
                }.let { data ->
                    for (values in data) {
                        when {
                            values[0][0].run {
                                equals('R', true) or equals('C', true)
                            } -> {
                                artists.add(
                                    Artist(
                                        name =          values[1],
                                        listenerCount = values[3].toInt(),
                                        pandoraId =     values[0],
                                        artUrls = hashMapOf(
                                            artSize to values[4].run {
                                                if (isBlank()) GENERIC_ART_URL else this
                                            }
                                        )
                                    )
                                )
                            }

                            values[0][0].equals('S', true)-> {
                                tracks.add(
                                    Track(
                                        name =          values[2],
                                        artist =        values[1],
                                        pandoraId =            values[0],
                                        artUrls = hashMapOf(
                                            artSize to values[5].run {
                                                if (isBlank()) GENERIC_ART_URL else this
                                            }
                                        )
                                    )
                                )
                            }

                            values[0][0].toUpperCase().equals('G', true) -> {
                                genreStations.add(
                                    GenreStation(
                                        name =          values[1],
                                        listenerCount = values[3].toInt(),
                                        pandoraId =     values[0],
                                        artUrls = hashMapOf(
                                            artSize to values[4].run {
                                                if (isBlank()) GENERIC_ART_URL else this
                                            }
                                        )
                                    )
                                )
                            }
                        }
                    }
                }
            }

            return AutocompleteResults(artists, tracks, genreStations)
        }
    }
}