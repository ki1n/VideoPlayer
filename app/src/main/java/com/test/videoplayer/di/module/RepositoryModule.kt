package com.test.videoplayer.di.module

import com.test.videoplayer.data.ApiService
import com.test.videoplayer.data.mapper.VideoResponseMapper
import com.test.videoplayer.data.repository.VideoRepositoryImpl
import com.test.videoplayer.domain.repository.VideoRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCartRemoteSource(
        apiService: ApiService,
        videoResponseMapper: VideoResponseMapper
    ): VideoRepository = VideoRepositoryImpl(apiService, videoResponseMapper)
}
