package com.test.videoplayer.presentation.main

import com.github.terrakok.cicerone.Router
import com.test.videoplayer.presentation.base.BaseViewModel
import com.test.videoplayer.presentation.video.videoScreen
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val router: Router
) : BaseViewModel() {

    fun onVideoScreen() = router.newRootScreen(videoScreen())
}
