package com.shuxin.mtxdadmin.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * created by Buzz
 * on 2021/2/1
 * email lmx2060918@126.com
 */
abstract class BaseFragment:Fragment() {
    private lateinit var mView: View
    private var mContext: Context? = null
    private var isViewCreated:Boolean = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        beforeOnCreate()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (layoutResID() != 0) {
            mView = inflater.inflate(layoutResID(), container, false)
            mView
        } else {
            super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewCreated = true
        initEventView()
    }

    abstract fun beforeOnCreate()

    abstract fun initEventView()

    abstract fun layoutResID(): Int


    open fun setWindowInsertPadding(paddingTop: Int){
        if (view != null && view!!.paddingTop != paddingTop){
            view!!.setPadding(view!!.paddingLeft, view!!.paddingTop + paddingTop, view!!.paddingRight, view!!.paddingBottom)
        }
    }
}