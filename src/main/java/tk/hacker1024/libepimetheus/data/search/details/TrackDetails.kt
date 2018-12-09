package tk.hacker1024.libepimetheus.data.search.details

import org.json.JSONArray
import org.json.JSONObject
import tk.hacker1024.libepimetheus.data.PandoraData

// TODO document
// TODO album, similar
data class TrackDetails(
    override val name: String,
    internal val pandoraId: String,
    internal val musicId: String,
    val artistName: String,
    val albumName: String,
    val lyricSnippet: String,
    val focusTraits: List<String>,
    override val artUrls: HashMap<Int, String>
) : PandoraData() {
    internal constructor(jsonObject: JSONObject) : this(
        name = jsonObject.getString("songTitle"),
        pandoraId = jsonObject.getString("pandoraId"),
        musicId = jsonObject.getString("musicId"),
        artistName = jsonObject.getString("artistName"),
        albumName = jsonObject.getString("albumTitle"),
        lyricSnippet =
            if (jsonObject.has("lyricSnippet"))
                lyricsArrayToString(jsonObject.getJSONObject("lyricSnippet").getJSONArray("lines"))
            else "",
        focusTraits =
            if (jsonObject.has("focusTraits"))
                jsonObject.getJSONArray("focusTraits").run { List(length()) { getString(it) } }
            else emptyList(),
        artUrls = artJSONtoMap(jsonObject.getJSONArray("albumArt"))
    )

    companion object {
        internal fun lyricsArrayToString(lyricsArray: JSONArray) =
            StringBuilder().apply {
                for (i in 0 until lyricsArray.length()) {
                    if (i > 0) appendln()
                    append(lyricsArray.getString(i))
                }
            }.toString()
    }
}