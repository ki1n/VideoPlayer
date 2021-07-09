package com.test.videoplayer.di.module

import androidx.lifecycle.ViewModel
import com.test.videoplayer.di.ViewModelKey
import com.test.videoplayer.presentation.video.VideoFragment
import com.test.videoplayer.presentation.video.VideoViewModel
import com.test.videoplayer.presentation.videoplayer.VideoPlayerFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun contributeVideoFragment(): VideoFragment

    @Binds
    @IntoMap
    @ViewModelKey(VideoViewModel::class)
    fun bindVideoViewModel(viewModel: VideoViewModel): ViewModel

    @ContributesAndroidInjector
    fun contributeVideoPlayerFragment(): VideoPlayerFragment
}
