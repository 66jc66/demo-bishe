package com.xyxy.servlet;

import com.alibaba.fastjson.JSON;
import com.xyxy.pojo.ResultInfo;
import com.xyxy.pojo.Singer;
import com.xyxy.pojo.Song;
import com.xyxy.service.SingerService;
import com.xyxy.util.PageUtil;
import com.xyxy.util.UploadUtil;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.util.List;

@WebServlet("/singer")
@MultipartConfig
public class SingerServlet extends BaseServlet {
    private SingerService singerService = new SingerService();
    private ResultInfo resultInfo = new ResultInfo();

    //增加歌手信息
    public void insertSinger(HttpServletRequest request, HttpServletResponse response) {
        try {
            //获取添加页面的数据
            String singerName = request.getParameter("singerName");
            String genderStr = request.getParameter("gender");
            Integer gender = Integer.valueOf(genderStr);
            String descp = request.getParameter("descp");
            String typeName = request.getParameter("typeName");
            String firstCode = request.getParameter("firstCode");
            String popularStr = request.getParameter("popular");
            Integer popular = Integer.valueOf(popularStr);
            //设置目标服务器路径
            String path = "E:\\bishe\\imgs\\";
            //创建上传的文件对象：Part里面封装了请求中上传的文件参数信息
            Part part = request.getPart("photo");
            //获取上传的文件名称:
            String photo = part.getSubmittedFileName();
            //将要上传到的路径名组合起来
            String newFileName = path + photo;
            //将文件上传到目标服务器:目前文件的路径及文件名称
            part.write(newFileName);
            //封装成一个对象
            Singer singer = new Singer(singerName, gender, photo, descp, typeName, firstCode, popular);
            //调用service中的添加方法
            int row = singerService.insertSinger(singer);
            if (row > 0) {
                response.sendRedirect("layuimini/page/singer_add.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据歌手名字来验证是否有重复的
    public void checkBySingerName(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取歌手的名字
        String singerName = request.getParameter("singerName");
        //调用service中的方法来查询是否有重复的歌手
        Singer singer = singerService.checkBySingerName(singerName);
        if (singer == null) {
            //如果为空就传一个可以执行的值ok
            response.getWriter().write(JSON.toJSONString("ok"));
        } else {
            //如果不为空就传递一个fail
            response.getWriter().write(JSON.toJSONString("fail"));
        }
    }

    //查询所有的歌曲
    public void querySingerAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取页面中要分页的数量以及一页中的数量
        Integer limit = Integer.valueOf(request.getParameter("limit"));
        String page = request.getParameter("page");
        //查询所有的歌手数量
        Integer count = singerService.queryCount();
        PageUtil<Singer> pageUtil = new PageUtil<Singer>(count, page, limit);
        //调用service方法来进行分页查询
        List<Singer> singersList = singerService.querySingerByPage(pageUtil);
        //设置返回结果信息
        resultInfo.setCode(0);
        resultInfo.setCount(count);
        resultInfo.setResult(singersList);
        response.getWriter().write(JSON.toJSONString(resultInfo));
    }


    //根据id查询歌手
    public void findById(HttpServletRequest request,
                         HttpServletResponse response) throws Exception {
        //获取传递过来的srid
        Integer srid = Integer.valueOf(request.getParameter("srid"));
        //调用service方法查询对象
        Singer singer = singerService.checkById(srid);
        //将查询到的对象传入到作用域中
        HttpSession session = request.getSession();
        session.setAttribute("singer", singer);
        request.getRequestDispatcher("layuimini/page/singer_edit.jsp").forward(request, response);
    }

    //修改歌手信息
    public void updateSinger(HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        //获取修改页面的数据
        Integer srid = Integer.valueOf(request.getParameter("srid"));
        String singerName = request.getParameter("singerName");
        String descp = request.getParameter("descp");
        String typeName = request.getParameter("typeName");
        String firstCode = request.getParameter("firstCode");
        Integer gender = Integer.valueOf(request.getParameter("gender"));
        Integer popular = Integer.valueOf(request.getParameter("popular"));
        //上传图片,保存图片名称
        Part part = request.getPart("photo");
        long len = part.getSize();
        if (len == 0) {//不用修改歌手图片
            //将歌手参数封装成对象
            Singer singer = new Singer(srid, singerName, gender, descp, typeName, firstCode, popular);
            //调用service方法来进行更改
            int row = singerService.updateSinger(singer);
        } else {
            //设置目标服务器路径
            String path = "E:\\bishe\\imgs\\";
            //获取上传的文件名称:
            String photo = part.getSubmittedFileName();
            //将要上传到的路径名组合起来
            String newFileName = path + photo;
            //将文件上传到目标服务器:目前文件的路径及文件名称
            part.write(newFileName);
            //将歌手参数封装成对象
            Singer singer = new Singer(srid, singerName, gender, photo, descp, typeName, firstCode, popular);
            //调用service方法来进行更改
            int row = singerService.updateSinger(singer);
        }
    }

    //根据id删除单个歌手信息
    public void deleteById(HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
        //获取要删除的单个数据id
        String sridStr = request.getParameter("srid");
        Integer srid = Integer.valueOf(sridStr);
        //调用service方法来进行删除
        int row = singerService.deleteById(srid);
        if (row > 0) {
            //删除成功，返回200
            response.getWriter().write(JSON.toJSONString(200));
        } else {
            //删除失败，返回201
            response.getWriter().write(JSON.toJSONString(201));
        }
    }

    //批量删除歌手信息
    public void batchDelete(HttpServletRequest request,
                            HttpServletResponse response) throws Exception{
        //获取要删除的id集合
        String ids = request.getParameter("ids");
        //调用service中的方法删除信息
        int row =singerService.batchDelete(ids);
        if (row > 0) {
            //删除成功，返回200
            response.getWriter().write(JSON.toJSONString("200"));
        } else {
            //删除失败，返回201
            response.getWriter().write(JSON.toJSONString("201"));
        }
    }

    //根据歌手名和性别来查询
    public void selectBySome(HttpServletRequest request,
                             HttpServletResponse response) throws Exception{
        //获取到要查询的歌手名和性别
        String singerName = request.getParameter("singerName");
        String genderStr = request.getParameter("gender");
        //获取页面中要分页的数量以及一页中的数量
        Integer limit = Integer.valueOf(request.getParameter("limit"));
        String page = request.getParameter("page");
        //查询所有的歌手数量
        Integer count = singerService.queryCount1(singerName,genderStr);
        PageUtil<Singer> pageUtil = new PageUtil<Singer>(count, page, limit);
        //调用service中的查询方法
        List<Singer> singerList=singerService.selectBySome(singerName,genderStr,pageUtil);
        //设置状态和数据，并将查询到的数据显示出来
        resultInfo.setCode(0);
        resultInfo.setCount(count);
        resultInfo.setResult(singerList);
        response.getWriter().write(JSON.toJSONString(resultInfo));
    }
}
