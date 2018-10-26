[libepimetheus](../index.md) / [tk.hacker1024.libepimetheus.data](./index.md)

## Package tk.hacker1024.libepimetheus.data

Data objects - [Station](-station/index.md) and [Song](-song/index.md).

### Types

| Name | Summary |
|---|---|
| [PandoraData](-pandora-data/index.md) | `abstract class PandoraData` |
| [Rateable](-rateable/index.md) | `interface Rateable` |
| [Song](-song/index.md) | `data class Song : `[`PandoraData`](-pandora-data/index.md)`, `[`Rateable`](-rateable/index.md)<br>A data class to hold information about a song. |
| [Station](-station/index.md) | `data class Station : `[`PandoraData`](-pandora-data/index.md)`, `[`Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html)<br>A data class to hold information about a station. |
