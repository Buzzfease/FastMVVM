package com.shuxin.mtxdadmin.mvvm.viewmodel

import androidx.lifecycle.viewModelScope
import com.shuxin.mtxdadmin.base.BaseViewModel
import com.shuxin.mtxdadmin.mvvm.view.MainViewState
import com.shuxin.mtxdadmin.utils.network.ApiCenter
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * created by Buzz
 * on 2021/2/1
 * email lmx2060918@126.com
 */
class MainViewModel  @Inject
constructor() : BaseViewModel<MainViewState>() {

    fun getNavigation(){
        mLiveData.value = MainViewState.Loading
        viewModelScope.launch(navigationExceptionHandler) {
            val response = ApiCenter.get().movieApi.getNavigationColumn()
            mLiveData.value = MainViewState.GetNavigationSuccess(response)
        }
    }


    private val navigationExceptionHandler = CoroutineExceptionHandler { _, exception ->
        mLiveData.value = MainViewState.GetNavigationFailed(exception.message)
        Timber.e(exception)
    }
}