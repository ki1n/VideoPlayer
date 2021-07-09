package com.test.videoplayer.presentation.videoplayer

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.test.videoplayer.domain.entity.ItemVideo

fun videoPlayerScreen(itemVideo: ItemVideo) = FragmentScreen {
    VideoPlayerFragment.newInstance(itemVideo)
}
