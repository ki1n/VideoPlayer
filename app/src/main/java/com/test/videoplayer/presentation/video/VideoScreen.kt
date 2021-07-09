package com.test.videoplayer.presentation.video

import com.github.terrakok.cicerone.androidx.FragmentScreen

fun videoScreen() = FragmentScreen {
    VideoFragment.newInstance()
}
