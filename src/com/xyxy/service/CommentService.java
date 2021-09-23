package com.xyxy.service;

import com.xyxy.dao.CommentDao;
import com.xyxy.dao.impl.CommentDaoImpl;
import com.xyxy.pojo.Comment;
import com.xyxy.util.PageUtil;

import java.util.List;

public class CommentService {
    private CommentDao commentDao = new CommentDaoImpl();

    //进行添加评论
    public int insertCom(Comment comment) {
        return commentDao.insertCom(comment);
    }

    //查询所有的评论数目
    public Integer queryCount() {
        return commentDao.queryCount();
    }

    //分页显示
    public List<Comment> queryComByPage(PageUtil<Comment> pageUtil) {
        return commentDao.queryComByPage(pageUtil);
    }

    //根据id删除单个数据
    public int deleteById(Integer comid) {
        return commentDao.deleteById(comid);
    }
    //根据ids集合批量删除数据
    public int batchDelete(String ids) {
        int num=0;
        String[] sidStr = ids.split(",");
        for(String sids:sidStr){
            Integer comid = Integer.valueOf(sids);
            //调用删除方法
            int row = commentDao.deleteById(comid);
            num+=row;
        }
        return num;
    }
    //根据comid查询
    public Comment findComById(Integer comid) {
        return commentDao.findComById(comid);
    }
    //修改维护评论
    public int updateCom(Comment comment) {
        return commentDao.updateCom(comment);
    }

    //根据歌曲id来查询评论
    public List<Comment> queryComBySid(Integer sid) {
        return commentDao.queryComById(sid);
    }

    //根据评论内容删除单个数据
    public int deleteByComtime(String comtime) { return commentDao.deleteByComtime(comtime);    }
}
