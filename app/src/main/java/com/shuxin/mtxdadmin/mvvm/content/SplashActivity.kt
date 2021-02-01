package com.shuxin.mtxdadmin.mvvm.content

import android.content.Intent
import com.shuxin.mtxdadmin.R
import com.shuxin.mtxdadmin.base.BaseVMActivity
import com.shuxin.mtxdadmin.mvvm.view.SplashViewState
import com.shuxin.mtxdadmin.mvvm.viewmodel.SplashViewModel
import com.shuxin.mtxdadmin.utils.EasyStatusBar
import kotlinx.android.synthetic.main.act_splash.*

/**
 * created by Buzz
 * on
 * email lmx2060918@126.com
 */
class SplashActivity: BaseVMActivity<SplashViewState, SplashViewModel>() {

    override fun initInject() {
        getActivityComponent().inject(this)
    }

    override fun layoutResID(): Int {
        return R.layout.act_splash
    }

    override fun initViewAndEvent() {
        EasyStatusBar.makeStatusBarTransparent(this, false, rl_splash_bg, null)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun setViewState(viewState: SplashViewState) {

    }
}