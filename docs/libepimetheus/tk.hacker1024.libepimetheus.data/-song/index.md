[libepimetheus](../../index.md) / [tk.hacker1024.libepimetheus.data](../index.md) / [Song](./index.md)

# Song

`data class Song : `[`PandoraData`](../-pandora-data/index.md)`, `[`Rateable`](../-rateable/index.md)

A data class to hold information about a song.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Song(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, artist: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, album: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, rating: RatingCompat, id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, trackToken: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, audioUri: `[`Uri`](https://developer.android.com/reference/android/net/Uri.html)`, artUrls: `[`HashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>)`<br>A data class to hold information about a song. |

### Properties

| Name | Summary |
|---|---|
| [album](album.md) | `val album: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The song album. |
| [artUrls](art-urls.md) | `val artUrls: `[`HashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [artist](artist.md) | `val artist: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The song artist. |
| [audioUri](audio-uri.md) | `val audioUri: `[`Uri`](https://developer.android.com/reference/android/net/Uri.html)<br>The song's audio URI. |
| [feedbackId](feedback-id.md) | `lateinit var feedbackId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [name](name.md) | `val name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name of the song. |
| [rating](rating.md) | `var rating: RatingCompat`<br>The song rating. |
| [settingFeedback](setting-feedback.md) | `var settingFeedback: RatingCompat`<br>The feedback that's being sent to Pandora at the time. If it's     unrated, no feedback is being sent. |

### Inherited Functions

| Name | Summary |
|---|---|
| [getArtUrl](../-pandora-data/get-art-url.md) | `fun getArtUrl(preferredSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>This function will return the data object's art URL for the given size. If the given size doesn't exist, it will return the URL for the nearest larger size. |

### Extension Functions

| Name | Summary |
|---|---|
| [addFeedback](../../tk.hacker1024.libepimetheus/add-feedback.md) | `fun `[`Song`](./index.md)`.addFeedback(thumbsUp: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, user: `[`User`](../../tk.hacker1024.libepimetheus/-user/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Adds feedback for the [Song](./index.md). |
| [addTired](../../tk.hacker1024.libepimetheus/add-tired.md) | `fun `[`Song`](./index.md)`.addTired(user: `[`User`](../../tk.hacker1024.libepimetheus/-user/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the [Song](./index.md) as tired (won't play for a while). |
| [deleteFeedback](../../tk.hacker1024.libepimetheus/delete-feedback.md) | `fun `[`Rateable`](../-rateable/index.md)`.deleteFeedback(user: `[`User`](../../tk.hacker1024.libepimetheus/-user/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Removes feedback from a [Rateable](../-rateable/index.md). |
