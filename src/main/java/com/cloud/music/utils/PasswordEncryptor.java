package com.cloud.music.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;


public class PasswordEncryptor {

    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
            "6", "!", "#", "@", "a", "b", "c", "d", "*", "f", "g", "F"};

    private  Object salt;   //盐值
    private  String algorithm;  //加密类型种类

    public PasswordEncryptor(Object salt, String algorithm) {
        this.salt = salt;
        this.algorithm = algorithm;
    }

    public String encode(String rawPass) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            // 加密后的字符串
            result = byteArrayToHexString(md.digest(mergePasswordAndSalt(rawPass).getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean isPasswordValid(String encPass, String rawPass) {
        String pass1 = "" + encPass;
        String pass2 = encode(rawPass);
        return pass1.equals(pass2);
    }

    //密码、盐值拼接
    private  String mergePasswordAndSalt(String password) {
        if (password == null ||"".equalsIgnoreCase(password)) {
            password = "";
        }
        if ((salt == null) || "".equals(salt)) {
            return password;
        } else {
            return password + salt.toString();
        }
    }

    /**
     * 转换字节数组为16进制字串
     *
     * @param b 字节数组
     * @return 16进制字串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte value : b) {
            resultSb.append(byteToHexString(value));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) n = 256 + n;
        return hexDigits[n / hexDigits.length] + hexDigits[n % hexDigits.length];
    }

//    public static void main(String[] args) {
//        String salt = UUID.randomUUID().toString().replaceAll("-","");//随机生成盐值
//        PasswordEncryptor encoderMd5 = new PasswordEncryptor(salt, "sha-256");
//        String encodedPassword = encoderMd5.encode("password");//加盐后的MD5
//        System.out.println("加密后密码：" + encodedPassword + "\n密码长度：" + encodedPassword.length());
//        System.out.println("salt:" + salt);
//    }

}