package com.test.videoplayer.di.module

import com.test.videoplayer.VideoPlayerApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule() {

    @Provides
    @Singleton
    fun provideContext(application: VideoPlayerApp) = application.applicationContext!!
}
