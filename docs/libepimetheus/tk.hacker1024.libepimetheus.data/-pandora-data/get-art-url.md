[libepimetheus](../../index.md) / [tk.hacker1024.libepimetheus.data](../index.md) / [PandoraData](index.md) / [getArtUrl](./get-art-url.md)

# getArtUrl

`fun getArtUrl(preferredSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)

This function will return the data object's art URL for the given size. If the given size
doesn't exist, it will return the URL for the nearest larger size.

### Parameters

`preferredSize` - The preferred size of the art.

**Return**
The art URL for the nearest larger or equal size of the size specified.

