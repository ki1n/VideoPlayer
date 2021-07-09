package com.test.videoplayer.presentation.main

import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.test.videoplayer.R
import com.test.videoplayer.databinding.ActivityMainBinding
import com.test.videoplayer.presentation.base.BaseActivity
import com.test.videoplayer.presentation.base.binding.viewBinding
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val navigator by lazy { AppNavigator(this, R.id.container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
        viewModel.onVideoScreen()
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}
