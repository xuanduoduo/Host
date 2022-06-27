package com.chenxuan.base.util

import android.app.ActivityManager
import android.content.Context
import android.os.Process
import android.util.Log

fun Context.getCurProcessName(): String {
    val pid = Process.myPid()
    val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val processName = activityManager.runningAppProcesses.find {
        it.pid == pid
    }?.processName ?: ""
    return processName
}

@JvmOverloads
fun Context.log(methodName: String, methodParam: String? = "") {
    Log.d(
        "chenxuan------>",
        "进程：${getCurProcessName()}，线程：${Thread.currentThread().name}------>Method：$methodName(${methodParam})"
    )
}