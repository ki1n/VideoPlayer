package com.test.videoplayer.di.module

import androidx.lifecycle.ViewModel
import com.test.videoplayer.di.ViewModelKey
import com.test.videoplayer.presentation.main.MainActivity
import com.test.videoplayer.presentation.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [FragmentModule::class])
interface ActivityModule {

    @ContributesAndroidInjector()
    fun bindMainActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}
