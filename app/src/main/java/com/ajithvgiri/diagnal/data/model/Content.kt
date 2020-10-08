package com.ajithvgiri.diagnal.data.model

import com.squareup.moshi.Json
import java.io.Serializable

data class Content(
    @Json(name = "name")
    var name: String,

    @Json(name = "poster_image")
    var poster_image: String
) : Serializable