package com.ajithvgiri.diagnal.data.model

import com.squareup.moshi.Json
import java.io.Serializable


data class Response(
    @Json(name = "page")
    var page: Page
):Serializable

data class Page(
    @Json(name = "title")
    var title: String,

    @Json(name = "total_content_items")
    var total_content_items: String,

    @Json(name = "page_num")
    var page_num: String,

    @Json(name = "page_size")
    var page_size: String,

    @Json(name = "content_items")
    var content_items: ContentItems
):Serializable

data class ContentItems(
    @Json(name = "content")
    var content: List<Content>
):Serializable