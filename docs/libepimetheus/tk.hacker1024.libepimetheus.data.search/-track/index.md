[libepimetheus](../../index.md) / [tk.hacker1024.libepimetheus.data.search](../index.md) / [Track](./index.md)

# Track

`data class Track : `[`PandoraData`](../../tk.hacker1024.libepimetheus.data/-pandora-data/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Track(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, artist: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, musicId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, pandoraId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, artUrls: `[`HashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>)` |

### Properties

| Name | Summary |
|---|---|
| [artUrls](art-urls.md) | `val artUrls: `[`HashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [artist](artist.md) | `val artist: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [name](name.md) | `val name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [getArtUrl](../../tk.hacker1024.libepimetheus.data/-pandora-data/get-art-url.md) | `fun getArtUrl(preferredSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>This function will return the data object's art URL for the given size. If the given size doesn't exist, it will return the URL for the nearest larger size. |
