[libepimetheus](../../index.md) / [tk.superl2.libepimetheus](../index.md) / [Stations](./index.md)

# Stations

`object Stations`

This singleton contains functions to retrieve station information.
The Pandora station APIs are documented [here](https://6xq.net/pandora-apidoc/rest/stations/).

### Functions

| Name | Summary |
|---|---|
| [getStations](get-stations.md) | `fun getStations(user: `[`User`](../-user/index.md)`, includeShuffle: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true, sortWith: `[`Comparator`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-comparator/index.html)`<`[`Station`](../../tk.superl2.libepimetheus.data/-station/index.md)`>? = Comparator { station1, station2 ->
                when {
                    station1.isShuffle -> -2
                    station2.isShuffle -> 2
                    station1.isThumbprint -> -1
                    station2.isThumbprint -> 1
                    else -> compareValues(station1.name, station2.name)
                }
            }): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Station`](../../tk.superl2.libepimetheus.data/-station/index.md)`>`<br>This function retrieves a list of the users stations. |
