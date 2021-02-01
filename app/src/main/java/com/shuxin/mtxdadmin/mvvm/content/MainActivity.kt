package com.shuxin.mtxdadmin.mvvm.content

import android.view.View
import com.shuxin.mtxdadmin.R
import com.shuxin.mtxdadmin.base.BaseVMActivity
import com.shuxin.mtxdadmin.mvvm.view.MainViewState
import com.shuxin.mtxdadmin.mvvm.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.act_main.*

/**
 * created by Buzz
 * on
 * email lmx2060918@126.com
 */
class MainActivity: BaseVMActivity<MainViewState, MainViewModel>() {

    override fun initInject() {
        getActivityComponent().inject(this)
    }

    override fun layoutResID(): Int {
        return R.layout.act_main
    }

    override fun initViewAndEvent() {
        viewModel.getNavigation()


    }

    override fun setViewState(viewState: MainViewState) {
        when (viewState) {
            is MainViewState.Loading -> {
                //show loading if you need
            }
            is MainViewState.GetNavigationSuccess -> {
                viewError.visibility = View.GONE
                var title = ""
                viewState.navigation.data.forEach {
                    title += it.column_name
                }
                tvContent.text = title
            }
            is MainViewState.GetNavigationFailed -> {
                //hide loading if you need
                viewError.visibility = View.VISIBLE
                viewError.setOnClickListener {
                    viewModel.getNavigation()
                }
            }
        }
    }

}