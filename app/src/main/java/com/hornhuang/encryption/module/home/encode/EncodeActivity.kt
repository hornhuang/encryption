package com.hornhuang.encryption.module.home.encode

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders
import com.getbase.floatingactionbutton.FloatingActionButton
import com.hornhuang.encryption.R
import com.hornhuang.encryption.module.base.activity.BaseActivity
import com.hornhuang.encryption.module.home.HomeViewModel
import com.hornhuang.encryption.utils.DesUtil

class EncodeActivity : BaseActivity(), View.OnClickListener {

    private final var TAG = "HomeFragment"

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var keyEdit    : EditText;
    private lateinit var decodeEdit : EditText;
    private lateinit var mDecodeBtn : FloatingActionButton;
    private lateinit var mEncodeBtn : FloatingActionButton;
    private lateinit var mCopyBtn   : FloatingActionButton;

    /**
     * 包裹范围内 属于静态方法
     */
    companion object {

        fun actionStart(activity: Activity) {
            activity.startActivity(Intent(activity, EncodeActivity::class.java))
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encode)

        supportActionBar?.title = "加密/解密"

        init()
    }

    private fun init() {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        keyEdit    = findViewById(R.id.key_edit)
        decodeEdit = findViewById(R.id.decode_edit)
        mDecodeBtn = findViewById(R.id.decode_btn)
        mEncodeBtn = findViewById(R.id.encode_btn)
        mCopyBtn   = findViewById(R.id.copy_btn)

        mDecodeBtn.setOnClickListener(this)
        mEncodeBtn.setOnClickListener(this)
        mCopyBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == null) {
            return
        }
        when (v.id) {
            R.id.decode_btn -> decode()
            R.id.encode_btn -> encode()
            R.id.copy_btn   -> copy()
        }
    }

    fun decode() {
        var resultStr = DesUtil.setData(decodeEdit.text.toString()).decode(keyEdit.text.toString())
        decodeEdit.setText(resultStr)
    }

    fun encode() {
        var resultStr = DesUtil.setData(decodeEdit.text.toString()).encode(keyEdit.text.toString())
        decodeEdit.setText(resultStr)
    }

    fun copy() {
        //获取剪贴板管理器：
        val cm = this.getSystemService(Context. CLIPBOARD_SERVICE) as ClipboardManager?
        // 创建普通字符型ClipData
        val mClipData = ClipData.newPlainText("Label", decodeEdit.text)
        // 将ClipData内容放到系统剪贴板里。
        cm!!.setPrimaryClip(mClipData)
    }
}
