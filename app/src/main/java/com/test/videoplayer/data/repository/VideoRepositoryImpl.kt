package com.test.videoplayer.data.repository

import com.test.videoplayer.data.ApiService
import com.test.videoplayer.data.mapper.VideoResponseMapper
import com.test.videoplayer.domain.repository.VideoRepository

class VideoRepositoryImpl(
    private val apiService: ApiService,
    private val videoResponseMapper: VideoResponseMapper
) : VideoRepository {

    override fun getVideo() = apiService.getVideo()
        .map(videoResponseMapper::mapToDomain)
}
