[libepimetheus](../../index.md) / [tk.hacker1024.libepimetheus.data.search](../index.md) / [Listenable](./index.md)

# Listenable

`abstract class Listenable : `[`PandoraData`](../../tk.hacker1024.libepimetheus.data/-pandora-data/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Listenable(defaultArtUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = GENERIC_ART_URL)` |

### Properties

| Name | Summary |
|---|---|
| [listenerCount](listener-count.md) | `abstract val listenerCount: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`?` |

### Inherited Properties

| Name | Summary |
|---|---|
| [artUrls](../../tk.hacker1024.libepimetheus.data/-pandora-data/art-urls.md) | `abstract val artUrls: `[`HashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [name](../../tk.hacker1024.libepimetheus.data/-pandora-data/name.md) | `abstract val name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| Name | Summary |
|---|---|
| [add](add.md) | `fun add(user: `[`User`](../../tk.hacker1024.libepimetheus/-user/index.md)`, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = this.name.trim() + " Radio"): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Adds the listenable to the user's station list. |

### Inherited Functions

| Name | Summary |
|---|---|
| [getArtUrl](../../tk.hacker1024.libepimetheus.data/-pandora-data/get-art-url.md) | `fun getArtUrl(preferredSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>This function will return the data object's art URL for the given size. If the given size doesn't exist, it will return the URL for the nearest larger size. |

### Inheritors

| Name | Summary |
|---|---|
| [Artist](../-artist/index.md) | `data class Artist : `[`Listenable`](./index.md)<br>A data class to hold information about an artist. |
| [GenreStation](../-genre-station/index.md) | `data class GenreStation : `[`Listenable`](./index.md)<br>A data class to hold information about a genre station. |
