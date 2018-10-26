[libepimetheus](../../index.md) / [tk.hacker1024.libepimetheus.data](../index.md) / [Station](./index.md)

# Station

`data class Station : `[`PandoraData`](../-pandora-data/index.md)`, `[`Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html)

A data class to hold information about a station.

### Types

| Name | Summary |
|---|---|
| [CREATOR](-c-r-e-a-t-o-r/index.md) | `companion object CREATOR : `[`Creator`](https://developer.android.com/reference/android/os/Parcelable/Creator.html)`<`[`Station`](./index.md)`>` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Station(parcel: `[`Parcel`](https://developer.android.com/reference/android/os/Parcel.html)`)``Station(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, isShuffle: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, isThumbprint: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, canDelete: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, canRename: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, artUrls: `[`HashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>)`<br>Creates the object with the given name, id, and art URLs. |

### Properties

| Name | Summary |
|---|---|
| [artUrls](art-urls.md) | `val artUrls: `[`HashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>A [HashMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html) containing art URLs values with their sizes as keys. |
| [canDelete](can-delete.md) | `val canDelete: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Boolean indicating if the station can be deleted. |
| [canRename](can-rename.md) | `val canRename: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Boolean indication if the station can be renamed. |
| [isShuffle](is-shuffle.md) | `val isShuffle: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Boolean indicating if the station is the Shuffle/QuickMix station. |
| [isThumbprint](is-thumbprint.md) | `val isThumbprint: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Boolean indicating if the station is the thumbprint station. |
| [name](name.md) | `val name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name of the station. |

### Functions

| Name | Summary |
|---|---|
| [describeContents](describe-contents.md) | `fun describeContents(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [equals](equals.md) | `fun equals(other: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](hash-code.md) | `fun hashCode(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [writeToParcel](write-to-parcel.md) | `fun writeToParcel(parcel: `[`Parcel`](https://developer.android.com/reference/android/os/Parcel.html)`, flags: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [getArtUrl](../-pandora-data/get-art-url.md) | `fun getArtUrl(preferredSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>This function will return the data object's art URL for the given size. If the given size doesn't exist, it will return the URL for the nearest larger size. |

### Companion Object Functions

| Name | Summary |
|---|---|
| [createFromParcel](create-from-parcel.md) | `fun createFromParcel(parcel: `[`Parcel`](https://developer.android.com/reference/android/os/Parcel.html)`): `[`Station`](./index.md) |
| [newArray](new-array.md) | `fun newArray(size: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`Station`](./index.md)`?>` |

### Extension Functions

| Name | Summary |
|---|---|
| [delete](../../tk.hacker1024.libepimetheus/delete.md) | `fun `[`Station`](./index.md)`.delete(user: `[`User`](../../tk.hacker1024.libepimetheus/-user/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>This extension function deletes the receiver station. |
| [getDetails](../../tk.hacker1024.libepimetheus/get-details.md) | `fun `[`Station`](./index.md)`.getDetails(user: `[`User`](../../tk.hacker1024.libepimetheus/-user/index.md)`): `[`JSONObject`](https://developer.android.com/reference/org/json/JSONObject.html)<br>This extension function retrieves detailed information about a [Station](./index.md). |
| [getFeedback](../../tk.hacker1024.libepimetheus/get-feedback.md) | `fun `[`Station`](./index.md)`.getFeedback(user: `[`User`](../../tk.hacker1024.libepimetheus/-user/index.md)`, positive: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, pageSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, startIndex: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0): `[`FeedbackList`](../../tk.hacker1024.libepimetheus.data.feedback/-feedback-list/index.md)<br>Retrieves the station feedback. |
| [getPlaylist](../../tk.hacker1024.libepimetheus/get-playlist.md) | `fun `[`Station`](./index.md)`.getPlaylist(user: `[`User`](../../tk.hacker1024.libepimetheus/-user/index.md)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Song`](../-song/index.md)`>`<br>This extension function returns the station playlist in the form of a list of [Song](../-song/index.md) objects. |
| [rename](../../tk.hacker1024.libepimetheus/rename.md) | `fun `[`Station`](./index.md)`.rename(newName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, user: `[`User`](../../tk.hacker1024.libepimetheus/-user/index.md)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>This extension function renames the receiver station. |
