package com.shuxin.mtxdadmin.base

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shuxin.mtxdadmin.MyApplication
import com.shuxin.mtxdadmin.di.ActivityComponent
import com.shuxin.mtxdadmin.di.DaggerActivityComponent
import javax.inject.Inject

/**
 * created by Buzz
 * on 2021/2/1
 * email lmx2060918@126.com
 */
abstract class BaseVMActivity<K:BaseViewState, T: BaseViewModel<K>>:BaseActivity() {
    @Inject
    lateinit var viewModel:T

    override fun onViewCreated(){
        initInject()
        viewModel = ViewModelProviders.of(this).get(viewModel::class.java)
        viewModel.getViewModelLiveData().observe(this, Observer { setViewState(it) })
    }

    abstract fun setViewState(viewState : K)


    protected fun getActivityComponent(): ActivityComponent {
        return DaggerActivityComponent.builder()
            .appComponent(MyApplication.appComponent)
            .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.getViewModelLiveData().removeObservers(this)
    }

    protected abstract fun initInject()
}