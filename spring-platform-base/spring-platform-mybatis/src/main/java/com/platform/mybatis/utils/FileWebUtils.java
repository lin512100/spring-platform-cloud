package com.platform.mybatis.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 文件网页相关工具类
 * @author lin512100
 * @date 2021/7/28
 */
public class FileWebUtils {

    /***
     * 文件下载
     * @param fileAbsPath 需要下载文件的绝对路径
     * @param res 响应内容
     * @throws IOException 异常
     */
    public static void download(String fileAbsPath, HttpServletResponse res) throws IOException {
        InputStream inputStream = null;
        OutputStream os = null;
        try{
            File file = new File(fileAbsPath);
            String name = file.getName();
            inputStream = new FileInputStream(file);
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len =0;
            while((len=inputStream.read(buffer))!=-1){
                outStream.write(buffer,0,len);
            }
            inputStream.close();
            byte[] data = outStream.toByteArray();
            res.reset();
            res.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(name,"UTF-8"));
            res.setContentType("application/octet-stream");
            os = res.getOutputStream();
            os.write(data);
            os.flush();
            os.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("导出元数据失败");
        }finally {
            if(inputStream!=null){
                inputStream.close();
            }
            if(os!=null){
                os.close();
            }
        }
    }
}
