package com.xyxy.dao;

import com.xyxy.pojo.Comment;
import com.xyxy.util.PageUtil;

import java.util.List;

public interface CommentDao {

    //进行添加评论
    int insertCom(Comment comment);
    //查询所有的评论数目
    Integer queryCount();
    //分页显示
    List<Comment> queryComByPage(PageUtil<Comment> pageUtil) ;

    //根据id删除单个数据
    int deleteById(Integer comid);

    //根据comid查询
    Comment findComById(Integer comid);
    //修改维护评论
    int updateCom(Comment comment);

    //根据歌曲id查询评论
    List<Comment> queryComById(Integer sid);

    //根据评论内容删除单个数据
    int deleteByComtime(String comtime);
}
