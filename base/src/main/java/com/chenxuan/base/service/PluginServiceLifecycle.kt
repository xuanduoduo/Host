package com.chenxuan.base.service

import android.content.Intent
import android.os.IBinder

interface PluginServiceLifecycle {
    fun onCreate()
    fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    fun onBind(intent: Intent?): IBinder?
    fun onUnbind(intent: Intent?): Boolean
}