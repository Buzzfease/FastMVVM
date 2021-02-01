package com.shuxin.mtxdadmin

import android.app.Application
import com.shuxin.mtxdadmin.di.HttpModule
import com.shuxin.mtxdadmin.di.AppComponent
import com.shuxin.mtxdadmin.di.DaggerAppComponent
import com.shuxin.mtxdadmin.di.GlideModule

import timber.log.Timber


/**
 * Created by Buzz on 2019/4/23.
 * Email :lmx2060918@126.com
 */
class MyApplication : Application() {

    companion object {
        var appChannelParam:Int = FinalParams.AUDIT_MODE_ON
        var appComponent: AppComponent? = null
        private var instance: Application? = null
        fun  instance() = instance!!
    }

    init{
        initDependency()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        //开启多任务栈崩溃处理
        CrashCollectHandler.instance.init(this)


        //开启关闭Log
        if (Constant.getIsShowLog()) {
            Timber.plant(Timber.DebugTree())
        }

    }

    private fun initDependency(){
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .httpModule(HttpModule())
                    .glideModule(GlideModule())
                    .build()
        }
    }
}