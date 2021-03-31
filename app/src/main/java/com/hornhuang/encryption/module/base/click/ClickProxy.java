package com.hornhuang.encryption.module.base.click;

import android.view.View;

/**
 * @author: Create by leek on 3/31/21
 * @email: 814484626@qq.com
 */
public class ClickProxy implements View.OnClickListener {

    private View.OnClickListener origin;
    private long lastclick = 0;
    private long timems = 1000; //ms
    private IAgain mIAgain;

    public ClickProxy(View.OnClickListener origin, long timems, IAgain again) {
        this.origin = origin;
        this.mIAgain = again;
        this.timems = timems;
    }

    public ClickProxy(View.OnClickListener origin) {
        this.origin = origin;
    }

    @Override
    public void onClick(View v) {
        if (System.currentTimeMillis() - lastclick >= timems) {
            origin.onClick(v);
            lastclick = System.currentTimeMillis();
        } else {
            if (mIAgain != null) mIAgain.onAgain();
        }
    }

    public interface IAgain {
        void onAgain();//重复点击
    }
}