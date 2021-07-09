package com.test.videoplayer.di

import com.test.videoplayer.VideoPlayerApp
import com.test.videoplayer.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        AppModule::class,
        NavigationModule::class,
        NetworkModule::class,
        ActivityModule::class,
        FragmentModule::class,
        RepositoryModule::class,
        MappersModule::class,
        InteractorModule::class,
        UtilsModule::class
    ]
)
interface AppComponent : AndroidInjector<VideoPlayerApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: VideoPlayerApp): Builder

        fun build(): AppComponent
    }
}
