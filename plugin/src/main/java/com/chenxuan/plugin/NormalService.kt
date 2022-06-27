package com.chenxuan.plugin

import android.content.Intent
import com.chenxuan.base.service.PluginService
import com.chenxuan.base.util.log

class NormalService : PluginService() {
    override fun onCreate() {
        super.onCreate()
        context?.log("onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        context?.log("onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }
}