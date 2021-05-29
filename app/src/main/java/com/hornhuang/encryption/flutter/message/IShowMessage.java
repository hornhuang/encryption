package com.hornhuang.encryption.flutter.message;

/**
 * @author: Create by leek on 5/29/21
 * @email: 814484626@qq.com
 */
public interface IShowMessage {
    /**
     *
     * @param message Flutter -> Android
     */
    void onShowMessage(String message);

    /**
     *
     * @param message Android --> flutter
     */
    void sendMessage(String message);
}
