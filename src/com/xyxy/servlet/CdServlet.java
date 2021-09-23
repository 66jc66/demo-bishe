package com.xyxy.servlet;

import com.alibaba.fastjson.JSON;
import com.xyxy.pojo.Cd;
import com.xyxy.pojo.ResultInfo;
import com.xyxy.service.CdService;
import com.xyxy.util.PageUtil;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.util.List;

@WebServlet("/cd")
@MultipartConfig
public class CdServlet extends BaseServlet {
    private CdService cdService = new CdService();
    private ResultInfo resultInfo = new ResultInfo();

    //查询所有的专辑数目
    public void queryCdAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取页面中要分页的数量以及一页中的数量
        Integer limit = Integer.valueOf(request.getParameter("limit"));
        String page = request.getParameter("page");
        //查询所有的专辑数量
        Integer count = cdService.queryCount();
        PageUtil<Cd> pageUtil = new PageUtil<Cd>(count, page, limit);
        //调用service方法来进行分页查询
        List<Cd> cdList = cdService.queryCdByPage(pageUtil);
        //设置返回结果信息
        resultInfo.setCode(0);
        resultInfo.setCount(count);
        resultInfo.setResult(cdList);
        response.getWriter().write(JSON.toJSONString(resultInfo));
    }

    //进行专辑的增加操作
    public void insertCd(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取输入框的值
        String cdName = request.getParameter("cdName");
        String city = request.getParameter("city");
        String singer = request.getParameter("singer");
        Integer cyear = Integer.valueOf(request.getParameter("cyear"));
        Integer popular = Integer.valueOf(request.getParameter("popular"));
        //设置目标服务器路径
        String path = "E:\\bishe\\imgs\\";
        //创建上传的文件对象：Part里面封装了请求中上传的文件参数信息
        Part part = request.getPart("cphoto");
        //获取上传的文件名称:
        String cphoto = part.getSubmittedFileName();
        //将要上传到的路径名组合起来
        String newFileName = path + cphoto;
        //将文件上传到目标服务器:目前文件的路径及文件名称
        part.write(newFileName);
        //封装成一个对象
        Cd cd = new Cd(cyear, popular, cdName, cphoto, city, singer);
        //调用service中的增加方法增加数据
        int row = cdService.insertCd(cd);
        if (row > 0) {
            response.sendRedirect("layuimini/page/cd_add.jsp");
        }
    }

    //根据专辑名称查询是否重复
    public void checkByCdName(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取专辑名称
        String cdName = request.getParameter("cdName");
        //调用service方法来检查是否重复
        Cd cd = cdService.checkByCdName(cdName);
        //判断是否查询到
        if (cd == null) {
            //如果为空就传一个可以执行的值ok
            response.getWriter().write(JSON.toJSONString("ok"));
        } else {
            //如果不为空就传递一个fail
            response.getWriter().write(JSON.toJSONString("fail"));
        }
    }

    //根据id删除单个数据
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取要删除的cid值
        Integer cid = Integer.valueOf(request.getParameter("cid"));
        //调用service方法来进行操作
        int row = cdService.deleteById(cid);
        if (row > 0) {
            //删除成功，返回200
            response.getWriter().write(JSON.toJSONString(200));
        } else {
            //删除失败，返回201
            response.getWriter().write(JSON.toJSONString(201));
        }
    }

    //根据ids集合批量删除数据
    public void batchDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取要删除的id集合
        String ids = request.getParameter("ids");
        //调用service中的方法删除信息
        int row = cdService.batchDelete(ids);
        if (row > 0) {
            //删除成功，返回200
            response.getWriter().write(JSON.toJSONString("200"));

        } else {
            //删除失败，返回201
            response.getWriter().write(JSON.toJSONString("201"));
        }
    }

    //根据专辑名或者歌手名来查询专辑信息
    public void selectBySome(HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        //获取搜素框中的字段信息
        String cdName = request.getParameter("cdName");
        String singer = request.getParameter("singer");
        //获取页面中要分页的数量以及一页中的数量
        Integer limit = Integer.valueOf(request.getParameter("limit"));
        String page = request.getParameter("page");
        //查询所有的歌手数量
        Integer count = cdService.queryCountNum(cdName, singer);
        PageUtil<Cd> pageUtil = new PageUtil<Cd>(count, page, limit);
        //调用service中的查询方法
        List<Cd> cdList = cdService.selectBySome(cdName, singer, pageUtil);
        //设置状态和数据，并将查询到的数据显示出来
        resultInfo.setCode(0);
        resultInfo.setCount(count);
        resultInfo.setResult(cdList);
        response.getWriter().write(JSON.toJSONString(resultInfo));
    }

    //根据专辑号来查询要修改的专辑
    public void findCdById(HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
        //获取修改更改的cid
        Integer cid = Integer.valueOf(request.getParameter("cid"));
        //调用service方法来进行查询专辑信息
        Cd cd = cdService.findCdById(cid);
        //将查询到的对象传入到作用域中
        HttpSession session = request.getSession();
        session.setAttribute("cd", cd);
        request.getRequestDispatcher("layuimini/page/cd_edit.jsp").forward(request, response);
    }


    //修改专辑信息
    public void updateCd(HttpServletRequest request,
                         HttpServletResponse response) throws Exception {
        //获取修改页面的数据
        Integer cid = Integer.valueOf(request.getParameter("cid"));
        String cdName = request.getParameter("cdName");
        String singer = request.getParameter("singer");
        String city = request.getParameter("city");
        Integer cyear = Integer.valueOf(request.getParameter("cyear"));
        Integer popular = Integer.valueOf(request.getParameter("popular"));
        //上传图片,保存图片名称
        Part part = request.getPart("cphoto");
        long len = part.getSize();
        if (len == 0) {//不用修改歌手图片
            //将专辑参数封装成对象
            Cd cd = new Cd(cid, cyear, popular, cdName, city, singer);
            //调用service方法来进行更改
            int row = cdService.updateCd(cd);
        } else {
            //设置目标服务器路径
            String path = "E:\\bishe\\imgs\\";
            //获取上传的文件名称:
            String cphoto = part.getSubmittedFileName();
            //将要上传到的路径名组合起来
            String newFileName = path + cphoto;
            //将文件上传到目标服务器:目前文件的路径及文件名称
            part.write(newFileName);
            //将专辑参数封装成对象
            Cd cd = new Cd(cid, cyear, popular, cdName, cphoto, city, singer);
            //调用service方法来进行更改
            int row = cdService.updateCd(cd);
        }
    }
}
