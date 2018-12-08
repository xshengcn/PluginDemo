package com.github.common

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.Window

abstract class BaseActivity : Activity() {
    var activity: Activity? = null

    fun setHost(activity: Activity) {
        this.activity = activity
    }


    @SuppressLint("MissingSuperCall")
    override public fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingSuperCall")
    override public fun onDestroy() {
        super.onDestroy()
    }

    override fun getWindow(): Window {
        return activity?.window ?: super.getWindow()
    }
}