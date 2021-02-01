package com.shuxin.mtxdadmin.base

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shuxin.mtxdadmin.ActivityManager
import com.shuxin.mtxdadmin.MyApplication
import com.shuxin.mtxdadmin.R
import com.shuxin.mtxdadmin.utils.DensityUtil
import com.shuxin.mtxdadmin.utils.EasyToast

/**
 * created by Buzz
 * on 2021/2/1
 * email lmx2060918@126.com
 */
abstract class BaseActivity:AppCompatActivity() {
    override fun getResources(): Resources {
        val res = super.getResources()
        if (res != null) {
            val config: Configuration? = res.configuration
            if (config != null && config.fontScale !== 1f) {
                config.fontScale = 1.0f
                res.updateConfiguration(config, res.displayMetrics)
            }
        }
        return res
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        beforeOnCreate()
        super.onCreate(savedInstanceState)
        ActivityManager.pushActivity(this)
        DensityUtil.setCustomDensity(this, MyApplication.instance())
        setContentView(layoutResID())
        onViewCreated()
        initViewAndEvent()
    }

    open fun onViewCreated(){

    }

    open fun beforeOnCreate(){

    }

    fun toast(content: String?) {
        if (!content.isNullOrEmpty()){
            EasyToast.newBuilder(R.layout.view_toast, R.id.tvToast).build().show(content)
        }
    }

    abstract fun layoutResID(): Int

    abstract fun initViewAndEvent()

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.popActivity(this)
    }
}