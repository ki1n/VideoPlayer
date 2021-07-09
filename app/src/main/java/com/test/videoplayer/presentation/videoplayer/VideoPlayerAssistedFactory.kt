package com.test.videoplayer.presentation.videoplayer

import com.test.videoplayer.domain.entity.ItemVideo
import dagger.assisted.AssistedFactory

@AssistedFactory
interface VideoPlayerAssistedFactory {
    fun create(itemVideo: ItemVideo): VideoPlayerViewModel
}
