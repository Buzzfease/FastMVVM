package com.shuxin.mtxdadmin

import android.app.Activity
import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.shuxin.mtxdadmin.MyApplication.Companion.instance
import java.util.*

object ActivityManager {
    private var activityStack: Stack<Activity>? = null// activity栈

    // 获取栈顶的activity，先进后出原则
    private fun getLastActivity(): Activity? {
        return activityStack?.lastElement()
    }


    // 把一个activity压入栈中
    fun pushActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }

    // 移除一个activity
    fun popActivity(activity: Activity) {
        if (activityStack != null && activityStack!!.size > 0) {
            activity.finish()
            activityStack!!.remove(activity)
        }
    }

    // 退出所有activity
    fun finishAllActivity() {
        if (activityStack != null) {
            while (activityStack?.size!! > 0) {
                val activity = getLastActivity() ?: break
                popActivity(activity)
            }
        }
    }

    /**
     * 程序是否在前台运行
     */
    fun isAppOnForeground(): Boolean {
        val activityManager = instance()
            .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val packageName = instance().packageName
        val appProcesses =
            activityManager.runningAppProcesses ?: return false
        for (appProcess in appProcesses) {
            if (appProcess.processName == packageName && appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true // 程序运行在前台
            }
        }
        return false
    }

    fun getProductFlavor(context: Context):String?{
        return try {
            val appInfo: ApplicationInfo = context.packageManager!!.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
            appInfo.metaData.getString("FLAVOR_ID")
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}