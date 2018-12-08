package com.github.plugindemo

import android.app.Activity
import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import android.os.Bundle
import com.github.common.BaseActivity
import dalvik.system.DexClassLoader
import java.lang.reflect.Method


class HostActivity : Activity() {

    private var activity: BaseActivity? = null
    private val pluginPath = "/data/user/0/com.github.plugindemo/files/plugin.apk"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//super.getAssets().list()

        val activityClass = classLoader.loadClass("com.github.plugin.PluginActivity")
        val constructor = activityClass.getConstructor()
        val instance = constructor.newInstance()
        activity = instance as BaseActivity?
        activity?.setHost(this)
        activity?.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
//        activity?.onDestroy()
    }

    override fun getClassLoader(): ClassLoader {
        val dir: String = getDir("dex", Context.MODE_PRIVATE).absolutePath
        return DexClassLoader(pluginPath, dir, null, super.getClassLoader())
    }

    override fun getAssets(): AssetManager {
        val assetManager = AssetManager::class.java.newInstance()
        val addAsset: Method =
            AssetManager::class.java.getMethod("addAssetPath", String::class.java)
        addAsset.invoke(assetManager, pluginPath)
        return assetManager
    }

    override fun getResources(): Resources {
        return Resources(
            assets,
            super.getResources().displayMetrics,
            super.getResources().configuration
        )
    }
}