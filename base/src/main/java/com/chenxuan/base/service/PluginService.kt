package com.chenxuan.base.service

import android.app.Service.START_STICKY
import android.content.Context
import android.content.Intent
import android.os.IBinder

open class PluginService : PluginServiceLifecycle {
    private var host: HostService? = null

    protected val context: Context?
        get() = host

    fun bindHost(host: HostService) {
        this.host = host
    }

    override fun onCreate() {

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return false
    }
}