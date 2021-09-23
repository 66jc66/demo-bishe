package com.xyxy.dao;

import com.xyxy.pojo.Video;
import com.xyxy.util.PageUtil;

import java.util.List;

public interface VideoDao {
    //增加MV视频
    int insertVideo(Video video);

    //查询所有的MV数量
    Integer queryCount();
    //1.通过歌手名来查询总数
    Integer queryCount1(String singer);
    //2.通过歌手名，MV名来查询总数
    Integer queryCount2(String viName, String singer);
    //3.根据MV名来查询总数
    Integer queryCount3(String viName);


    //根据MV名称查询是否重复
    Video checkByName(String viName);
    //进行分页显示
    List<Video> queryVideoByPage(PageUtil<Video> pageUtil);
    //1.根据歌手名查询
    List<Video> selectVideoBySinger(String singer,PageUtil<Video> pageUtil);
    //2.根据歌手名和MV名称来查询
    List<Video> selectVideoBySvi(String singer, String viName,PageUtil<Video> pageUtil);
    //3.根据MV名称来查询
    List<Video> selectVideoByViname(String viName,PageUtil<Video> pageUtil);

    //根据id进行单个查询,然后删除
    int deleteById(Integer vid);

    //根据mvid来查询要修改的专辑
    Video checkByVid(Integer vid);
    //修改MV信息
    int updateVideo(Video video);

    //查询所有歌MV并显示
    List<Video> queryVideo();
    //根据地区查询所有歌MV并显示
    List<Video> queryByVcity(String vcity);
    //根据是否热门查询所有歌MV并显示
    List<Video> queryPopular(Integer popular);
    //根据年代查询所有歌MV并显示
    List<Video> queryByVyear(Integer vyear);
}
