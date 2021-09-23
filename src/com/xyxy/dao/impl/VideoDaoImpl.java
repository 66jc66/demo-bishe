package com.xyxy.dao.impl;

import com.xyxy.dao.BaseDao;
import com.xyxy.dao.VideoDao;
import com.xyxy.pojo.Video;
import com.xyxy.util.PageUtil;

import java.util.List;

public class VideoDaoImpl extends BaseDao<Video> implements VideoDao {

    //增加MV视频
    @Override
    public int insertVideo(Video video) {
        String sql = "INSERT INTO video (viName,singer,vcity,vyear,popular,vphoto,viurl) " +
                "VALUES (?,?,?,?,?,?,?)";
        int row = update(sql, video.getViName(), video.getSinger(), video.getVcity(),
                video.getVyear(), video.getPopular(), video.getVphoto(), video.getViurl());
        return row;
    }

    //根据MV名称查询是否重复
    @Override
    public Video checkByName(String viName) {
        String sql = "SELECT * FROM video WHERE viName=?";
        Video video = getBean(sql, viName);
        return video;
    }

    //查询所有的MV数量
    @Override
    public Integer queryCount() {
        String sql = "SELECT COUNT(*) FROM video";
        int count = getNumber(sql);
        return count;
    }

    //1.通过歌手名来查询总数
    @Override
    public Integer queryCount1(String singer) {
        String sql = "SELECT COUNT(*) FROM video WHERE singer=?";
        int count = getNumber(sql, singer);
        return count;
    }

    //2.通过歌手名，MV名来查询总数
    @Override
    public Integer queryCount2(String viName, String singer) {
        String sql = "SELECT COUNT(*) FROM video WHERE singer=? AND viName=?";
        int count = getNumber(sql, singer, viName);
        return count;
    }

    //3.根据MV名来查询总数
    @Override
    public Integer queryCount3(String viName) {
        String sql = "SELECT COUNT(*) FROM video WHERE viName=?";
        int count = getNumber(sql, viName);
        return count;
    }

    //进行分页显示
    @Override
    public List<Video> queryVideoByPage(PageUtil<Video> pageUtil) {
        String sql = "SELECT * FROM video LIMIT ?,?";
        List<Video> videoList = getBeanList(sql, pageUtil.getStartIndex(), pageUtil.getPageSize());
        return videoList;
    }

    //1.根据歌手名查询
    @Override
    public List<Video> selectVideoBySinger(String singer, PageUtil<Video> pageUtil) {
        String sql = "SELECT * FROM video WHERE singer=? LIMIT ?,?";
        List<Video> videoList = getBeanList(sql, singer, pageUtil.getStartIndex(), pageUtil.getPageSize());
        return videoList;
    }

    //2.根据歌手名和MV名称来查询
    @Override
    public List<Video> selectVideoBySvi(String singer, String viName, PageUtil<Video> pageUtil) {
        String sql = "SELECT * FROM video WHERE singer=? AND viName=? LIMIT ?,?";
        List<Video> videoList = getBeanList(sql, singer, pageUtil.getStartIndex(), pageUtil.getPageSize());
        return videoList;
    }

    //3.根据MV名称来查询
    @Override
    public List<Video> selectVideoByViname(String viName, PageUtil<Video> pageUtil) {
        String sql = "SELECT * FROM video WHERE viName=? LIMIT ?,?";
        List<Video> videoList = getBeanList(sql, viName, pageUtil.getStartIndex(), pageUtil.getPageSize());
        return videoList;
    }

    //根据id进行单个查询,然后删除
    @Override
    public int deleteById(Integer vid) {
        String sql = "DELETE  FROM video WHERE vid=?";
        int row = update(sql, vid);
        return row;
    }

    //根据mvid来查询要修改的专辑
    @Override
    public Video checkByVid(Integer vid) {
        String sql = "SELECT * FROM video WHERE vid=? ";
        Video video = getBean(sql, vid);
        return video;
    }

    //修改MV信息
    @Override
    public int updateVideo(Video v) {
        Integer row = 0;
        if (v.getViurl() != null) {//图片不变
            String sql = "UPDATE video SET viName=?,singer=?,vcity=?,vyear=?,popular=? WHERE vid=?";
            row = update(sql, v.getViName(), v.getSinger(), v.getVcity(), v.getVyear(), v.getPopular()
                    , v.getVid());
        } else {//图片不进行重新更新
            String sql = "UPDATE video SET viName=?,singer=?,vcity=?,vyear=?,popular=? WHERE vid=?";
            row = update(sql, v.getViName(), v.getSinger(), v.getVcity(), v.getVyear(), v.getPopular()
                    , v.getVid());
        }
        return row;
    }

    //查询所有歌MV并显示
    @Override
    public List<Video> queryVideo() {
        String sql = "SELECT * FROM video";
        List<Video> videoList = getBeanList(sql);
        return videoList;
    }
    //根据地区查询所有歌MV并显示
    @Override
    public List<Video> queryByVcity(String vcity) {
        String sql = "SELECT * FROM video WHERE vcity=? ";
        List<Video> videoList = getBeanList(sql, vcity);
        return videoList;

    }
    //根据是否热门查询所有歌MV并显示
    @Override
    public List<Video> queryPopular(Integer popular) {
        String sql = "SELECT * FROM video WHERE popular=? ";
        List<Video> videoList = getBeanList(sql, popular);
        return videoList;
    }
    //根据年代查询所有歌MV并显示
    @Override
    public List<Video> queryByVyear(Integer vyear) {
        String sql = "SELECT * FROM video WHERE vyear=? ";
        List<Video> videoList = getBeanList(sql, vyear);
        return videoList;
    }

}
