package com.test.videoplayer.presentation.video

import androidx.lifecycle.LiveData
import com.github.terrakok.cicerone.Router
import com.test.videoplayer.domain.entity.ItemVideo
import com.test.videoplayer.domain.interactor.VideoInteractor
import com.test.videoplayer.presentation.base.BaseViewModel
import com.test.videoplayer.presentation.base.SingleLiveEvent
import com.test.videoplayer.presentation.videoplayer.videoPlayerScreen
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class VideoViewModel @Inject constructor(
    private val videoInteractor: VideoInteractor,
    private val router: Router
) : BaseViewModel() {

    private val _itemsVideoLiveEvent = SingleLiveEvent<List<ItemVideo>>()
    val itemsVideoLiveEvent: LiveData<List<ItemVideo>> = _itemsVideoLiveEvent

    fun onVideoPlayerScreen(itemVideo: ItemVideo) = router.navigateTo(videoPlayerScreen(itemVideo))

    private fun getVideo() = disposeOnCleared(
        videoInteractor.getVideo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading() }
            .doFinally { hideLoading() }
            .subscribe(::processVideo, ::processError)
    )

    private fun processVideo(itemsVideo: List<ItemVideo>) {
        _itemsVideoLiveEvent.setValue(itemsVideo)
    }

    init {
        getVideo()
    }
}
