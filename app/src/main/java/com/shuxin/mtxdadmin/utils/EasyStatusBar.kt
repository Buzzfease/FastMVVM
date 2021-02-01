package com.shuxin.mtxdadmin.utils

import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.shuxin.mtxdadmin.base.BaseFragment


/**
 * Created by Buzz on 2020/1/9.
 * Email :lmx2060918@126.com
 */
object EasyStatusBar {

    private var lastVisibility:Int = -18522

    fun makeStatusBarTransparent(activity: AppCompatActivity, isLightBg: Boolean, container: View?, vararg content: View?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && isLightBg) {
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            activity.window.statusBarColor = Color.TRANSPARENT
            //适配刘海
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val lp = activity.window.attributes
                lp.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
                activity.window.attributes = lp
            }
        }

        if (container != null){
            ViewCompat.setOnApplyWindowInsetsListener(container, null)
            ViewCompat.setOnApplyWindowInsetsListener(container) { view, windowInsetsCompat ->
                for (value in content) {
                    if (value != null){
                        setMarginTop(value, windowInsetsCompat.systemWindowInsetTop)
                    }
                }
                windowInsetsCompat.consumeSystemWindowInsets()
            }
        }
    }

    fun makeHomeStatusBarTransparent(activity: AppCompatActivity, isLightBg: Boolean, container: View?, fragments:ArrayList<BaseFragment>){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && isLightBg) {
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            activity.window.statusBarColor = Color.TRANSPARENT
            //适配刘海
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val lp = activity.window.attributes
                lp.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
                activity.window.attributes = lp
            }
        }

        if (container != null){
            ViewCompat.setOnApplyWindowInsetsListener(container) { view, windowInsetsCompat ->
                fragments.forEach {
                    it.setWindowInsertPadding(windowInsetsCompat.systemWindowInsetTop)
                }
                windowInsetsCompat.consumeSystemWindowInsets()
            }
        }
    }

    fun setStatusBarColor(activity: AppCompatActivity, @ColorInt barColor: Int, isLightBg: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                if (isLightBg) {
                    lastVisibility = activity.window.decorView.systemUiVisibility
                    activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }else{
                    if (lastVisibility != -18522)
                        activity.window.decorView.systemUiVisibility = lastVisibility
                }
            }
            activity.window.statusBarColor = barColor
        }
    }

    private fun setPaddingTop(view: View, paddingTop: Int){
        view.setPadding(view.paddingLeft, view.paddingTop + paddingTop, view.paddingRight, view.paddingBottom)
    }

    private fun setMarginTop(view: View, marginTop: Int) {
        val menuLayoutParams = view.layoutParams as MarginLayoutParams
        val ori = menuLayoutParams.topMargin
        if (marginTop != ori){
            menuLayoutParams.setMargins(0, marginTop + ori, 0, 0)
        }
        view.layoutParams = menuLayoutParams
    }

}