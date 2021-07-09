package com.test.videoplayer.presentation.video

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.test.videoplayer.R
import com.test.videoplayer.databinding.FragmentVideoBinding
import com.test.videoplayer.domain.entity.ItemVideo
import com.test.videoplayer.presentation.base.BaseFragment
import com.test.videoplayer.presentation.base.binding.viewBinding
import com.test.videoplayer.presentation.video.adapter.VideoAdapter

class VideoFragment : BaseFragment<VideoViewModel>(R.layout.fragment_video) {

    private val binding by viewBinding(FragmentVideoBinding::bind)

    private val videoAdapter by lazy {
        VideoAdapter { itemVideo ->
            viewModel.onVideoPlayerScreen(itemVideo)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            vpVideo.adapter = videoAdapter
            TabLayoutMediator(indicatorVideo, vpVideo) { _, _ ->
                // Ignore
            }.attach()
        }

        subscribe(viewModel.itemsVideoLiveEvent, ::handleItemsVideo)
    }

    private fun handleItemsVideo(itemsVideo: List<ItemVideo>) {
        videoAdapter.items = itemsVideo
    }

    companion object {
        fun newInstance() = VideoFragment()
    }
}
