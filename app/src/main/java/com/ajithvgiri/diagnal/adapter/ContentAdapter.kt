package com.ajithvgiri.diagnal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ajithvgiri.diagnal.R
import com.ajithvgiri.diagnal.data.model.Content
import com.ajithvgiri.diagnal.utils.loadPoster
import kotlinx.android.synthetic.main.layout_content_adapter.view.*
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class ContentAdapter : PagingDataAdapter<Content, ContentAdapter.ContentViewHolder>(diffCallback) {
    companion object {
        /**
         * This diff callback informs the PagedListAdapter how to compute list differences when new
         * PagedLists arrive.
         */
        private val diffCallback = object : DiffUtil.ItemCallback<Content>() {
            override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean =
                oldItem.equals(newItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return ContentViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_content_adapter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun getItemViewType(position: Int): Int = position

    class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(content: Content) {
            itemView.textViewTitle.text = content.name
            itemView.imageView.loadPoster(content.poster_image)
        }
    }

}