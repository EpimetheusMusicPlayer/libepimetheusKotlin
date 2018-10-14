[libepimetheus](../../index.md) / [tk.hacker1024.libepimetheus.data.search](../index.md) / [GenreCategory](./index.md)

# GenreCategory

`data class GenreCategory : `[`PandoraData`](../../tk.hacker1024.libepimetheus.data/-pandora-data/index.md)

A data class to hold information about a genre category.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `GenreCategory(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, token: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, artUrls: `[`HashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>)`<br>A data class to hold information about a genre category. |

### Properties

| Name | Summary |
|---|---|
| [artUrls](art-urls.md) | `val artUrls: `[`HashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [name](name.md) | `val name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name of the category. |

### Inherited Functions

| Name | Summary |
|---|---|
| [getArtUrl](../../tk.hacker1024.libepimetheus.data/-pandora-data/get-art-url.md) | `fun getArtUrl(preferredSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>This function will return the data object's art URL for the given size. If the given size doesn't exist, it will return the URL for the nearest larger size. |

### Extension Functions

| Name | Summary |
|---|---|
| [getGenres](../../tk.hacker1024.libepimetheus/get-genres.md) | `fun `[`GenreCategory`](./index.md)`.getGenres(user: `[`User`](../../tk.hacker1024.libepimetheus/-user/index.md)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`GenreStation`](../-genre-station/index.md)`>`<br>Retrieves all the genres belonging to a category. |
