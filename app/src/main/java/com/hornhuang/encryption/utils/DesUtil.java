package com.hornhuang.encryption.utils;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.spec.EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

/**
 * DES加密 解密算法
 *
 * @author lifq
 * @date 2015-3-17 上午10:12:11
 */
public class DesUtil {

    private final static String DES = "DES";
    private final static String ENCODE = "GBK";
    private final static String defaultKey = "hornhuang7938";

    static String data = "GHEubSlGHfwWJnzmZ0v4nry/Xuoh9PluwmfKkZHrXvCQ6Q1h/6tCvGDTGa5BReoCnQnHZKPK3mCoR20KE6MqSJ0Jx2Sjyt5gcJrC0aFQKLjld3dqFzSk4jIBkiabnqcJFVRZoYO4kFGOsp/htlL+G/JH/J5oAWjO5EVSKWdW/fjX57Mp5OySzwsNRAhUfgIGL8kNxDgYp1Ce6Kh3iHPoSV72pvz9ykBc+sUkhRkVPV3iuFZ+/o4Hq8U21CofbpPo";

    private DesUtil(String data) {
        this.data = data;
    }

    public String encode() {
        try {
            return encrypt(data);
//			new FileUtil().writeFile(encrypt(data));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public String decode() {
        try {
//    		System.out.println(data);
            return decrypt(data);
//			new FileUtil().writeFile(decrypt(data));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 使用 默认key 加密
     *
     * @return String
     * @author lifq
     * @date 2015-3-17 下午02:46:43
     */
    private String encrypt(String data) throws Exception {
        byte[] bt = encrypt(data.getBytes(ENCODE), defaultKey.getBytes(ENCODE));
        Encoder encoder = Base64.getEncoder();
        String strs = encoder.encodeToString(bt);
        return strs;
    }

    /**
     * 使用 默认key 解密
     *
     * @return String
     * @author lifq
     * @date 2015-3-17 下午02:49:52
     */
    private String decrypt(String data) throws IOException, Exception {
        if (data == null)
            return null;
        Decoder decoder = Base64.getDecoder();
        byte[] buf = decoder.decode(data);
        byte[] bt = decrypt(buf, defaultKey.getBytes(ENCODE));
        return new String(bt, ENCODE);
    }

    /**
     * Description 根据键值进行加密
     *
     * @param data
     * @param key
     *            加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }

    /**
     * Description 根据键值进行解密
     *
     * @param data
     * @param key
     *            加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }

    public static DesUtil setData(String data) {
        return new DesUtil(data);
    }
}