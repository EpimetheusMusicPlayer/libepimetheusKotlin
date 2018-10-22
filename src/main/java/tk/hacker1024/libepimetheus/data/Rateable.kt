package tk.hacker1024.libepimetheus.data

import android.support.v4.media.RatingCompat

interface Rateable {
    var feedbackId: String
    var rating: RatingCompat
    var settingFeedback: RatingCompat
}