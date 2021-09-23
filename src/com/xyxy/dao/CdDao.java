package com.xyxy.dao;

import com.xyxy.pojo.Cd;
import com.xyxy.util.PageUtil;

import java.util.List;

public interface CdDao {
    //查询所有的专辑数量
    Integer queryCount();
    //1.通过歌手名来查询总数
    Integer queryCount1(String singer);
    //2.通过歌手名，专辑名来查询总数
    Integer queryCount2(String cdName, String singer);
    //3.通过专辑名来查询总数
    Integer queryCount3(String cdName);

    //将查询到的数据进行分页显示
    List<Cd> queryCdByPage(PageUtil<Cd> pageUtil);

    //增加专辑数据
    int insertCd(Cd cd);

    //通过专辑名称查询是否有重复的
    Cd checkByCdName(String cdName);

    //根据id删除单个数据
    int deleteById(Integer cid);

    //1.通过歌手来查询
    List<Cd> selectBySome1(String singer, PageUtil<Cd> pageUtil);
    //2.通过歌手和专辑名来查询
    List<Cd> selectBySome2(String cdName, String singer, PageUtil<Cd> pageUtil);
    //3.根据歌手名来查询
    List<Cd> selectBySome3(String cdName, PageUtil<Cd> pageUtil);

    //根据专辑号来查询要修改的专辑
    Cd fingCdById(Integer cid);

    //修改专辑信息
    int updateCd(Cd cd);


    //查询所有的专辑并显示
    List<Cd> queryCd();
    //根据地区查询所有歌专辑并显示
    List<Cd> queryCdByCity(String city);
    //根据热门查询所有歌专辑并显示
    List<Cd> queryCdByPopular(Integer popular);
    //根据年代查询所有歌MV并显示
    List<Cd> queryCdByCyear(Integer cyear);
}
