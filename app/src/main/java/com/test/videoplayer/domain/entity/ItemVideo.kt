package com.test.videoplayer.domain.entity

import android.os.Parcelable
import com.test.videoplayer.domain.enum.VideoStatus
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemVideo(
    val url: String = "",
    val previewImage: String = "",
    val status: VideoStatus = VideoStatus.ERROR_NO_VALID
) : Parcelable
