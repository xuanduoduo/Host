package com.chenxuan.host

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chenxuan.base.service.ipc.IPerson
import com.chenxuan.base.util.bindRemotePluginService
import com.chenxuan.base.util.jumpPluginActivity
import com.chenxuan.base.util.log
import com.chenxuan.base.util.startLocalPluginService

class MainActivity : AppCompatActivity() {
    private var iPerson: IPerson? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.jump).setOnClickListener {
            jumpPluginActivity("com.chenxuan.plugin.LoginActivity")
        }

        findViewById<TextView>(R.id.startService).setOnClickListener {
            startLocalPluginService("com.chenxuan.plugin.NormalService")
        }

        val connection: ServiceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                Toast.makeText(this@MainActivity, "onServiceConnected", Toast.LENGTH_SHORT).show()
                iPerson = IPerson.Stub.asInterface(service)
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Toast.makeText(this@MainActivity, "onServiceDisconnected", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        val unbind = findViewById<Button>(R.id.unbindService).apply {
            setOnClickListener {
                unbindService(connection)
                this.visibility = View.GONE
            }
        }

        findViewById<Button>(R.id.bindService).setOnClickListener {
            bindRemotePluginService(
                "com.chenxuan.plugin.PersonService",
                connection,
                Context.BIND_AUTO_CREATE
            )
            unbind.visibility = View.VISIBLE
        }

        findViewById<Button>(R.id.eat).setOnClickListener {
            val food = iPerson?.eat("money")
            log("eat", food)
        }
        findViewById<Button>(R.id.age).setOnClickListener {
            val age = iPerson?.age(27)
            log("age", "$age")
        }
        findViewById<Button>(R.id.name).setOnClickListener {
            val name = iPerson?.name("chenxuan")
            log("name", name)
        }
    }
}