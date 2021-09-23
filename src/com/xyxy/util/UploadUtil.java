package com.xyxy.util;

import javax.servlet.http.Part;
import java.io.IOException;

//文件上传工具类
public class UploadUtil {
    //上传文件
    public static String uploadFile(Part part) {
        try {  //设置目标服务器路径
            //设置目标服务器路径
            String path = "E:\\bishe\\songurl\\";
            //创建上传的文件对象：Part里面封装了请求中上传的文件参数信息
            //获取上传的文件名称:
            String fileName = part.getSubmittedFileName();
            //将要上传到的路径名组合起来
            String songUrl = path + fileName;
            //将文件上传到目标服务器:目前文件的路径及文件名称path+filename
            part.write(songUrl);
            return songUrl;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
