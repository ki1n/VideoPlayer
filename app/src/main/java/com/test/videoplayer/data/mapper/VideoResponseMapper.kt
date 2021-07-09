package com.test.videoplayer.data.mapper

import com.test.videoplayer.data.response.BaseResponse
import com.test.videoplayer.data.response.VideoResponse
import com.test.videoplayer.domain.entity.VideoModel
import com.test.videoplayer.domain.entity.VideoResponseModel

class VideoResponseMapper {

    fun mapToDomain(response: BaseResponse<VideoResponse>) = response.run {
        VideoResponseModel(
            a = a.orEmpty(),
            taskId = taskId ?: 0,
            status = status ?: 0,
            results = results?.toDomain()
        )
    }

    private fun VideoResponse.toDomain() = run {
        VideoModel(
            single = single.orEmpty(),
            splitV = splitV.orEmpty(),
            splitH = splitH.orEmpty(),
            src = src.orEmpty(),
            previewImage = previewImage.orEmpty(),
        )
    }
}
