package com.aiggo.common.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Administrator
 * @ Created by Administrator on 2014/11/26.
 */
public class DESUtil {
//    private static Cipher encrypt = null;
//    private static Cipher decrypt = null;
//    private static boolean isEncryptInit = false;
//    private static boolean isDecryptInit = false;
    private static String KEYSTR = "-7-*d@#5EdxBvrTRe-#5CtUs";

//    private static Cipher getEncrypt() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
//        if (encrypt == null && !isEncryptInit) {
//            synchronized (DESUtil.class) {
//                if (encrypt == null && !isEncryptInit) {
//                    encrypt = Cipher.getInstance("TripleDES");
//                    encrypt.init(Cipher.ENCRYPT_MODE, getKey());
//                    isEncryptInit = true;
//                }
//            }
//
//        }
//        return encrypt;
//    }
//
//
//    private static Cipher getDecrypt() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
//        if (decrypt == null && !isDecryptInit) {
//            synchronized (DESUtil.class) {
//                if (decrypt == null && !isDecryptInit) {
//                    decrypt = Cipher.getInstance("TripleDES");
//                    decrypt.init(Cipher.DECRYPT_MODE, getKey());
//                    isDecryptInit = true;
//                }
//            }
//
//        }
//        return decrypt;
//    }

    private static Key getKey() {
        SecretKey key = new SecretKeySpec(KEYSTR.getBytes(), "TripleDES");
        return key;
    }

    public static byte[] encrypt(byte[] inputByte) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
    	Cipher encrypt = Cipher.getInstance("TripleDES");
        encrypt.init(Cipher.ENCRYPT_MODE, getKey());
    	byte[] ciperByte = encrypt.doFinal(inputByte);
        byte[] encode = Base64.encodeBase64(ciperByte);
        return encode;
    }

    public static byte[] decrypt(byte[] inputByte) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
        byte[] encodeStr = Base64.decodeBase64(inputByte);
        Cipher decrypt = Cipher.getInstance("TripleDES");
        decrypt.init(Cipher.DECRYPT_MODE, getKey());
        byte[] ciperByte = decrypt.doFinal(encodeStr);
        return ciperByte;
    }
}
