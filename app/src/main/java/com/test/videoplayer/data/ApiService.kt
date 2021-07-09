package com.test.videoplayer.data

import com.test.videoplayer.data.response.BaseResponse
import com.test.videoplayer.data.response.VideoResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("test/item")
    fun getVideo(): Single<BaseResponse<VideoResponse>>
}
