package com.shuxin.mtxdadmin.di

import com.shuxin.mtxdadmin.base.BaseVMActivity
import com.shuxin.mtxdadmin.mvvm.content.MainActivity
import com.shuxin.mtxdadmin.mvvm.content.SplashActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class])
interface ActivityComponent {
    fun inject(splashActivity: SplashActivity)
    fun inject(mainActivity: MainActivity)
}