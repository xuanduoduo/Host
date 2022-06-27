package com.chenxuan.base.activity

import android.content.res.AssetManager
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.chenxuan.base.PluginClassLoader
import java.io.File

class HostActivity : AppCompatActivity() {
    private var pluginClassLoader: PluginClassLoader? = null
    private var pluginActivity: PluginActivity? = null

    private var apkPath: String? = null
    private var pluginResources: Resources? = null
    private var realResources: Resources? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        initCurrentActivity()
        initActivityResource()
        super.onCreate(savedInstanceState)
        pluginActivity?.onCreate(savedInstanceState)
    }

    fun setHostContentView(@LayoutRes layoutResID: Int) {
        val view = LayoutInflater.from(this).inflate(layoutResID, null, false)
        setHostContentView(view)
    }

    fun setHostContentView(view: View) {
        beforeSetContentView()
        setContentView(view)
        afterSetContentView()
    }

    private fun beforeSetContentView() {
        realResources = super.getResources()
    }

    private fun afterSetContentView() {
        realResources = pluginResources
    }

    override fun getResources(): Resources {
        return realResources ?: super.getResources()
    }

    private fun initActivityResource() {
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
            realResources = pluginResources
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initCurrentActivity() {
        apkPath = "${cacheDir.absolutePath}${File.separator}plugin-debug.apk"
        pluginClassLoader = PluginClassLoader(
            dexPath = apkPath ?: "",
            optimizedDirectory = cacheDir.absolutePath,
            librarySearchPath = null,
            classLoader
        )
        val activityName = intent.getStringExtra("ActivityName") ?: ""
        pluginActivity = pluginClassLoader?.loadActivity(activityName, this)
    }

    override fun onStart() {
        super.onStart()
        pluginActivity?.onStart()
    }

    override fun onResume() {
        super.onResume()
        pluginActivity?.onResume()
    }

    override fun onRestart() {
        super.onRestart()
        pluginActivity?.onRestart()
    }

    override fun onPause() {
        super.onPause()
        pluginActivity?.onPause()
    }

    override fun onStop() {
        super.onStop()
        pluginActivity?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        pluginActivity?.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        pluginActivity?.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        pluginActivity?.onRestoreInstanceState(savedInstanceState)
    }
}