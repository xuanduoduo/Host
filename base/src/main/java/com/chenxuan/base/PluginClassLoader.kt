package com.chenxuan.base

import com.chenxuan.base.activity.HostActivity
import com.chenxuan.base.activity.PluginActivity
import com.chenxuan.base.service.HostService
import com.chenxuan.base.service.PluginService
import dalvik.system.DexClassLoader

class PluginClassLoader(
    dexPath: String,
    optimizedDirectory: String,
    librarySearchPath: String?,
    parent: ClassLoader
) : DexClassLoader(dexPath, optimizedDirectory, librarySearchPath, parent) {
    fun loadActivity(activityName: String, host: HostActivity): PluginActivity? {
        try {
            return (loadClass(activityName)?.newInstance() as PluginActivity?).apply {
                this?.bindHost(host)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun loadService(serviceName: String, host: HostService): PluginService? {
        try {
            return (loadClass(serviceName)?.newInstance() as PluginService?).apply {
                this?.bindHost(host)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}