package com.test.videoplayer.presentation.video.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.test.videoplayer.domain.entity.ItemVideo

class VideoAdapter(
    onImageClicked: (ItemVideo) -> Unit
) : AsyncListDifferDelegationAdapter<ItemVideo>(DIFF_CALLBACK) {

    init {
        delegatesManager.addDelegate(
            videoAdapterDelegate {
                onImageClicked(items[it])
            }
        )
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemVideo>() {
            override fun areItemsTheSame(
                oldItem: ItemVideo,
                newItem: ItemVideo
            ) = oldItem.url == newItem.url

            override fun areContentsTheSame(
                oldItem: ItemVideo,
                newItem: ItemVideo
            ) = oldItem == newItem
        }
    }
}
