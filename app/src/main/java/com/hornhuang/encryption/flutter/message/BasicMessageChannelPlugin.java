package com.hornhuang.encryption.flutter.message;

import android.app.Activity;
import android.widget.Toast;

import io.flutter.Log;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.StringCodec;

/**
 szj 2020/11/26
 CSDN博客:https://blog.csdn.net/weixin_44819566/
 微信公众号:码上变有钱
 */
public class BasicMessageChannelPlugin implements BasicMessageChannel.MessageHandler<String> {

    private final Activity activity;
    private final BasicMessageChannel<String> messageChannel;

    public static BasicMessageChannelPlugin registerWith(BinaryMessenger messenger, Activity activity) {
        return new BasicMessageChannelPlugin(messenger, activity);
    }

    private BasicMessageChannelPlugin(BinaryMessenger messenger, Activity activity) {
        this.activity = activity;
        this.messageChannel = new BasicMessageChannel(messenger, "com.geekholt.hybird/TestBasicMessageChannel", StringCodec.INSTANCE);
        //设置消息处理器，处理来自Dart的消息
        messageChannel.setMessageHandler(this);

    }

    @Override//处理Dart发来的消息
    public void onMessage(String s, BasicMessageChannel.Reply<String> reply) {
        reply.reply("BasicMessageChannel收到：" + s);//可以通过reply进行回复
        if (activity instanceof IShowMessage) {
            ((IShowMessage) activity).onShowMessage(s);
            Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
        }


    }

    /**
     * 向Dart发送消息，并接受Dart的反馈
     *
     * @param message  要给Dart发送的消息内容
     * @param callback 来自Dart的反馈
     */
    public void send(String message, BasicMessageChannel.Reply<String> callback) {
        messageChannel.send(message, callback);
    }

}
