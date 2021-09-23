package com.xyxy.dao.impl;

import com.xyxy.dao.BaseDao;
import com.xyxy.dao.CommentDao;
import com.xyxy.pojo.Comment;
import com.xyxy.util.PageUtil;

import java.util.List;

public class CommentDaoImpl extends BaseDao<Comment> implements CommentDao {
    //进行添加评论
    @Override
    public int insertCom(Comment com) {
        String sql = "INSERT INTO songcomment(uid,sid,comtext,comtime) \n" +
                "VALUES (?,?,?,?) ;";
        int row = update(sql, com.getUid(), com.getSid(), com.getComtext(), com.getComtime());
        return row;
    }

    //查询所有评论数量
    @Override
    public Integer queryCount() {
        String sql = "SELECT COUNT(*) FROM songcomment";
        int count = getNumber(sql);
        return count;
    }

    //分页显示
    @Override
    public List<Comment> queryComByPage(PageUtil<Comment> pageUtil) {
        String sql = "SELECT * FROM songcomment LIMIT ?,?";
        List<Comment> comList = getBeanList(sql, pageUtil.getStartIndex(), pageUtil.getPageSize());
        return comList;
    }

    //根据id删除单个数据
    @Override
    public int deleteById(Integer comid) {
        String sql = "DELETE FROM songcomment WHERE comid=?";
        int row = update(sql, comid);
        return row;
    }

    //根据comid查询
    @Override
    public Comment findComById(Integer comid) {
        String sql = "SELECT * FROM songcomment WHERE comid=?";
        Comment bean = getBean(sql, comid);
        return bean;
    }

    //修改维护评论
    @Override
    public int updateCom(Comment co) {
        String sql = "UPDATE songcomment SET uid = ?,sid = ?,comtext= ?,comtime = ?  WHERE comid =?  ;";
        int row = update(sql, co.getUid(), co.getSid(), co.getComtext(), co.getComtime(), co.getComid());
        return row;
    }

    //根据歌曲id查询评论
    @Override
    public List<Comment> queryComById(Integer sid) {
        String sql = "SELECT * FROM songcomment WHERE sid=?";
        List<Comment> comments = getBeanList(sql, sid);
        return comments;
    }

    //根据评论内容来删除数据
    @Override
    public int deleteByComtime(String comtime) {
        String sql = "DELETE FROM songcomment WHERE comtime=?";
        int row = update(sql, comtime);
        return row;
    }
}
