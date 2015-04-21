package com.mobile.meishang.utils;
//package com.ivpoints.mobile.utils;
//
//import java.math.BigInteger;
//import java.security.Key;
//
//import javax.crypto.Cipher;
//import javax.crypto.SecretKey;
//import javax.crypto.SecretKeyFactory;
//import javax.crypto.spec.PBEKeySpec;
//import javax.crypto.spec.PBEParameterSpec;
//
///**
// * PBE安全编码组件 <br>
// * 终端使用userID作为口令， DEFAULTSALT作为盐</br> <br>
// * 终端加密后的密文是十六进制的字符串，接口需要先转换成 字节数组，才能进行解密</br>
// * 
// * @author 11078566
// * @version 1.0
// * @since 1.0
// */
//public class PBECoder
//{
//    /**
//     * 采用的加密算法
//     */
//    public static final String ALGORITHM = "PBEWITHMD5andDES";
//
//    /**
//     * 默认盐，可配置，需要与客户端保持一致 盐的位数必须为8字字节
//     */
//    public static final String DEFAULTSALT = "sn201209";
//
//    /**
//     * 密钥摘要次数，可配置，需要与客户端保持一致
//     */
//    public static final int ITERATIONSNUM = 50;
//
//    /**
//     * 转换密钥<br>
//     * 
//     * @param password
//     *            口令，这里暂定为用户名
//     * @return
//     * @throws Exception
//     */
//    private static Key toKey(String password) throws Exception
//    {
//        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
//
//        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
//
//        SecretKey secretKey = keyFactory.generateSecret(keySpec);
//
//        return secretKey;
//    }
//
//    /**
//     * 加密
//     * 
//     * @param data
//     *            数据
//     * @param password
//     *            密码
//     * @param salt
//     *            盐
//     * @return
//     * @throws Exception
//     */
//    public static byte[] encrypt(byte[] data, String password, byte[] salt)
//            throws Exception
//    {
//
//        Key key = toKey(password);
//
//        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 50);
//        Cipher cipher = Cipher.getInstance(ALGORITHM);
//        cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
//
//        return cipher.doFinal(data);
//
//    }
//
//    /**
//     * 解密
//     * 
//     * @param data
//     *            数据
//     * @param password
//     *            密码
//     * @param salt
//     *            盐
//     * @return
//     * @throws Exception
//     */
//    public static byte[] decrypt(byte[] data, String password, byte[] salt)
//            throws Exception
//    {
//
//        Key key = toKey(password);
//
//        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 50);
//        Cipher cipher = Cipher.getInstance(ALGORITHM);
//        cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
//
//        return cipher.doFinal(data);
//
//    }
//
//    /**
//     * 二行制转十六进制字符串
//     * 
//     * @param b
//     * @return
//     */
//    public static String byte2hex(byte[] b)
//    {
//        StringBuffer hs = new StringBuffer();
//        String stmp = "";
//
//        for (int n = 0; n < b.length; n++)
//        {
//            // 整数转成十六进制表示
//            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
//
//            if (stmp.length() == 1)
//            {
//                hs.append("0" + stmp);
//            }
//            else
//            {
//                hs.append(stmp);
//            }
//        }
//
//        return hs.toString().toUpperCase(); // 转成大写
//    }
//
//    /*
//     * 将字节数组转换为十六进制字符串 *
//     */
//    public static String getHexString(byte[] b) throws Exception
//    {
//        StringBuffer result = new StringBuffer();
//        for (int i = 0; i < b.length; i++)
//        {
//            result.append(Integer.toString((b[i] & 0xff) + 0x100, 16)
//                    .substring(1));
//        }
//        return result.toString();
//    }
//
//    public static byte[] getByteArray(String hexString)
//    {
//        return new BigInteger(hexString, 16).toByteArray();
//    }
//
//}
//
