package com.test.videoplayer.domain.repository

import com.test.videoplayer.domain.entity.VideoResponseModel
import io.reactivex.Single

interface VideoRepository {

    fun getVideo(): Single<VideoResponseModel>
}
