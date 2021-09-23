package com.xyxy.dao;

import com.xyxy.pojo.Singer;
import com.xyxy.util.PageUtil;

import java.util.List;

public interface SingerDao {
    //增加歌手信息
    int insertSinger(Singer singer);

    //根据歌手名来查询是否有重复的歌手
    Singer checkBySingerName(String singerName);

    //将查询出来的歌曲数据按照分页进行划分
    List<Singer> querySongByPage(PageUtil<Singer> pageUtil);

    //根据id查询歌手
    Singer checkById(Integer srid);

    //修改歌手信息
    int updateSinger(Singer singer);

    //根据id来删除单个歌手数据
    int deleteById(Integer srid);

    //1.通过性别来查询
    List<Singer> selectBySome(Integer gender,PageUtil<Singer> pageUtil);

    //2.通过歌手名，性别来查询
    List<Singer> selectBySome1(String singerName, Integer gender,PageUtil<Singer> pageUtil);

    //3.根据歌手名来查询
    List<Singer> selectBySome2(String singerName,PageUtil<Singer> pageUtil);

    //查询所有的歌曲数量
    Integer queryCount();

    //1.通过性别来查询
    Integer queryCount1(Integer gender);

    //2.通过歌手名，性别来查询总数
    Integer queryCount2(String singerName, Integer gender);

    //3.根据歌手名来查询总数
    Integer queryCount3(String singerName);

    //查询所有歌手不分页
    List<Singer> querySong();
    //查询华语男歌手歌手并显示
    List<Singer> queryManSinger();
    //查询华语女歌手歌手并显示
    List<Singer> queryWomanSinger();
    //根据歌手首字母来查询歌手
    List<Singer>  querySingerByCode(String firstCode);
}
