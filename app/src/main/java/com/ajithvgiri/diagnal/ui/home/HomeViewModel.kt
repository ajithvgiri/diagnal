package com.ajithvgiri.diagnal.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ajithvgiri.diagnal.adapter.ContentDataSourceClass
import com.ajithvgiri.diagnal.data.model.Content
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val contentDataSourceClass: ContentDataSourceClass) : ViewModel(){

    // for Kotlin Coroutines ---
    val contentData = Pager(PagingConfig(pageSize = 20),pagingSourceFactory = {contentDataSourceClass}).flow.cachedIn(viewModelScope)
}
