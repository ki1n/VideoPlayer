package com.test.videoplayer.di.module

import com.test.videoplayer.data.mapper.VideoResponseMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MappersModule {

    @Singleton
    @Provides
    fun provideVideoResponseMapper() = VideoResponseMapper()
}
