package com.shuxin.mtxdadmin.utils.network

import androidx.annotation.MainThread
import com.shuxin.mtxdadmin.MyApplication
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Buzz on 2019/7/1.
 * Email :lmx2060918@126.com
 */
class ApiCenter{
    companion object {
        private var mInstance: ApiCenter? = null
        @MainThread
        fun get(): ApiCenter {
            if (mInstance == null) {
                mInstance = ApiCenter()
            }
            return mInstance!!
        }
    }

    init {
        MyApplication.appComponent?.inject(this)
    }


    @Inject
    @field:Named("default")
    lateinit var resApi: ResApi

    @Inject
    @field:Named("shop")
    lateinit var shopApi: ResApi

    @Inject
    @field:Named("movie")
    lateinit var movieApi:ResApi
}