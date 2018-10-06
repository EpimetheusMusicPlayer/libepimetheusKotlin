[libepimetheus](../../index.md) / [tk.hacker1024.libepimetheus.data](../index.md) / [Station](index.md) / [getArtUrl](./get-art-url.md)

# getArtUrl

`fun getArtUrl(preferredSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)

This function will return the station's art URL for the given size. If the given size
doesn't exist, it will return the URL for the nearest larger size.

Note: The URLs for the Shuffle station's 130x130, 600x600, and 640x640 logos aren't valid,
    you'll get a 404 error. (At the time of writing this.) Make sure not to use the URLs
    for these sizes of the Shuffle station logo.

### Parameters

`preferredSize` - The preferred size of the art.

**Return**
The art URL for the nearest larger or equal size of the size specified.

