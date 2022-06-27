package com.chenxuan.base.service

import android.app.Service
import android.content.Intent
import android.content.res.AssetManager
import android.content.res.Resources
import android.os.IBinder
import com.chenxuan.base.PluginClassLoader
import java.io.File

abstract class HostService : Service() {
    private var pluginClassLoader: PluginClassLoader? = null
    private var pluginService: PluginService? = null

    private var apkPath: String? = null
    private var pluginResources: Resources? = null

    override fun onCreate() {
        super.onCreate()
        pluginService?.onCreate()
    }

    private fun initCurrentService(intent: Intent?) {
        apkPath = "${cacheDir.absolutePath}${File.separator}plugin-debug.apk"
        pluginClassLoader = PluginClassLoader(
            dexPath = apkPath ?: "",
            optimizedDirectory = cacheDir.absolutePath,
            librarySearchPath = null,
            classLoader
        )
        val serviceName = intent?.getStringExtra("ServiceName") ?: ""
        pluginService = pluginClassLoader?.loadService(serviceName, this)
    }

    private fun initServiceResource() {
        try {
            val pluginAssetManager = AssetManager::class.java.newInstance()
            val addAssetPathMethod = pluginAssetManager.javaClass
                .getMethod("addAssetPath", String::class.java)
            addAssetPathMethod.invoke(pluginAssetManager, apkPath)
            pluginResources = Resources(
                pluginAssetManager,
                super.getResources().displayMetrics,
                super.getResources().configuration
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getResources(): Resources {
        return pluginResources ?: super.getResources()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (pluginService == null) {
            initCurrentService(intent)
        }
        if (pluginResources == null) {
            initServiceResource()
        }
        return pluginService?.onStartCommand(intent, flags, startId) ?: super.onStartCommand(
            intent,
            flags,
            startId
        )
    }

    override fun onBind(intent: Intent?): IBinder? {
        if (pluginService == null) {
            initCurrentService(intent)
        }
        if (pluginResources == null) {
            initServiceResource()
        }
        return pluginService?.onBind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return pluginService?.onUnbind(intent) ?: false
    }
}