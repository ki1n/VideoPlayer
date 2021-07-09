package com.test.videoplayer.presentation.videoplayer

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.test.videoplayer.R
import com.test.videoplayer.databinding.FragmentVideoPlayerBinding
import com.test.videoplayer.domain.entity.ItemVideo
import com.test.videoplayer.presentation.base.BaseFragment
import com.test.videoplayer.presentation.base.binding.viewBinding
import com.test.videoplayer.presentation.extension.viewModelProviderFactoryOf
import com.test.videoplayer.util.HttpsTrustManager
import javax.inject.Inject

class VideoPlayerFragment : BaseFragment<VideoPlayerViewModel>(R.layout.fragment_video_player) {

    @Inject
    lateinit var assistedFactory: VideoPlayerAssistedFactory

    @Inject
    lateinit var httpsTrustManager: HttpsTrustManager

    override val viewModelFactory: ViewModelProvider.Factory
        get() = viewModelProviderFactoryOf {
            assistedFactory.create(
                payload
            )
        }

    private val payload by lazy {
        requireArguments().getParcelable<ItemVideo>(ARGUMENT_PAYLOAD)!!
    }

    private val binding by viewBinding(FragmentVideoPlayerBinding::bind)

    private lateinit var player: SimpleExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        player = SimpleExoPlayer.Builder(requireContext()).build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            binding.pvVideoPlayer.player = player
            ivClose.setOnClickListener {
                viewModel.onEvent(VideoPlayerView.Event.OnCloseClicked)
            }
        }
        subscribe(viewModel.stateLiveData, ::handleState)
        subscribe(viewModel.uiLabelsLiveEvent, ::handleUiLabel)
    }

    override fun onStart() {
        super.onStart()
        viewModel.onEvent(VideoPlayerView.Event.FragmentOnStart)
    }

    override fun onResume() {
        super.onResume()
        viewModel.onEvent(VideoPlayerView.Event.FragmentOnResume)
    }

    override fun onPause() {
        viewModel.onEvent(
            VideoPlayerView.Event.FragmentOnPause(
                player.currentPosition,
                player.isPlaying
            )
        )
        super.onPause()
    }

    override fun onDestroyView() {
        binding.pvVideoPlayer.player = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        player.release()
        super.onDestroy()
    }

    private fun handleState(state: VideoPlayerView.Model) = state.run {
        player.seekTo(currentPlayerPosition)
        setPlayer(url)
    }

    private fun handleUiLabel(uiLabel: VideoPlayerView.UiLabel) = when (uiLabel) {
        is VideoPlayerView.UiLabel.SetPlayer -> setPlayer(uiLabel.videoUrl)
        VideoPlayerView.UiLabel.PreparePlayer -> player.prepare()
        is VideoPlayerView.UiLabel.PlayOrPause -> playOrPause(uiLabel.shouldPlay)
        VideoPlayerView.UiLabel.PausePlayer -> player.pause()
    }

    private fun setPlayer(videoUrl: String) {
        httpsTrustManager.allowAllSSL()
        player.setMediaItem(MediaItem.fromUri(videoUrl))
        player.addListener(object : Player.Listener {
            override fun onPlayerError(error: ExoPlaybackException) {
                showError(R.string.error_video)
            }
        })
    }

    private fun playOrPause(shouldPlay: Boolean) {
        if (shouldPlay) {
            player.playWhenReady = true
        } else {
            player.pause()
        }
    }

    companion object {
        private const val ARGUMENT_PAYLOAD = "payload"

        fun newInstance(payload: ItemVideo) = VideoPlayerFragment().apply {
            arguments = bundleOf(ARGUMENT_PAYLOAD to payload)
        }
    }
}
