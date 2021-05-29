package com.hornhuang.encryption;

import android.app.Application;

import com.hornhuang.encryption.utils.EngineHelper;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;

/**
 * @author: Create by leek on 3/31/21
 * @email: 814484626@qq.com
 */
public class MyApplication extends Application {

    static private FlutterEngine flutterEngine;

    @Override
    public void onCreate() {
        super.onCreate();
        // Instantiate a FlutterEngine.
        flutterEngine = new FlutterEngine(this);

        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.getDartExecutor().executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
        );

        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
                .getInstance()
                .put(EngineHelper.engineName, flutterEngine);

    }

    /**
     * onTerminate()当App销毁时执行
     */
    @Override
    public void onTerminate() {
        //销毁flutter引擎
        flutterEngine.destroy();
        super.onTerminate();
    }

    static public FlutterEngine getFlutterEngine() {
        return flutterEngine;
    }
}
