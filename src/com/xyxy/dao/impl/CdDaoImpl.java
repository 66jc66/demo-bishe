package com.xyxy.dao.impl;

import com.xyxy.dao.BaseDao;
import com.xyxy.dao.CdDao;
import com.xyxy.pojo.Cd;
import com.xyxy.util.PageUtil;

import java.util.List;

public class CdDaoImpl extends BaseDao<Cd> implements CdDao {
    //查询成都表中的所有专辑数量
    @Override
    public Integer queryCount() {
        String sql = "SELECT COUNT(*) FROM cd";
        int count = getNumber(sql);
        return count;
    }

    //1.通过歌手名来查询总数
    @Override
    public Integer queryCount1(String singer) {
        String sql = "SELECT COUNT(*) FROM cd WHERE singer=?";
        int count = getNumber(sql, singer);
        return count;
    }

    //2.通过歌手名，专辑名来查询总数
    @Override
    public Integer queryCount2(String cdName, String singer) {
        String sql = "SELECT COUNT(*) FROM cd WHERE cdName=? AND singer=? ";
        int count = getNumber(sql, cdName, singer);
        return count;
    }

    //3.通过专辑名来查询总数
    @Override
    public Integer queryCount3(String cdName) {
        String sql = "SELECT COUNT(*)  FROM cd WHERE cdName=?";
        int count = getNumber(sql, cdName);
        return count;
    }

    //将查询到的数据进行分页显示
    @Override
    public List<Cd> queryCdByPage(PageUtil<Cd> pageUtil) {
        String sql = "SELECT * FROM cd LIMIT ?,?";
        List<Cd> cdList = getBeanList(sql, pageUtil.getStartIndex(), pageUtil.getPageSize());
        return cdList;
    }

    //增加专辑数据
    @Override
    public int insertCd(Cd cd) {
        String sql = "INSERT INTO cd (cdName,cphoto,city,cyear,singer,popular)" +
                "VALUES (?,?,?,?,?,?)";
        int row = update(sql, cd.getCdName(), cd.getCphoto(), cd.getCity(), cd.getCyear(), cd.getSinger(), cd.getPopular());
        return row;
    }

    //通过专辑名称查询是否有重复的
    @Override
    public Cd checkByCdName(String cdName) {
        String sql = "SELECT * FROM cd WHERE cdName=?";
        Cd cd = getBean(sql, cdName);
        return cd;
    }

    //根据id删除单个数据
    @Override
    public int deleteById(Integer cid) {
        String sql = "DELETE FROM cd WHERE cid=?";
        int row = update(sql, cid);
        return row;
    }

    @Override
    //1.通过歌手来查询
    public List<Cd> selectBySome1(String singer, PageUtil<Cd> pageUtil) {
        String sql = "SELECT * FROM cd WHERE singer=? LIMIT ?,?";
        List<Cd> cdList = getBeanList(sql, singer, pageUtil.getStartIndex(), pageUtil.getPageSize());
        return cdList;
    }

    @Override
    //2.通过歌手和专辑名来查询
    public List<Cd> selectBySome2(String cdName, String singer, PageUtil<Cd> pageUtil) {
        String sql = "SELECT * FROM cd WHERE cdName=? AND singer=? LIMIT ?,?";
        List<Cd> cdList = getBeanList(sql, cdName, singer, pageUtil.getStartIndex(), pageUtil.getPageSize());
        return cdList;
    }

    @Override
    //3.根据歌手名来查询
    public List<Cd> selectBySome3(String cdName, PageUtil<Cd> pageUtil) {
        String sql = "SELECT * FROM cd WHERE cdName=?  LIMIT ?,?";
        List<Cd> cdList = getBeanList(sql, cdName, pageUtil.getStartIndex(), pageUtil.getPageSize());
        return cdList;
    }

    //根据专辑号来查询要修改的专辑
    @Override
    public Cd fingCdById(Integer cid) {
        String sql = "SELECT * FROM cd WHERE cid=?";
        Cd cd = getBean(sql, cid);
        return cd;
    }

    //修改专辑信息
    @Override
    public int updateCd(Cd cd) {
        int row = 0;
        if (cd.getCphoto() == null) {//当没有图片上传时就使用原来的图片不发生改变
            String sql = "UPDATE cd SET cdName=?,singer=?,city=?,cyear=?,popular=? WHERE cid=?";
            row = update(sql, cd.getCdName(), cd.getSinger(), cd.getCity(), cd.getCyear(),
                    cd.getPopular(), cd.getCid());
        } else {//若有图片上传的时候就会进行修改使用新的图片
            String sql = "UPDATE cd SET cdName=?,singer=?,city=?,cyear=?,popular=?,cphoto=? WHERE cid=?";
            row = update(sql, cd.getCdName(), cd.getSinger(), cd.getCity(), cd.getCyear(),
                    cd.getPopular(), cd.getCphoto(), cd.getCid());
        }
        return row;
    }


    //查询所有的专辑并显示
    @Override
    public List<Cd> queryCd() {
        String sql="SELECT *FROM cd";
        List<Cd> cdList = getBeanList(sql);
        return cdList;
    }
    //根据地区查询所有歌专辑并显示
    @Override
    public List<Cd> queryCdByCity(String city) {
        String sql="SELECT *FROM cd WHERE city=?";
        List<Cd> cdList = getBeanList(sql,city);
        return cdList;
    }
    //根据热门查询所有歌专辑并显示
    @Override
    public List<Cd> queryCdByPopular(Integer popular) {
        String sql="SELECT *FROM cd WHERE popular=?";
        List<Cd> cdList = getBeanList(sql,popular);
        return cdList;
    }
    //根据年代查询所有歌MV并显示
    public List<Cd> queryCdByCyear(Integer cyear) {
        String sql="SELECT *FROM cd WHERE cyear=?";
        List<Cd> cdList = getBeanList(sql,cyear);
        return cdList;
    }
}
