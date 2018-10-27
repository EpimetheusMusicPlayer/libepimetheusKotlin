package tk.hacker1024.libepimetheus.data

import android.support.v4.media.RatingCompat

abstract class Rateable : PandoraData() {
    internal abstract var feedbackId: String
    abstract var rating: RatingCompat; internal set
    abstract var settingFeedback: RatingCompat; internal set
}