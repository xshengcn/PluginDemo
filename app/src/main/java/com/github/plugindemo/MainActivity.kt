package com.github.plugindemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : Activity() {

    companion object {
        private const val TAG = "MainActivity"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pluginFile = File(filesDir, "plugin.apk")
        loadPluginBtn.setOnClickListener {
            copyFileFromAssets("plugin-debug.apk", pluginFile)
        }

        openPluginBtn.setOnClickListener {
            startActivity(Intent(this, HostActivity::class.java))
        }

    }

    private fun copyFileFromAssets(assetsName: String, file: File): File = File(file.absolutePath)
        .also {
            if (!it.exists()) {
                it.outputStream().use { cache ->
                    assets.open(assetsName).use { inputStream ->
                        inputStream.copyTo(cache)
                    }
                }
            }
        }
}