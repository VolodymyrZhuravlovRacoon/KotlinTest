package com.example.kotlintest.features.news.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintest.R
import com.example.kotlintest.commons.RedditNewsItem
import com.example.kotlintest.commons.adapter.ViewType
import com.example.kotlintest.commons.adapter.ViewTypeDelegateAdapter
import com.example.kotlintest.commons.extensions.getFriendlyTime
import com.example.kotlintest.commons.extensions.inflate
import com.example.kotlintest.commons.extensions.loadImg
import kotlinx.android.synthetic.main.news_item.view.*

class NewsDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return TurnsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as TurnsViewHolder
        holder.bind(item as RedditNewsItem)
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.news_item)) {

        fun bind(item: RedditNewsItem) = with(itemView) {
            //Picasso.with(itemView.context).load(item.thumbnail).into(img_thumbnail)
            item.thumbnail?.let { img_thumbnail.loadImg(it) }
            description.text = item.title
            author.text = item.author
            comments.text = "${item.numComments} comments"
            time.text = item.created.getFriendlyTime()
        }
    }
}