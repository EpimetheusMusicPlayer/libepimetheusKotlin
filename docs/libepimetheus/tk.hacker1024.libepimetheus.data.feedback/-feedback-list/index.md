[libepimetheus](../../index.md) / [tk.hacker1024.libepimetheus.data.feedback](../index.md) / [FeedbackList](./index.md)

# FeedbackList

`class FeedbackList : `[`ArrayList`](https://developer.android.com/reference/java/util/ArrayList.html)`<`[`FeedbackItem`](../-feedback-item/index.md)`>`

An [ArrayList](https://developer.android.com/reference/java/util/ArrayList.html) with information about the feedback items in it.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FeedbackList(isPositive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, totalSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, stationId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, size: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, init: (`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`) -> `[`FeedbackItem`](../-feedback-item/index.md)`)``FeedbackList(isPositive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, totalSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, stationId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`<br>An [ArrayList](https://developer.android.com/reference/java/util/ArrayList.html) with information about the feedback items in it. |

### Properties

| Name | Summary |
|---|---|
| [isPositive](is-positive.md) | `val isPositive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>true = thumbs up, false = thumbs down. |
| [totalSize](total-size.md) | `val totalSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The total amount of feedback in the station. |

### Functions

| Name | Summary |
|---|---|
| [toString](to-string.md) | `fun toString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
