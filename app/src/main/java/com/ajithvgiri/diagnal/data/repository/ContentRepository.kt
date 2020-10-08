package com.ajithvgiri.diagnal.data.repository

import com.ajithvgiri.diagnal.data.model.Content
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class ContentRepository @Inject constructor() {

    fun fetchContents(page: Int): ArrayList<Content> {
        return ArrayList<Content>()
    }
}