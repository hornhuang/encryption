package com.hornhuang.encryption.module.base.activity

import androidx.appcompat.app.AppCompatActivity
import com.hornhuang.encryption.R
import com.hornhuang.encryption.utils.StatusBarUtils

open class BaseActivity : AppCompatActivity() {
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        StatusBarUtils.setStatusBarDrawable(this, R.drawable.bg_action_bar);
    }
}