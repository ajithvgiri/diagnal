package com.ajithvgiri.diagnal.adapter

import androidx.paging.PagingSource
import com.ajithvgiri.diagnal.data.model.Content
import com.ajithvgiri.diagnal.network.RetrofitApi
import java.io.IOException
import javax.inject.Inject

class ContentDataSourceClass @Inject constructor(private val retrofitApi: RetrofitApi) :
    PagingSource<Int, Content>() {

    private val initialPageIndex: Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Content> {
        val position = params.key ?: initialPageIndex
        if (position > 3) {
            return LoadResult.Error(Exception("size exceed"))
        } else {
            return try {
                val response = retrofitApi.fetchContents(position).await()
                val items = response.body()?.page?.content_items
                LoadResult.Page(
                    data = items!!.content,
                    prevKey = if (position == initialPageIndex) null else position - 1,
                    nextKey = if (items.content.isEmpty()) null else position + 1
                )
            } catch (exception: IOException) {
                return LoadResult.Error(exception)
            } catch (exception: Exception) {
                LoadResult.Error(exception)
            }
        }
    }
}
