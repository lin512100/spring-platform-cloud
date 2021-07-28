package com.platform.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * MD5计算工具
 * @author lin512100
 * @date 2021/7/28
 */
public class CalculateMd5Utils {

    /**
     * 获取单个文件的MD5值！
     * @param file 文件
     * @return MD5值
     */
    public static String getFileMd5(File file) {
        if (!file.isFile()) {
            return null;
        }
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("文件流获取异常:" + e.getMessage());
        }
        return getFileMd5(inputStream);
    }

    /**
     * 获取单个文件的MD5值！
     * @param file 文件
     * @return MD5值
     */
    public static String getFileMd5(MultipartFile file) {

        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException("文件流获取异常:" + e.getMessage());
        }
        return getFileMd5(inputStream);
    }

    /**
     * 获取文件MD5值
     * @param in 输入流
     * @return MD5值
     */
    public static String getFileMd5(InputStream in) {
        MessageDigest digest = null;
        byte[] buffer = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("文件MD5值计算失败：" + e.getMessage());
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

}
