package com.xyxy.dao.impl;

import com.xyxy.dao.BaseDao;
import com.xyxy.dao.SingerDao;
import com.xyxy.pojo.Singer;
import com.xyxy.util.PageUtil;

import java.util.List;

public class SingerDaoImpl extends BaseDao<Singer> implements SingerDao {
    //增加歌手信息
    @Override
    public int insertSinger(Singer s) {
        String sql = "INSERT INTO singer (\n" +
                "  singerName,\n" +
                "  gender,\n" +
                "  photo,\n" +
                "  descp,\n" +
                "  typeName,\n" +
                "  firstCode,\n" +
                "  popular\n" +
                ") " +
                " VALUES (?,?,?,?,?,?,?)";
        int row = update(sql, s.getSingerName(), s.getGender(), s.getPhoto(), s.getDescp(), s.getTypeName(), s.getFirstCode()
                , s.getPopular());
        return row;
    }

    //根据歌手名来查询是否有重复的歌手
    @Override
    public Singer checkBySingerName(String singerName) {
        String sql = "SELECT * FROM singer WHERE singerName=?";
        Singer singer = getBean(sql, singerName);
        return singer;
    }

    //查询所有的歌曲数量
    @Override
    public Integer queryCount() {
        String sql = "SELECT COUNT(*) FROM singer";
        int number = getNumber(sql);
        return number;
    }
    //1.根据性别来查询总数
    @Override
    public Integer queryCount1(Integer gender) {
        String sql = "SELECT COUNT(*) FROM singer WHERE gender=?";
        int number = getNumber(sql,gender);
        return number;
    }
    //2.根据歌手名，性别来查询总数
    @Override
    public Integer queryCount2(String singerName, Integer gender) {
        String sql = "SELECT COUNT(*) FROM singer WHERE singerName=? AND gender=? ";
        int number = getNumber(sql,singerName,gender);
        return number;
    }
    //3.根据歌手名来查询总数
    @Override
    public Integer queryCount3(String singerName) {
        String sql = "SELECT COUNT(*) FROM singer WHERE singerName=?";
        int number = getNumber(sql,singerName);
        return number;
    }
    //查询所有歌手不分页
    @Override
    public List<Singer> querySong() {
        String sql = "SELECT * FROM singer ORDER BY popular DESC";
        List<Singer> beanList = getBeanList(sql);
        return beanList;
    }
    //查询华语男歌手歌手并显示
    @Override
    public List<Singer> queryManSinger() {
        String sql = "SELECT * FROM singer WHERE gender=1";
        List<Singer> beanList = getBeanList(sql);
        return beanList;
    }
    //查询华语男歌手歌手并显示
    @Override
    public List<Singer> queryWomanSinger() {
        String sql = "SELECT * FROM singer WHERE gender=0";
        List<Singer> beanList = getBeanList(sql);
        return beanList;
    }
    //查询华语组合歌手并显示
    @Override
    public List<Singer> queryZhSinger() {
        String sql = "SELECT * FROM singer WHERE typeName='华语组合'";
        List<Singer> beanList = getBeanList(sql);
        return beanList;
    }

    @Override
    public List<Singer>  querySingerByCode(String firstCode) {
        String sql="SELECT * FROM singer WHERE firstCode=?";
        List<Singer> beanList = getBeanList(sql, firstCode);
        return beanList;
    }


    //将查询出来的歌手数据按照分页进行划分
    @Override
    public List<Singer> querySongByPage(PageUtil<Singer> pageUtil) {
        String sql = "SELECT * FROM singer LIMIT ?,?";
        List<Singer> beanList = getBeanList(sql, pageUtil.getStartIndex(), pageUtil.getPageSize());
        return beanList;
    }

    //根据id查询歌手
    @Override
    public Singer checkById(Integer srid) {
        String sql = "SELECT * FROM singer WHERE srid=?";
        Singer singer = getBean(sql, srid);
        return singer;

    }

    //修改歌手信息
    @Override
    public int updateSinger(Singer s) {
        int row = 0;
        if (s.getPhoto() == null) {
            String sql = "UPDATE singer SET singerName = ?,\n" +
                    "  gender = ?,\n" +
                    "  descp  = ?,\n" +
                    "  typeName = ?,\n" +
                    "  firstCode = ?,\n" +
                    "  popular = ? \n" +
                    "WHERE srid = ? ;";
            row = update(sql, s.getSingerName(), s.getGender(), s.getDescp(), s.getTypeName(),
                    s.getFirstCode(), s.getPopular(), s.getSrid());
        } else {
            String sql = "UPDATE singer SET singerName = ?,\n" +
                    "  gender = ?,\n" +
                    "  photo = ?,\n" +
                    "  descp  = ?,\n" +
                    "  typeName = ?,\n" +
                    "  firstCode = ?,\n" +
                    "  popular = ? \n" +
                    "WHERE srid = ? ;";
            row = update(sql, s.getSingerName(), s.getGender(), s.getPhoto(), s.getDescp(), s.getTypeName(),
                    s.getFirstCode(), s.getPopular(), s.getSrid());
        }
        return row;
    }

    //根据id来删除单个歌手数据
    @Override
    public int deleteById(Integer srid) {
        String sql = "DELETE FROM singer WHERE srid=?";
        int row = update(sql, srid);
        return row;
    }

    //1.通过性别来查询
    @Override
    public List<Singer> selectBySome(Integer gender,PageUtil<Singer> pageUtil) {
        String sql="SELECT * FROM singer WHERE gender=?  LIMIT ?,? ;";
        List<Singer> singerList = getBeanList(sql, gender,pageUtil.getStartIndex(), pageUtil.getPageSize());
        return singerList;
    }

    //2.通过歌手名，性别来查询
    @Override
    public List<Singer> selectBySome1(String singerName, Integer gender,PageUtil<Singer> pageUtil) {
        String sql="SELECT *FROM singer WHERE singerName=? AND gender=? LIMIT ?,? ";
        List<Singer> singerList = getBeanList(sql, singerName,gender,pageUtil.getStartIndex(), pageUtil.getPageSize());
        return singerList;
    }

    //3.根据歌手名来查询
    @Override
    public List<Singer> selectBySome2(String singerName,PageUtil<Singer> pageUtil) {
        String sql="SELECT *FROM singer WHERE singerName=? LIMIT ?,? ";
        List<Singer> singerList = getBeanList(sql, singerName,pageUtil.getStartIndex(), pageUtil.getPageSize());
        return singerList;
    }
}
