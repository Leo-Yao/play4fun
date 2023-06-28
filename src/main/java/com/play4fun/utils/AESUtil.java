package com.play4fun.utils;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.codec.Base64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class AESUtil {

    //AES加密
    public static String encrypt(String key, String value) throws Exception {
        return Base64Encoder.encode(encryptToBytes(key, value));
    }

    public static byte[] encryptToBytes(String key, String value) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(key);
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(key.getBytes());
        keyGenerator.init(256, random);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyGenerator.generateKey().getEncoded(), "AES"));
        return cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
    }

    //AES解密
    public static String decrypt(String decryptKey, String encryptValue) throws Exception {
        return decryptBytesToString(decryptKey, Base64Decoder.decode(encryptValue));
    }

    public static String decryptBytesToString(String decryptKey, byte[] encryptValue) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(decryptKey.getBytes());
        keyGenerator.init(256, random);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyGenerator.generateKey().getEncoded(), "AES"));
        return new String(cipher.doFinal(encryptValue));
    }
}
