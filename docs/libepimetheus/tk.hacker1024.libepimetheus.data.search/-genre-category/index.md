[libepimetheus](../../index.md) / [tk.hacker1024.libepimetheus.data.search](../index.md) / [GenreCategory](./index.md)

# GenreCategory

`data class GenreCategory : `[`PandoraData`](../../tk.hacker1024.libepimetheus.data/-pandora-data/index.md)`, `[`Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html)

A data class to hold information about a genre category.

### Types

| Name | Summary |
|---|---|
| [CREATOR](-c-r-e-a-t-o-r/index.md) | `companion object CREATOR : `[`Creator`](https://developer.android.com/reference/android/os/Parcelable/Creator.html)`<`[`GenreCategory`](./index.md)`>` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `GenreCategory(parcel: `[`Parcel`](https://developer.android.com/reference/android/os/Parcel.html)`)``GenreCategory(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, token: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, artUrls: `[`HashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>)`<br>A data class to hold information about a genre category. |

### Properties

| Name | Summary |
|---|---|
| [artUrls](art-urls.md) | `val artUrls: `[`HashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [name](name.md) | `val name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name of the category. |

### Functions

| Name | Summary |
|---|---|
| [describeContents](describe-contents.md) | `fun describeContents(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [writeToParcel](write-to-parcel.md) | `fun writeToParcel(parcel: `[`Parcel`](https://developer.android.com/reference/android/os/Parcel.html)`, flags: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [getArtUrl](../../tk.hacker1024.libepimetheus.data/-pandora-data/get-art-url.md) | `fun getArtUrl(preferredSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>This function will return the data object's art URL for the given size. If the given size doesn't exist, it will return the URL for the nearest larger size. |

### Companion Object Functions

| Name | Summary |
|---|---|
| [createFromParcel](create-from-parcel.md) | `fun createFromParcel(parcel: `[`Parcel`](https://developer.android.com/reference/android/os/Parcel.html)`): `[`GenreCategory`](./index.md) |
| [newArray](new-array.md) | `fun newArray(size: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`GenreCategory`](./index.md)`?>` |

### Extension Functions

| Name | Summary |
|---|---|
| [getGenres](../../tk.hacker1024.libepimetheus/get-genres.md) | `fun `[`GenreCategory`](./index.md)`.getGenres(user: `[`User`](../../tk.hacker1024.libepimetheus/-user/index.md)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`GenreStation`](../-genre-station/index.md)`>`<br>Retrieves all the genres belonging to a category. |
