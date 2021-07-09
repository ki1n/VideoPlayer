package com.test.videoplayer.presentation.video.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.test.videoplayer.R
import com.test.videoplayer.databinding.ItemVideoBinding
import com.test.videoplayer.domain.entity.ItemVideo
import com.test.videoplayer.domain.enum.VideoStatus

fun videoAdapterDelegate(
    onImageClicked: (Int) -> Unit
) = adapterDelegateViewBinding<ItemVideo, ItemVideo, ItemVideoBinding>(
    { layoutInflater, parent ->
        ItemVideoBinding.inflate(layoutInflater, parent, false)
    }
) {
    binding.root.setOnClickListener {
        if (adapterPosition != RecyclerView.NO_POSITION) {
            onImageClicked(adapterPosition)
        }
    }

    fun processVideoStatus(image: Int, description: Int) {
        binding.ivPlay.setImageResource(image)
        binding.tvDescription.text = getString(description)
    }

    bind {
        with(binding) {
            when (item.status) {
                VideoStatus.SUCCESS -> {
                    processVideoStatus(R.drawable.ic_play, R.string.video_success)
                }
                VideoStatus.ERROR_NO_CONNECT -> {
                    itemView.isEnabled = false
                    processVideoStatus(
                        R.drawable.ic_error_video,
                        R.string.video_error_no_connect
                    )
                }
                VideoStatus.ERROR_NO_VALID -> {
                    itemView.isEnabled = false
                    processVideoStatus(
                        R.drawable.ic_error_video,
                        R.string.video_error_no_valid
                    )
                }
            }
            Glide.with(ivPreview.context)
                .asBitmap()
                .placeholder(R.drawable.ic_progress)
                .error(R.drawable.ic_camera)
                .load(item.previewImage)
                .into(ivPreview)
        }
    }
}
