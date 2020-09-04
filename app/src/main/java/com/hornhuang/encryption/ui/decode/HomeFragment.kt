package com.hornhuang.encryption.ui.decode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hornhuang.encryption.R
import android.widget.ImageView
import com.hornhuang.encryption.module.base.BaseActivity
import com.hornhuang.encryption.module.home.EncodeActivity


class HomeFragment : Fragment(),View.OnClickListener {

    private final var TAG = "HomeFragment"

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var mEncodeBtn : ImageView;

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
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        mEncodeBtn = root.findViewById(R.id.encode_img)
        homeViewModel.text.observe(this, Observer {
            //            decodeEdit.text = it
        })

        mEncodeBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == null) {
            return
        }
        when (v.id) {
            R.id.encode_img -> encode()
        }
    }

    fun encode() {
        EncodeActivity.actionStart(activity as BaseActivity)
    }
}