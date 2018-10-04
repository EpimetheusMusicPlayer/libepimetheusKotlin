## libepimetheus

### About

Libepimetheus is an Android library to connect to [Pandora Music](https://www.pandora.com/)'s REST API, documented [here](https://6xq.net/pandora-apidoc/rest/). It's the same API the web app uses. Libepimetheus doesn't enforce ads or skip limits.  
The library can optionally use a free smart DNS service, [Portaller](http://portaller.com/), to access Pandora from outside the US.



### Download [![](https://jitpack.io/v/EpimetheusAndroid/libepimetheus.svg)](https://jitpack.io/#EpimetheusAndroid/libepimetheus)

The library is available through [JitPack](https://jitpack.io/#EpimetheusAndroid/libepimetheus/). Add the JitPack repository to your app module's `build.gradle` file:

```groovy
repositories {
	...
	maven { url 'https://jitpack.io' }
}
```

Then, add the library in the `dependencies` closure.

```groovy
dependencies {
    ...
    implementation 'com.github.EpimetheusAndroid:libepimetheus:1.0'
}
```

Alternatively, you can manually use the library `aar` and sources `jar` from the [releases page](https://github.com/EpimetheusAndroid/libepimetheus/releases).



### Documentation

KDoc, generated in [GFM Markdown](https://github.github.com/gfm/), is [available in the `docs` directory in this repository](https://github.com/EpimetheusAndroid/libepimetheus/blob/master/docs/libepimetheus/index.md).  
Continue reading to get started.



### Get started

To authenticate with Pandora, create a `User` object. The last boolean enables [Portaller](http://portaller.com/), a free smart DNS service, to access Pandora outside the US. `User` objects are `Parcelable`.

```kotlin
val user = User("username", "password", usePortaller = true)
```

Get a list of the user's stations with the `Stations.getStations` function. This will return a list of station objects. `Station` objects are `Parcelable`.

```kotlin
val stations: List<Station> = Stations.getStation(user)
```

Get a station playlist with the `Station.getPlaylist` function. This will return a list of (usually) four song objects.

```kotlin
val playlist: List<Song> = stations[index].getPlaylist(user)
```

To get more songs, call `getPlaylist` again.

```kotlin
playlist += stations[index].getPlaylist(user)
```

`Song` objects have many properties, like:

```kotlin
// Song name, album, artist
song.name; song.album; song.artist

// Audio URI
song.audioUri

// Art URL (returns the URL for the nearest larger existing size)
song.getArtUrl(preferredSize)

// Rating
song.rating
```

Add and remove feedback with the feedback functions:

```kotlin
song.addTired(user)
song.addFeedback(thumbsUp = true, user)
song.deleteFeedback(user)
```



#### Custom API requests

You can use [this function](https://github.com/EpimetheusAndroid/libepimetheus/blob/master/docs/libepimetheus/tk.superl2.libepimetheus/-networking/make-api-request.md) to make a custom API request.