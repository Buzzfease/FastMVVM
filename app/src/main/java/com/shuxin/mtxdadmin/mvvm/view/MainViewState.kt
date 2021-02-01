package com.shuxin.mtxdadmin.mvvm.view

import com.shuxin.mtxdadmin.base.BaseViewState
import com.shuxin.mtxdadmin.mvvm.model.Navigation

/**
 * created by Buzz
 * on
 * email lmx2060918@126.com
 */
sealed class MainViewState:BaseViewState {
    object Loading: MainViewState()
    data class GetNavigationFailed(val message: String?) : MainViewState()
    data class GetNavigationSuccess(val navigation:  Navigation) : MainViewState()
}