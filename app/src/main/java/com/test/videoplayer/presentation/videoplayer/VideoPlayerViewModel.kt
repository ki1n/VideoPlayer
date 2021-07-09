package com.test.videoplayer.presentation.videoplayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.terrakok.cicerone.Router
import com.test.videoplayer.domain.entity.ItemVideo
import com.test.videoplayer.presentation.base.BaseViewModel
import com.test.videoplayer.presentation.base.SingleLiveEvent
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class VideoPlayerViewModel @AssistedInject constructor(
    private val router: Router,
    @Assisted private val payload: ItemVideo
) : BaseViewModel() {

    private val _stateLiveData = MutableLiveData<VideoPlayerView.Model>()
    val stateLiveData: LiveData<VideoPlayerView.Model> get() = _stateLiveData

    private val _uiLabels = SingleLiveEvent<VideoPlayerView.UiLabel>()
    val uiLabelsLiveEvent: LiveData<VideoPlayerView.UiLabel> get() = _uiLabels

    private var uiState = VideoPlayerView.Model()
        set(value) {
            field = value
            _stateLiveData.value = value
        }

    private var shouldPlay: Boolean = true

    fun onEvent(event: VideoPlayerView.Event) = when (event) {
        VideoPlayerView.Event.OnCloseClicked -> onCloseClicked()
        VideoPlayerView.Event.FragmentOnStart -> _uiLabels.setValue(VideoPlayerView.UiLabel.PreparePlayer)
        VideoPlayerView.Event.FragmentOnResume -> _uiLabels.setValue(
            VideoPlayerView.UiLabel.PlayOrPause(
                shouldPlay
            )
        )
        is VideoPlayerView.Event.FragmentOnPause -> processFragmentOnPause(event.isPlaying)
        is VideoPlayerView.Event.FragmentOnSaveInstanceState ->
            uiState = uiState.copy(currentPlayerPosition = event.currentPlayerPosition)
    }

    private fun onCloseClicked() {
        router.exit()
    }

    private fun initData() {
        uiState = uiState.copy(
            currentPlayerPosition = START_PLAYER_POSITION,
            url = payload.url
        )
    }

    private fun processFragmentOnPause(isPlaying: Boolean) {
        shouldPlay = isPlaying
        _uiLabels.setValue(VideoPlayerView.UiLabel.PausePlayer)
    }

    init {
        initData()
    }

    companion object {
        private const val START_PLAYER_POSITION = 0L
    }
}
