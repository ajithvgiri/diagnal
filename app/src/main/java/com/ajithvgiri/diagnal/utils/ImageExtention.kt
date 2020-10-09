package com.ajithvgiri.diagnal.utils

import android.widget.ImageView
import com.ajithvgiri.diagnal.R

fun ImageView.loadPoster(posterImage: String) {
    when(posterImage){
        "poster1.jpg"-> this.setImageResource(R.drawable.poster1)
        "poster2.jpg"-> this.setImageResource(R.drawable.poster2)
        "poster3.jpg"-> this.setImageResource(R.drawable.poster3)
        "poster4.jpg"-> this.setImageResource(R.drawable.poster4)
        "poster5.jpg"-> this.setImageResource(R.drawable.poster5)
        "poster6.jpg"-> this.setImageResource(R.drawable.poster6)
        "poster7.jpg"-> this.setImageResource(R.drawable.poster7)
        "poster8.jpg"-> this.setImageResource(R.drawable.poster8)
        "poster9.jpg"-> this.setImageResource(R.drawable.poster9)
    }

}