[libepimetheus](../../index.md) / [tk.hacker1024.libepimetheus.data](../index.md) / [PandoraData](./index.md)

# PandoraData

`abstract class PandoraData`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PandoraData(defaultArtUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = GENERIC_ART_URL)` |

### Properties

| Name | Summary |
|---|---|
| [artUrls](art-urls.md) | `abstract val artUrls: `[`HashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [name](name.md) | `abstract val name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| Name | Summary |
|---|---|
| [getArtUrl](get-art-url.md) | `fun getArtUrl(preferredSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>This function will return the data object's art URL for the given size. If the given size doesn't exist, it will return the URL for the nearest larger size. |

### Inheritors

| Name | Summary |
|---|---|
| [Artist](../-artist/index.md) | `data class Artist : `[`PandoraData`](./index.md)<br>A data class to hold information about an artist. |
| [GenreCategory](../-genre-category/index.md) | `data class GenreCategory : `[`PandoraData`](./index.md)<br>A data class to hold information about a genre category. |
| [GenreStation](../-genre-station/index.md) | `data class GenreStation : `[`PandoraData`](./index.md)<br>A data class to hold information about a genre station. |
| [Song](../-song/index.md) | `class Song : `[`PandoraData`](./index.md)<br>A data class to hold information about a song. |
| [Station](../-station/index.md) | `data class Station : `[`PandoraData`](./index.md)`, `[`Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html)<br>A data class to hold information about a station. |
