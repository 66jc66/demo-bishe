package com.xyxy.service;

import com.xyxy.dao.SingerDao;
import com.xyxy.dao.impl.SingerDaoImpl;
import com.xyxy.pojo.Singer;
import com.xyxy.util.PageUtil;

import java.util.List;

public class SingerService {
    private SingerDao singerDao = new SingerDaoImpl();

    //增加歌手
    public int insertSinger(Singer singer) {
        return singerDao.insertSinger(singer);
    }

    //根据歌手名来查询是否有重复的歌手
    public Singer checkBySingerName(String singerName) {
        return singerDao.checkBySingerName(singerName);
    }

    //查询所有的歌曲数量
    public Integer queryCount() {
        return singerDao.queryCount();
    }



    //将查询出来的歌曲数据按照分页进行划分
    public List<Singer> querySingerByPage(PageUtil<Singer> pageUtil) {
        return singerDao.querySongByPage(pageUtil);
    }

    //根据id查询歌手
    public Singer checkById(Integer srid) {
        return singerDao.checkById(srid);
    }

    //修改歌手信息
    public int updateSinger(Singer singer) {
        return singerDao.updateSinger(singer);
    }

    //根据id来删除单个歌手数据
    public int deleteById(Integer srid) {
        return singerDao.deleteById(srid);
    }

    //批量删除歌手信息
    public int batchDelete(String ids) {
        int num = 0;
        String[] idStr = ids.split(",");
        //遍历数组
        for (String singid : idStr) {
            Integer srid = Integer.valueOf(singid);
            //调用删除方法
            int row = singerDao.deleteById(srid);
            num += row;
        }
        return num;
    }

    //根据歌手名，性别查询相关歌手
    public List<Singer> selectBySome(String singerName, String genderStr, PageUtil<Singer> pageUtil) {
        List<Singer> singerList = null;
        Integer gender;
        if(singerName.trim().equals("")){//搜索框中歌手名为空
            if(!genderStr.trim().equals("")){//搜索框中的歌手性别不为空
                //判断输入的是男是女
                if (genderStr.equals("男")) {
                    gender = 1;
                } else {
                    gender = 0;
                }
                //1.通过性别来查询
                singerList=singerDao.selectBySome(gender,pageUtil);
            }
        }else{
            if(!genderStr.trim().equals("")){//搜索框中的歌手性别不为空
                //判断输入的是男是女
                if (genderStr.equals("男")) {
                    gender = 1;
                } else {
                    gender = 0;
                }
                //2.通过歌手名，性别来查询
                singerList=singerDao.selectBySome1(singerName,gender,pageUtil);
            }else {
                //3.根据歌手名来查询
                singerList=singerDao.selectBySome2(singerName,pageUtil);
            }
        }
        return singerList;
    }
    //根据歌手名或者是性别来查总数
    public Integer queryCount1(String singerName, String genderStr) {
        Integer count=0;
        Integer gender;
        if(singerName.trim().equals("")){//搜索框中歌手名为空
            if(!genderStr.trim().equals("")){//搜索框中的歌手性别不为空
                //判断输入的是男是女
                if (genderStr.equals("男")) {
                    gender = 1;
                } else {
                    gender = 0;
                }
                //1.通过性别来查询总数
                count=singerDao.queryCount1(gender);
            }
        }else{
            if(!genderStr.trim().equals("")){//搜索框中的歌手性别不为空
                //判断输入的是男是女
                if (genderStr.equals("男")) {
                    gender = 1;
                } else {
                    gender = 0;
                }
                //2.通过歌手名，性别来查询总数
                count=singerDao.queryCount2(singerName,gender);
            }else {
                //3.根据歌手名来查询总数
                count=singerDao.queryCount3(singerName);
            }
    }
        return count;
    }

    //查询所有歌手不分页
    public List<Singer> querySinger() {
        return singerDao.querySong();
    }
    //查询华语男歌手歌手并显示
    public List<Singer> queryManSinger() {
        return singerDao.queryManSinger();
    }
    //查询华语女歌手歌手并显示
    public List<Singer> queryWomanSinger() {
        return singerDao.queryWomanSinger();
    }
    //根据歌手首字母来查询歌手
    public List<Singer>  querySingerByCode(String firstCode) {
        return singerDao.querySingerByCode(firstCode);
    }
}
