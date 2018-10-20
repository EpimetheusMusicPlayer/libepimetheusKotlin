package tk.hacker1024.libepimetheus.data.search

import tk.hacker1024.libepimetheus.User
import tk.hacker1024.libepimetheus.data.GENERIC_ART_URL
import tk.hacker1024.libepimetheus.data.PandoraData
import tk.hacker1024.libepimetheus.add

abstract class Listenable(defaultArtUrl: String = GENERIC_ART_URL) : PandoraData(defaultArtUrl) {
    internal abstract val pandoraId: String
    abstract val listenerCount: Int?

    /**
     * @see tk.hacker1024.libepimetheus.add
     */
    fun add(user: User, name: String = this.name.trim() + " Radio") {
        add(this, user, name)
    }
}