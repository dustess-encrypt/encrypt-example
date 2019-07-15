package com.dustess.sdk;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class EncryptHelper {

    private static final String HEX = "0123456789ABCDEF";
    private static final String MD5 = "MD5";
    private static final String UTF8 = "UTF-8";
    private static final String AES = "AES";
    private static final String AES_CBC_PADDING = "AES/CBC/PKCS5Padding";

    private static Key generateKey(String key) {
        try {
            MessageDigest md = MessageDigest.getInstance(MD5);
            byte[] md5 = md.digest(key.getBytes(UTF8));
            return new SecretKeySpec(md5, AES);
        } catch (Exception ex) {
            throw new EncryptException(ex);
        }
    }


    private static byte[] encodeDecode(int mode, byte[] bytes, String key) {
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PADDING);
            cipher.init(mode, generateKey(key), new IvParameterSpec(HEX.getBytes(UTF8)));
            return cipher.doFinal(bytes);

        } catch (Exception e) {
            throw new EncryptException(e);
        }
    }


    public static String encode(String blankText, String key) {

        try {
            byte[] bytes = encodeDecode(Cipher.ENCRYPT_MODE, blankText.getBytes(UTF8), key);
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            throw new EncryptException(e);
        }
    }

    public static String decode(String encryptedText, String key) {
        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
            byte[] bytes = encodeDecode(Cipher.DECRYPT_MODE, encryptedBytes, key);
            return new String(bytes, UTF8);
        } catch (Exception ex) {
            throw new EncryptException(ex);
        }
    }
}
