package com.shuxin.mtxdadmin.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * created by Buzz
 * on
 * email lmx2060918@126.com
 */
abstract class BaseViewModel <T:BaseViewState> : ViewModel(){
    val mLiveData: MutableLiveData<T> = MutableLiveData()

    fun getViewModelLiveData(): LiveData<T> {
        return mLiveData
    }
}