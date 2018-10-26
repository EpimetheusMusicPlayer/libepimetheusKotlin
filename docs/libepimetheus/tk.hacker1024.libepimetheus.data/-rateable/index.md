[libepimetheus](../../index.md) / [tk.hacker1024.libepimetheus.data](../index.md) / [Rateable](./index.md)

# Rateable

`interface Rateable`

### Properties

| Name | Summary |
|---|---|
| [feedbackId](feedback-id.md) | `abstract var feedbackId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [rating](rating.md) | `abstract var rating: RatingCompat` |
| [settingFeedback](setting-feedback.md) | `abstract var settingFeedback: RatingCompat` |

### Extension Functions

| Name | Summary |
|---|---|
| [deleteFeedback](../../tk.hacker1024.libepimetheus/delete-feedback.md) | `fun `[`Rateable`](./index.md)`.deleteFeedback(user: `[`User`](../../tk.hacker1024.libepimetheus/-user/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Removes feedback from a [Rateable](./index.md). |

### Inheritors

| Name | Summary |
|---|---|
| [FeedbackItem](../../tk.hacker1024.libepimetheus.data.feedback/-feedback-item/index.md) | `data class FeedbackItem : `[`PandoraData`](../-pandora-data/index.md)`, `[`Rateable`](./index.md)<br>A data class to hold information about a feedback item. |
| [Song](../-song/index.md) | `data class Song : `[`PandoraData`](../-pandora-data/index.md)`, `[`Rateable`](./index.md)<br>A data class to hold information about a song. |
