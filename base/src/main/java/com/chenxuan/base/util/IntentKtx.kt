package com.chenxuan.base.util

import android.app.Activity
import android.content.Intent
import android.content.ServiceConnection
import com.chenxuan.base.activity.HostActivity
import com.chenxuan.base.service.LocalHostService
import com.chenxuan.base.service.RemoteHostService

fun Activity.jumpPluginActivity(activityName: String, pluginName: String? = "") {
    startActivity(Intent(this, HostActivity::class.java).apply {
        putExtra("ActivityName", activityName)
        putExtra("PluginName", pluginName)
    })
}

fun Activity.startLocalPluginService(
    serviceName: String,
) {
    startService(Intent(this, LocalHostService::class.java).apply {
        putExtra("ServiceName", serviceName)
    })
}

fun Activity.bindLocalPluginService(
    serviceName: String,
    conn: ServiceConnection,
    flags: Int
) {
    bindService(Intent(this, LocalHostService::class.java).apply {
        putExtra("ServiceName", serviceName)
    }, conn, flags)
}

fun Activity.bindRemotePluginService(
    serviceName: String,
    conn: ServiceConnection,
    flags: Int
) {
    bindService(Intent(this, RemoteHostService::class.java).apply {
        putExtra("ServiceName", serviceName)
    }, conn, flags)
}