package com.xyxy.service;

import com.xyxy.dao.VideoDao;
import com.xyxy.dao.impl.VideoDaoImpl;
import com.xyxy.pojo.Video;
import com.xyxy.util.PageUtil;

import java.util.List;

public class VideoService {
    private VideoDao videoDao = new VideoDaoImpl();

    //增加MV视频
    public int insertVideo(Video video) {
        return videoDao.insertVideo(video);
    }

    //根据MV名称查询是否重复
    public Video checkByViName(String viName) {
        return videoDao.checkByName(viName);
    }

    //查询所有的MV数量
    public Integer queryCount() {
        return videoDao.queryCount();
    }

    //进行分页显示
    public List<Video> queryVideoByPage(PageUtil<Video> pageUtil) {
        return videoDao.queryVideoByPage(pageUtil);
    }

    //根据条件来查询所有符合条件的MV数量
    public Integer queryCountNum(String singer, String viName) {
        Integer count = 0;
        if (viName.trim().equals("")) {//搜索框中MV名为空
            if (!singer.trim().equals("")) {//搜索框中的歌手名不为空
                //1.通过歌手名来查询总数
                count = videoDao.queryCount1(singer);
            }
        } else {
            if (!singer.trim().equals("")) {//搜索框中的歌手名不为空
                //2.通过歌手名，MV名来查询总数
                count = videoDao.queryCount2(viName, singer);
            } else {
                //3.根据MV名来查询总数
                count = videoDao.queryCount3(viName);
            }
        }
        return count;
    }

    public List<Video> selectVideoBySome(String singer, String viName, PageUtil<Video> pageUtil) {
        List<Video> videos=null;
        if(viName.trim().equals("")){//MV名为空
            if(!singer.trim().equals("")){//歌手名不为空
                //1.根据歌手名查询
                videos=videoDao.selectVideoBySinger(singer,pageUtil);
            }
        }else{//MV名不为空
            if(!singer.trim().equals("")){//歌手名不为空
                //2.根据歌手名和MV名称来查询
                videos=videoDao.selectVideoBySvi(singer,viName,pageUtil);
            }else {//歌手名为空
                //3.根据MV名称来查询
                videos=videoDao.selectVideoByViname(viName,pageUtil);
            }
        }
        return videos;
    }

    //根据id进行单个查询,然后删除
    public int deleteById(Integer vid) {
        return  videoDao.deleteById(vid);
    }
    //根据ids集合来进行批量删除的操作
    public int batchDelete(String ids) {
        int num=0;
        String[] idsStr=ids.split(",");
        for(String vidStr: idsStr){
            Integer vid = Integer.valueOf(vidStr);
            //调用删除的方法
            int row = videoDao.deleteById(vid);
            num+=row;
        }
        return num;
    }
    //根据mvid来查询要修改的专辑
    public Video findVideoById(Integer vid) {
        return videoDao.checkByVid(vid);
    }
    //修改MV信息
    public int updateVideo(Video video) {
        return videoDao.updateVideo(video);
    }


    //查询所有歌MV并显示
    public List<Video> queryVideo() {
        return videoDao.queryVideo();
    }
    //根据地区查询所有歌MV并显示
    public List<Video> queryByVcity(String vcity) { return videoDao.queryByVcity(vcity); }
    //根据是否热门查询所有歌MV并显示
    public List<Video> queryPopular(Integer popular) {
        return videoDao.queryPopular(popular);
    }
    //根据年代查询所有歌MV并显示
    public List<Video> queryVyear(Integer vyear) {return videoDao.queryByVyear(vyear);    }
}
