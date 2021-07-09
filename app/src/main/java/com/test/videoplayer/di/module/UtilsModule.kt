package com.test.videoplayer.di.module

import com.test.videoplayer.util.HttpsTrustManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilsModule {

    @Provides
    @Singleton
    fun provideHttpsTrustManager() = HttpsTrustManager()
}
