package com.xyxy.servlet;

import com.alibaba.fastjson.JSON;
import com.xyxy.pojo.ResultInfo;
import com.xyxy.pojo.Video;
import com.xyxy.service.VideoService;
import com.xyxy.util.PageUtil;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.util.List;

@WebServlet("/video")
@MultipartConfig
public class VideoServlet extends BaseServlet {
    private VideoService videoService = new VideoService();
    private ResultInfo resultInfo = new ResultInfo();

    //增加MV视频
    public void insertVideo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取添加表单中编写的数据
        String viName = request.getParameter("viName");
        String singer = request.getParameter("singer");
        String vcity = request.getParameter("vcity");
        Integer vyear = Integer.valueOf(request.getParameter("vyear"));
        Integer popular = Integer.valueOf(request.getParameter("popular"));
        //设置目标服务器路径
        String path = "E:\\bishe\\MV\\";
        //创建上传的文件对象：Part里面封装了请求中上传的文件参数信息
        //图片
        Part photo = request.getPart("vphoto");
        //视频
        Part url = request.getPart("viurl");
        //获取上传的文件名称:
        String vphoto = photo.getSubmittedFileName();
        String viurl = url.getSubmittedFileName();
        //将要上传到的路径名组合起来
        String newFileName = path + vphoto;
        String fileName = path + viurl;
        //将文件上传到目标服务器:目前文件的路径及文件名称
        photo.write(newFileName);
        url.write(fileName);
        //封装成一个对象
        Video video = new Video(vyear, popular, viName, vphoto, singer, vcity, viurl);
        //调用service方法来进行增加
        int row = videoService.insertVideo(video);
        if (row > 0) {
            response.sendRedirect("layuimini/page/video_add.jsp");
        }
    }

    //根据MV名称查询是否重复
    public void checkByViName(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取MV名称
        String viName = request.getParameter("viName");
        //调用service方法来检查是否重复
        Video video = videoService.checkByViName(viName);
        //判断是否查询到
        if (video == null) {
            //如果为空就传一个可以执行的值ok
            response.getWriter().write(JSON.toJSONString("ok"));
        } else {
            //如果不为空就传递一个fail
            response.getWriter().write(JSON.toJSONString("fail"));
        }
    }

    //查询所有的mv并显示出来
    public void queryVideoAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取页面中要分页的数量以及一页中的数量
        Integer limit = Integer.valueOf(request.getParameter("limit"));
        String page = request.getParameter("page");
        //查询所有的MV数量
        Integer count = videoService.queryCount();
        PageUtil<Video> pageUtil = new PageUtil<Video>(count, page, limit);
        //调用service方法来进行分页查询
        List<Video> videoList = videoService.queryVideoByPage(pageUtil);
        //设置返回结果信息
        resultInfo.setCode(0);
        resultInfo.setCount(count);
        resultInfo.setResult(videoList);
        response.getWriter().write(JSON.toJSONString(resultInfo));
    }

    //根据MV名称，歌手名来查询并分页显示数据
    public void selectBySome(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取前端输入框输入要查询的值
        String singer = request.getParameter("singer");
        String viName = request.getParameter("viName");
        //获取页面中要分页的数量以及一页中的数量
        Integer limit = Integer.valueOf(request.getParameter("limit"));
        String page = request.getParameter("page");
        //根据条件来查询所有符合条件的MV数量
        Integer count = videoService.queryCountNum(singer, viName);
        PageUtil<Video> pageUtil = new PageUtil<Video>(count, page, limit);
        //调用service方法来进行分页查询
        List<Video> videoList = videoService.selectVideoBySome(singer, viName, pageUtil);
        //设置状态和数据，并将查询到的数据显示出来
        resultInfo.setCode(0);
        resultInfo.setCount(count);
        resultInfo.setResult(videoList);
        response.getWriter().write(JSON.toJSONString(resultInfo));
    }

    //根据id进行单个查询,然后删除
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取要删除的id值
        Integer vid = Integer.valueOf(request.getParameter("vid"));
        //调用service方法来进行删除
        int row = videoService.deleteById(vid);
        //判断是否删除成功
        if (row > 0) {
            //删除成功，返回200
            response.getWriter().write(JSON.toJSONString(200));
        } else {
            //删除失败，返回201
            response.getWriter().write(JSON.toJSONString(201));
        }
    }

    //根据ids集合来进行批量删除的操作
    public void batchDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取要进行删除操作的ids集合
        String ids = request.getParameter("ids");
        //调用service方法来进行删除
        int row = videoService.batchDelete(ids);
        //判断是否删除成功
        if (row > 0) {
            //删除成功，返回200
            response.getWriter().write(JSON.toJSONString("200"));
        } else {
            //删除失败，返回201
            response.getWriter().write(JSON.toJSONString("201"));
        }
    }

    //根据mvid来查询要修改的专辑
    public void findVideoById(HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        //获取修改更改的vid
        Integer vid = Integer.valueOf(request.getParameter("vid"));
        //调用service方法来进行查询专辑信息
        Video video = videoService.findVideoById(vid);
        //将查询到的对象传入到作用域中
        HttpSession session = request.getSession();
        session.setAttribute("video", video);
        request.getRequestDispatcher("layuimini/page/video_edit.jsp").forward(request, response);
    }

    //修改MV信息
    public void updateVideo(HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
        //获取要修改的相关信息
        Integer vid = Integer.valueOf(request.getParameter("vid"));
        String viName = request.getParameter("viName");
        String singer = request.getParameter("singer");
        String vcity = request.getParameter("vcity");
        Integer vyear = Integer.valueOf(request.getParameter("vyear"));
        Integer popular = Integer.valueOf(request.getParameter("popular"));
        //设置目标服务器路径
        String path = "E:\\bishe\\MV\\";
        //创建上传的文件对象：Part里面封装了请求中上传的文件参数信息
        //图片
        Part photo = request.getPart("vphoto");
        long size1 = photo.getSize();
        if (size1 != 0) {//上传新的图片进行修改
            //获取上传的文件名称:
            String vphoto = photo.getSubmittedFileName();
            //将要上传到的路径名组合起来
            String newFileName = path + vphoto;
            //将文件上传到目标服务器:目前文件的路径及文件名称
            photo.write(newFileName);
            //将其封装成一个对象
            Video video = new Video(vid, vyear, popular, viName, vphoto, singer, vcity);
            //调用方法来进行修改
            int row = videoService.updateVideo(video);
        } else {//图片不发生变化
            //将其封装成一个对象
            Video video = new Video(vid, vyear, popular, viName, singer, vcity);
            //调用方法来进行修改
            int row = videoService.updateVideo(video);
        }
    }


}
