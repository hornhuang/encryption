package com.hornhuang.encryption.ui.tools

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.hornhuang.encryption.R
import com.hornhuang.encryption.flutter.FlutterAppActivity
import com.hornhuang.encryption.module.base.activity.BaseActivity
import com.hornhuang.encryption.module.home.EncodeActivity
import io.flutter.plugin.common.BasicMessageChannel
import java.util.*


class HomeFragment : Fragment(),View.OnClickListener {

    private final var TAG = "HomeFragment"

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var mEncodeImg : ImageView
    private lateinit var mHushenImg : ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        init(root)
        return root
    }

    private fun init(root: View) {
        mEncodeImg = root.findViewById(R.id.encode_img)
        mHushenImg = root.findViewById(R.id.hushen300_img)

        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        homeViewModel.text.observe(this, Observer {
            //            decodeEdit.text = it
        })

        mEncodeImg.setOnClickListener(this)
        mHushenImg.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == null) {
            return
        }
        when (v.id) {
            R.id.encode_img  -> encode()
            R.id.hushen300_img -> hushen300()
        }
    }

    fun encode() {
        EncodeActivity.actionStart(activity as BaseActivity)
    }

    fun hushen300() {
        if (context != null) {
            //2.向Flutter端发送消息
            val params: MutableMap<String, String> =
                HashMap()
            params["url"] = "https://androidinvest.com/ChinaIndicesPE/SH000300/"
            params["route"] = "WebView"
            FlutterAppActivity.start(context!!, Gson().newBuilder().create().toJson(params), 1)
        }
    }
}