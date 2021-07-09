package com.test.videoplayer.domain.entity

data class VideoResponseModel(
    val a: String = "",
    val taskId: Long = 0,
    val status: Int = 0,
    val results: VideoModel? = null
)
