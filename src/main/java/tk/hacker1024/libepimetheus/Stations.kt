package tk.hacker1024.libepimetheus

import org.json.JSONObject
import tk.hacker1024.libepimetheus.data.Song
import tk.hacker1024.libepimetheus.data.Station

/**
 * This singleton contains functions to retrieve station information.
 * The Pandora station APIs are documented [here](https://6xq.net/pandora-apidoc/rest/stations/).
 */
object Stations {
    /**
     * This function retrieves a list of the users stations.
     *
     * @param [user] The [User] object to authenticate with.
     * @param [includeShuffle] Whether to include the Shuffle station in the list or not.
     * @param [trim] Remove leading and trailing whitespace from the station names.
     * @param [sortWith] A comparator to sort the list with. Can be null. The default comparator
     *                   sorts in alphabetical order, with the Shuffle and Thumbprint stations at
     *                   the top.
     * @return A list of [Station] objects.
     */
    fun getStations(
        user: User,
        includeShuffle: Boolean = true,
        trim: Boolean = true,
        sortWith: Comparator<Station>? =
            Comparator { station1, station2 ->
                when {
                    station1.isShuffle -> -2
                    station2.isShuffle -> 2
                    station1.isThumbprint -> -1
                    station2.isThumbprint -> 1
                    else -> compareValues(station1.name, station2.name)
                }
            }
    ): List<Station> =
        Station.createListFromJSONArray(
            Networking.makeApiRequest(
                "v1",
                "station/getStations",
                JSONObject().put("pageSize", 250),
                user
            ).getJSONArray("stations"),
            trim
        ).apply {
            if (includeShuffle) {
                add(0, getShuffle(user, trim))
            }
        }.run { if (sortWith != null) sortedWith(sortWith) else this }

    /**
     * This function gets the shuffle station, which isn't included in [getStations].
     *
     * @param [user] The [User] object to authenticate with.
     * @param [trim] Remove leading and trailing whitespace from the station names.
     * @return A shuffle [Station] object
     */
    private fun getShuffle(user: User, trim: Boolean = true) =
        Station(
            Networking.makeApiRequest(
                version = "v1",
                endpoint = "station/shuffle",
                user = user
            ),
            trim
        )
}

/**
 * This extension function retrieves detailed information about a [Station].
 *
 * @receiver The [Station] to get the details of.
 * @param [user] The [User] object to authenticate with.
 * @return The JSON response from Pandora.
 */
fun Station.getDetails(user: User) =
    Networking.makeApiRequest(
        "v1",
        "station/getStationDetails",
        JSONObject()
            .put("stationId", id)
            .put("isCurrentStation", false),
        user
    )

/**
 * This extension function returns the station playlist in the form of a list of [Song] objects.
 *
 * @receiver The [Station] to get the playlist for.
 * @param [user] The [User] object to authenticate with.
 * @return The station playlist in the form of a list of [Song] objects.
 */
fun Station.getPlaylist(user: User) =
    Song.createListFromJSONArray(
        Networking.makeApiRequest(
            "v1",
            "playlist/getFragment",
            JSONObject()
                .put("stationId", id)
                .put("isStationStart", false)
                .put("fragmentRequestReason", "Normal")
                .put("audioFormat", "aacplus")
                .put("startingAtTrackId", null)
                .put("onDemandArtistMessageArtistUidHex", null)
                .put("onDemandArtistMessageIdHex", null),
            user
        ).getJSONArray("tracks")
    )

/**
 * This extension function deletes the receiver station.
 *
 * @param [user] The [User] object to authenticate with.
 */
fun Station.delete(user: User) {
    Networking.makeApiRequest(
        "v1",
        "station/removeStation",
        JSONObject().put("stationId", id),
        user
    )
}


/**
 * This extension function renames the receiver station.
 *
 * @param [user] The [User] object to authenticate with.
 * @return The name of the station after it has been renamed.
 */
fun Station.rename(newName: String, user: User): String {
    if (canRename) {
        Networking.makeApiRequest(
            "v1",
            "station/updateStation",
            JSONObject()
                .put("name", newName)
                .put("stationId", id),
            user
        ).apply {
            return getString("name")
        }
    } else {
        return name
    }
}