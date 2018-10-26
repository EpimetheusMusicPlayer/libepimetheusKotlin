[libepimetheus](../../index.md) / [tk.hacker1024.libepimetheus.data.feedback](../index.md) / [FeedbackItem](./index.md)

# FeedbackItem

`data class FeedbackItem : `[`PandoraData`](../../tk.hacker1024.libepimetheus.data/-pandora-data/index.md)`, `[`Rateable`](../../tk.hacker1024.libepimetheus.data/-rateable/index.md)

A data class to hold information about a feedback item.

### Types

| Name | Summary |
|---|---|
| [DiffUtilItemCallback](-diff-util-item-callback/index.md) | `class DiffUtilItemCallback : ItemCallback<`[`FeedbackItem`](./index.md)`>`<br>A [DiffUtil.ItemCallback](#) implementation. |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FeedbackItem(rating: RatingCompat, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, artist: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, album: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, sampleUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, feedbackId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, artUrls: `[`HashMap`](https://developer.android.com/reference/java/util/HashMap.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>)`<br>A data class to hold information about a feedback item. |

### Properties

| Name | Summary |
|---|---|
| [album](album.md) | `val album: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name of the album. |
| [artUrls](art-urls.md) | `val artUrls: `[`HashMap`](https://developer.android.com/reference/java/util/HashMap.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [artist](artist.md) | `val artist: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name of the artist. |
| [feedbackId](feedback-id.md) | `var feedbackId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [name](name.md) | `val name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name of the song. |
| [rating](rating.md) | `var rating: RatingCompat`<br>The feedback item rating. |
| [sampleUrl](sample-url.md) | `val sampleUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>The sample audio URL ( ~ 30s of the song) from iTunes. |
| [settingFeedback](setting-feedback.md) | `var settingFeedback: RatingCompat` |

### Inherited Functions

| Name | Summary |
|---|---|
| [getArtUrl](../../tk.hacker1024.libepimetheus.data/-pandora-data/get-art-url.md) | `fun getArtUrl(preferredSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>This function will return the data object's art URL for the given size. If the given size doesn't exist, it will return the URL for the nearest larger size. |

### Extension Functions

| Name | Summary |
|---|---|
| [deleteFeedback](../../tk.hacker1024.libepimetheus/delete-feedback.md) | `fun `[`Rateable`](../../tk.hacker1024.libepimetheus.data/-rateable/index.md)`.deleteFeedback(user: `[`User`](../../tk.hacker1024.libepimetheus/-user/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Removes feedback from a [Rateable](../../tk.hacker1024.libepimetheus.data/-rateable/index.md). |
