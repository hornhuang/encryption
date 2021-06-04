package com.hornhuang.encryption.flutter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.hornhuang.encryption.flutter.message.BasicMessageChannelPlugin;
import com.hornhuang.encryption.flutter.message.IShowMessage;
import com.hornhuang.encryption.utils.EngineHelper;

import java.util.HashMap;
import java.util.Map;

import io.flutter.embedding.android.FlutterActivity;

import static com.hornhuang.encryption.MyApplication.getFlutterEngine;

/**
 * @author: Create by leek on 5/29/21
 * @email: 814484626@qq.com
 */
public class FlutterAppActivity extends FlutterActivity implements IShowMessage {

    private final String TAG = "FlutterAppActivity";

    private BasicMessageChannelPlugin basicMessageChannelPlugin;

    public final static String INIT_PARAMS = "initParams";

    String mInitParam;
    /**
     * 0 给Flutter传递初始化数据
     * 1 使用BasicMsgChannel传递数据
     */
    private static int mtype ;

    public static void start(Context context, String initParams, int type) {
        mtype = type;
//        Intent intent = FlutterAppActivity.withCachedEngine(EngineHelper.engineName).build(context);
        Intent intent = new Intent(context, FlutterAppActivity.class);
        intent.putExtra(INIT_PARAMS, initParams);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"11111");
        if (mtype == 0) {
            // 给Flutter传递初始化数据
            mInitParam = getIntent().getStringExtra(INIT_PARAMS);

        }else if(mtype == 1){
            //使用BasicMsgChannel传递数据
            basicMessageChannelPlugin = BasicMessageChannelPlugin.registerWith(getFlutterEngine().getDartExecutor(), this);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mtype == 1) {
            String initParam = getIntent().getStringExtra(INIT_PARAMS);
            this.sendMessage(initParam);
        }

    }

    @Override
    public void onShowMessage(String message) {
        Log.i(TAG,message);
        switch (message) {
            case "close":
                finish();
            default:
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }

    @Nullable
    @Override
    public String getCachedEngineId() {
        return EngineHelper.engineName;
    }

    @Override
    public String getInitialRoute() {
        return mInitParam == null ? super.getInitialRoute() : mInitParam;
    }

    @Override
    public void sendMessage(String message) {
        Log.i(TAG, "sendMessage :: " + message);
        if (basicMessageChannelPlugin == null) {
            return;
        }
        basicMessageChannelPlugin.send(getIntent().getStringExtra(INIT_PARAMS),this::onShowMessage);
    }
}
