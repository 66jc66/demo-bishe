package com.xyxy.servlet;

import com.alibaba.fastjson.JSON;
import com.xyxy.pojo.Cd;
import com.xyxy.pojo.Comment;
import com.xyxy.pojo.ResultInfo;
import com.xyxy.pojo.Song;
import com.xyxy.service.CommentService;
import com.xyxy.service.SongService;
import com.xyxy.util.PageUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/com")
public class CommentServlet extends BaseServlet {
    private CommentService commentService = new CommentService();
    private ResultInfo resultInfo=new ResultInfo();
    private SongService songService=new SongService();

    //进行添加评论
    public void insertComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取用户，歌曲值，评论的值
        Integer uid = Integer.valueOf(request.getParameter("uid"));
        Integer sid = Integer.valueOf(request.getParameter("sid"));
        String comtext = request.getParameter("comtext");
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String comtime = temp.format(date);//评论时间
        //构建一个新的对象
        Comment comment = new Comment(uid, sid, comtext, comtime);
        //调用service方法增加
        int row = commentService.insertCom(comment);
        if(row>0){
            //增加评论成功，歌曲评论数量加一
            Song song=new Song(sid);
           int count= songService.updateCom(song);
            response.getWriter().write(JSON.toJSONString("ok"));
        }
    }

    //查询所有的评论
    public void queryComAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取页面中要分页的数量以及一页中的数量
        Integer limit = Integer.valueOf(request.getParameter("limit"));
        String page = request.getParameter("page");
        //查询所有的评论数目
        Integer count = commentService.queryCount();
        PageUtil<Comment> pageUtil = new PageUtil<Comment>(count, page, limit);
        //调用service方法来进行分页查询
        List<Comment> comList = commentService.queryComByPage(pageUtil);
        //设置返回结果信息
        resultInfo.setCode(0);
        resultInfo.setCount(count);
        resultInfo.setResult(comList);
        response.getWriter().write(JSON.toJSONString(resultInfo));
    }

    //根据id删除单个数据
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取要删除的comid值
        Integer comid = Integer.valueOf(request.getParameter("comid"));
        //调用service方法来进行操作
        int row = commentService.deleteById(comid);
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
        int row = commentService.batchDelete(ids);
        if (row > 0) {
            //删除成功，返回200
            response.getWriter().write(JSON.toJSONString("200"));
        } else {
            //删除失败，返回201
            response.getWriter().write(JSON.toJSONString("201"));
        }
    }

    //根据comid查询评论维护评论
    public void findComById(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
            //获取修改更改的id
            Integer comid = Integer.valueOf(request.getParameter("comid"));
            //调用service方法来进行查询评论信息
            Comment comment = commentService.findComById(comid);
            //将查询到的对象传入到作用域中
            HttpSession session = request.getSession();
            session.setAttribute("comment", comment);
            request.getRequestDispatcher("layuimini/page/comment_edit.jsp").forward(request, response);
    }

    //修改维护评论
    public void updateCom(HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
        //获取参数
        Integer comid = Integer.valueOf(request.getParameter("comid"));
        String comtext = request.getParameter("comtext");
        String comtime = request.getParameter("comtime");
        Integer uid = Integer.valueOf(request.getParameter("uid"));
        Integer sid = Integer.valueOf(request.getParameter("sid"));
        //构建新的对象
        Comment comment=new Comment(comid,uid,sid,comtext,comtime);
        //调用service方法
        int row=commentService.updateCom(comment);
    }

    //根据评论内容删除单个数据
    public void deleteByComtime(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取要删除的comtext值
        String comtime = request.getParameter("comtime");
        Integer sid = Integer.valueOf(request.getParameter("sid"));
        //调用service方法来进行操作
        int row = commentService.deleteByComtime(comtime);
        if (row > 0) {
            //删除成功，返回200
            response.getWriter().write(JSON.toJSONString(200));
            //删除评论成功，歌曲评论数量减一
            Song song=new Song(sid);
            int count= songService.updateComment(song);
        } else {
            //删除失败，返回201
            response.getWriter().write(JSON.toJSONString(201));
        }
    }
}
