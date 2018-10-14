package tk.hacker1024.libepimetheus.data.search

import tk.hacker1024.libepimetheus.data.GENERIC_ART_URL
import tk.hacker1024.libepimetheus.data.PandoraData

abstract class Listenable(defaultArtUrl: String = GENERIC_ART_URL) : PandoraData(defaultArtUrl) {
    abstract val listenerCount: Int?
}