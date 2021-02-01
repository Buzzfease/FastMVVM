package com.shuxin.mtxdadmin.di

import com.shuxin.mtxdadmin.utils.glide.GlideCenter
import com.shuxin.mtxdadmin.utils.network.ApiCenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [HttpModule::class, GlideModule::class])
interface AppComponent {
    fun inject(apiCenter: ApiCenter)
    fun inject(glideCenter: GlideCenter)
}