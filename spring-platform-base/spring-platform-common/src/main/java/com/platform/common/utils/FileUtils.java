package com.platform.common.utils;


import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 文件操作工具类
 * @author lin512100
 * @date 2021/7/27
 */
public class FileUtils {

    /**
     * 创建多级目录
     * @param filePath 文件路径
     */
    public static void createMulFilePath(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isDirectory()) {
            boolean mkdir = file.mkdirs();
            if (!mkdir) {
                throw new RuntimeException("文件路径创建失败");
            }
        }
    }


    /**
     * 获取绝对路径
     * @param basePath     根路径
     * @param relativePath 相对路径
     * @param fileName 文件名
     * @return 绝对路径
     */
    public static String getAbsolutePath(String basePath, String relativePath,String fileName) {
        return basePath + File.separator + relativePath + File.separator + fileName;
    }

    /**
     * 获取年月日时间路径
     * @return 日期路径
     * */
    public static String getDatePath(){
        String format =
            File.separator + "yyyy" +
            File.separator + "MM" +
            File.separator + "dd";
        DateTimeFormatter dfDate = DateTimeFormatter.ofPattern(format);
        return dfDate.format(LocalDateTime.now());
    }

}
