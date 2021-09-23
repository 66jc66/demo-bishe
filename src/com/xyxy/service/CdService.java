package com.xyxy.service;

import com.xyxy.dao.CdDao;
import com.xyxy.dao.impl.CdDaoImpl;
import com.xyxy.pojo.Cd;
import com.xyxy.util.PageUtil;

import java.util.List;

public class CdService {
    private CdDao cdDao = new CdDaoImpl();

    //查询所有的专辑数量
    public Integer queryCount() {
        return cdDao.queryCount();
    }

    //将查询到的数据进行分页显示
    public List<Cd> queryCdByPage(PageUtil<Cd> pageUtil) {
        return cdDao.queryCdByPage(pageUtil);
    }

    //增加专辑数据
    public int insertCd(Cd cd) {
        return cdDao.insertCd(cd);
    }

    //通过专辑名称查询是否有重复的
    public Cd checkByCdName(String cdName) {
        return cdDao.checkByCdName(cdName);
    }
    //根据id删除单个数据
    public int deleteById(Integer cid) {
        return cdDao.deleteById(cid);
    }
    //根据ids集合批量删除数据
    public int batchDelete(String ids) {
        int num=0;
        String[] sidStr = ids.split(",");
        for(String sids:sidStr){
            Integer cid = Integer.valueOf(sids);
            //调用删除方法
            int row = cdDao.deleteById(cid);
            num+=row;
        }
        return num;
    }
//根据专辑名，歌手来查询数据后分页显示
    public List<Cd> selectBySome(String cdName, String singer, PageUtil<Cd> pageUtil) {
        List<Cd> cdList = null;
        if(cdName.trim().equals("")){//搜索框中专辑名为空
            if(!singer.trim().equals("")){//搜索框中的歌手不为空
                //1.通过歌手来查询
                cdList=cdDao.selectBySome1(singer,pageUtil);
            }
        }else{
            if(!singer.trim().equals("")){//搜索框中的歌手不为空
                //2.通过歌手和专辑名来查询
                cdList=cdDao.selectBySome2(cdName,singer,pageUtil);
            }else {
                //3.根据歌手名来查询
                cdList=cdDao.selectBySome3(cdName,pageUtil);
            }
        }
        return cdList;
    }

    //根据专辑名或者歌手来查询总数
    //根据歌手名或者是性别来查总数
    public Integer queryCountNum(String cdName, String singer) {
        Integer count=0;
        if(cdName.trim().equals("")){//搜索框中专辑名为空
            if(!singer.trim().equals("")){//搜索框中的歌手名不为空
                //1.通过歌手名来查询总数
                count=cdDao.queryCount1(singer);
            }
        }else{
            if(!singer.trim().equals("")){//搜索框中的歌手名不为空

                //2.通过歌手名，专辑名来查询总数
                count=cdDao.queryCount2(cdName,singer);
            }else {
                //3.根据歌手名来查询总数
                count=cdDao.queryCount3(cdName);
            }
        }
        return count;
    }

    //根据专辑号来查询要修改的专辑
    public Cd findCdById(Integer cid) {
        return cdDao.fingCdById(cid);
    }

    //修改专辑信息
    public int updateCd(Cd cd) {
        return cdDao.updateCd(cd);
    }

    //查询所有的专辑并显示
    public List<Cd> queryCd() { return cdDao.queryCd();    }
    //根据地区查询所有歌专辑并显示
    public List<Cd> queryByCity(String city) { return cdDao.queryCdByCity(city);    }
    //根据是否热门查询所有专辑并显示
    public List<Cd> queryPopular(Integer popular) {  return cdDao.queryCdByPopular(popular);  }
    //根据年代查询所有歌MV并显示
    public List<Cd> queryCyear(Integer cyear) { return cdDao.queryCdByCyear(cyear);    }
}