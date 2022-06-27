package com.chenxuan.base.activity

import android.os.Bundle

interface PluginActivityLifecycle {
    fun onCreate(savedInstanceState: Bundle?)
    fun onStart()
    fun onResume()
    fun onRestart()
    fun onPause()
    fun onStop()
    fun onDestroy()
    fun onSaveInstanceState(outState: Bundle)
    fun onRestoreInstanceState(savedInstanceState: Bundle)
}