package com.example.kotlintest.features.news.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintest.R
import com.example.kotlintest.commons.adapter.ViewType
import com.example.kotlintest.commons.adapter.ViewTypeDelegateAdapter
import com.example.kotlintest.commons.extensions.inflate

class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.news_item_loading)) {
    }
}