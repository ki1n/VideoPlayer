package com.test.videoplayer.di.module

import com.test.videoplayer.domain.interactor.VideoInteractor
import com.test.videoplayer.domain.repository.VideoRepository
import com.test.videoplayer.util.HttpsTrustManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {

    @Provides
    @Singleton
    fun provideVideoInteractor(
        videoRepository: VideoRepository,
        httpsTrustManager: HttpsTrustManager
    ) = VideoInteractor(videoRepository, httpsTrustManager)
}
