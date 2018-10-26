[libepimetheus](../index.md) / [tk.hacker1024.libepimetheus](index.md) / [getFeedback](./get-feedback.md)

# getFeedback

`fun `[`Station`](../tk.hacker1024.libepimetheus.data/-station/index.md)`.getFeedback(user: `[`User`](-user/index.md)`, positive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, pageSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, startIndex: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0): `[`FeedbackList`](../tk.hacker1024.libepimetheus.data.feedback/-feedback-list/index.md)

Retrieves the station feedback.

### Parameters

`user` - The [User](-user/index.md) object to authenticate with.

`positive` - true = thumbs up, false = thumbs down.

`pageSize` - The size of the feedback list.

`startIndex` - The index to start at (used for pagination).

**Receiver**
The station to get the feedback for.

**Return**
A [FeedbackList](../tk.hacker1024.libepimetheus.data.feedback/-feedback-list/index.md).

