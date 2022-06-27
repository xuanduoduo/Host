package com.chenxuan.plugin

import android.content.Intent
import android.os.IBinder
import com.chenxuan.base.service.PluginService
import com.chenxuan.base.service.ipc.IPerson
import com.chenxuan.base.util.log

class PersonService : PluginService() {
    private val binder = Binder()

    override fun onBind(intent: Intent?): IBinder {
        context?.log("onBind")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        context?.log("onUnbind")
        return super.onUnbind(intent)
    }

    inner class Binder : IPerson.Stub() {
        override fun eat(food: String): String {
            context?.log("eat", food)
            return food
        }

        override fun age(age: Int): Int {
            context?.log("age", "" + age)
            return age
        }

        override fun name(name: String): String {
            context?.log("name", name)
            return name
        }
    }
}