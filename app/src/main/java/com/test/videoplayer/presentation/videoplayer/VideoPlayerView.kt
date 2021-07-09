package com.test.videoplayer.presentation.videoplayer

import com.test.videoplayer.presentation.base.BaseView

interface VideoPlayerView : BaseView<VideoPlayerView.Model, VideoPlayerView.Event> {

    data class Model(
        val currentPlayerPosition: Long = 0L,
        val url: String = ""
    )

    sealed class Event {
        object OnCloseClicked : Event()
        object FragmentOnStart : Event()
        object FragmentOnResume : Event()
        data class FragmentOnSaveInstanceState(val currentPlayerPosition: Long) : Event()
        data class FragmentOnPause(val currentPlayerPosition: Long, val isPlaying: Boolean) :
            Event()
    }

    sealed class UiLabel {
        object PreparePlayer : UiLabel()
        object PausePlayer : UiLabel()
        data class SetPlayer(val videoUrl: String) : UiLabel()
        data class PlayOrPause(val shouldPlay: Boolean) : UiLabel()
    }
}
