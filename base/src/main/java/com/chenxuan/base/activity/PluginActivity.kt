package com.chenxuan.base.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes

open class PluginActivity : PluginActivityLifecycle {
    private var host: HostActivity? = null

    protected val context: Context?
        get() = host

    fun bindHost(host: HostActivity) {
        this.host = host
    }

    override fun onCreate(savedInstanceState: Bundle?) {
    }

    override fun onStart() {
    }

    override fun onResume() {
    }

    override fun onRestart() {
    }

    override fun onPause() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
    }

    override fun onSaveInstanceState(outState: Bundle) {
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
    }

    fun setContentView(@LayoutRes layoutResID: Int) {
        host?.setHostContentView(layoutResID)
    }

    fun setContentView(view: View) {
        host?.setHostContentView(view)
    }
}