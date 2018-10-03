[libepimetheus](../../index.md) / [tk.superl2.libepimetheus](../index.md) / [Stations](index.md) / [getStations](./get-stations.md)

# getStations

`fun getStations(user: `[`User`](../-user/index.md)`, includeShuffle: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true, sortWith: `[`Comparator`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparator/index.html)`<`[`Station`](../../tk.superl2.libepimetheus.data/-station/index.md)`>? = Comparator { station1, station2 ->
                when {
                    station1.isShuffle -> -2
                    station2.isShuffle -> 2
                    station1.isThumbprint -> -1
                    station2.isThumbprint -> 1
                    else -> compareValues(station1.name, station2.name)
                }
            }): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Station`](../../tk.superl2.libepimetheus.data/-station/index.md)`>`

This function retrieves a list of the users stations.

### Parameters

`user` - The [User](../-user/index.md) object to authenticate with.

`includeShuffle` - Whether to include the Shuffle station in the list or not.

`sortWith` - A comparator to sort the list with. Can be null. The default comparator
    sorts in alphabetical order, with the Shuffle and Thumbprint stations at
    the top.

**Return**
A list of [Station](../../tk.superl2.libepimetheus.data/-station/index.md) objects.

