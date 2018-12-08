package com.github.plugin

import android.annotation.SuppressLint
import android.os.Bundle
import com.github.common.BaseActivity

class PluginActivity : BaseActivity() {

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_plugin)
    }
}
