package com.platform.common.utils;


import com.platform.common.consts.StringConst;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
     * 文件复制
     * @param file 文件
     * @param baseFilePath     根路径
     * @param relativePath 相对路径
     * @param fileNewName 文件名
     * @return 文件存储相对路径
     */
    public static String transferFile(MultipartFile file, String baseFilePath, String relativePath, String fileNewName) {
        if (file.getOriginalFilename() == null) {
            throw new RuntimeException("获取原始文件名失败：" + file.getName());
        }
        // 获取文件新名称
        String fileName = file.getOriginalFilename();
        fileName = fileNewName + fileName.substring(fileName.lastIndexOf(StringConst.PERIOD));

        // 创建文件路径
        String filePath = baseFilePath + File.separator + relativePath;
        createMulFilePath(filePath);

        // 保存文件
        try {
            file.transferTo(new File(filePath + File.separator + fileName));
        } catch (IOException e) {
            throw new RuntimeException("文件复制异常:" + e.getMessage());
        }
        return relativePath + File.separator + fileName;
    }

    /**
     * 获取绝对路径
     * @param basePath     根路径
     * @param relativePath 相对路径
     * @param fileName     文件名
     * @return 绝对路径
     */
    public static String getAbsolutePath(String basePath, String relativePath, String fileName) {
        return basePath + File.separator + relativePath + File.separator + fileName;
    }

    /**
     * 获取年月日时间路径
     * @return 日期路径
     */
    public static String getDatePath() {
        String format =
            File.separator + "yyyy" +
                File.separator + "MM" +
                File.separator + "dd";
        DateTimeFormatter dfDate = DateTimeFormatter.ofPattern(format);
        return dfDate.format(LocalDateTime.now());
    }

    /**
     * 根据路径获取文件信息
     * @param baseFilePath 基础路径
     * @param relativeFilePath 相对路径信息
     * @return file 文件信息
     * */
    public static File getFile(String baseFilePath,String relativeFilePath){
        File file = new File(baseFilePath + File.separator + relativeFilePath);
        if(!file.exists()){
            throw new RuntimeException("文件不存在");
        }
        return file;

    }



}
