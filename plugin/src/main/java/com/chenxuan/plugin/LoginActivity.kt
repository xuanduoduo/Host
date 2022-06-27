package com.chenxuan.plugin

import android.os.Bundle
import com.chenxuan.base.activity.PluginActivity

class LoginActivity : PluginActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}