package com.test.videoplayer.domain.interactor

import com.test.videoplayer.domain.entity.ItemVideo
import com.test.videoplayer.domain.entity.VideoModel
import com.test.videoplayer.domain.enum.VideoStatus
import com.test.videoplayer.domain.repository.VideoRepository
import com.test.videoplayer.util.HttpsTrustManager
import io.reactivex.Single
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class VideoInteractor(
    private val videoRepository: VideoRepository,
    private val httpsTrustManager: HttpsTrustManager
) {

    fun getVideo(): Single<List<ItemVideo>> = videoRepository.getVideo()
        .map { videoResponseModel ->
            if (videoResponseModel.results != null) {
                processVideo(videoResponseModel.results)
            } else {
                throw IllegalArgumentException()
            }
        }

    private fun processVideo(videoModel: VideoModel): List<ItemVideo> {
        return listOf(
            addItemVideo(
                url = videoModel.single,
                previewImage = videoModel.previewImage
            ),
            addItemVideo(
                url = videoModel.splitV,
                previewImage = videoModel.previewImage
            ),
            addItemVideo(
                url = videoModel.splitH,
                previewImage = videoModel.previewImage
            ),
            addItemVideo(
                url = videoModel.src,
                previewImage = videoModel.previewImage
            )
        )
    }

    private fun addItemVideo(url: String, previewImage: String): ItemVideo {
        httpsTrustManager.allowAllSSL()
        return ItemVideo(
            url = url,
            previewImage = previewImage,
            status = processVideoUrlStatus(url)
        )
    }

    private fun processVideoUrlStatus(url: String) =
        try {
            val newUrl = URL(url)
            val connect: HttpURLConnection = newUrl.openConnection() as HttpURLConnection
            connect.connect()
            if (connect.responseCode == HttpURLConnection.HTTP_OK) {
                VideoStatus.SUCCESS
            } else {
                VideoStatus.ERROR_NO_CONNECT
            }
        } catch (e: MalformedURLException) {
            VideoStatus.ERROR_NO_VALID
        } catch (e: IOException) {
            VideoStatus.ERROR_NO_CONNECT
        }
}
