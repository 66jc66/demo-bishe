package com.xyxy.service;

import com.xyxy.dao.SongDao;
import com.xyxy.dao.impl.SongDaoImpl;
import com.xyxy.pojo.Singer;
import com.xyxy.pojo.Song;
import com.xyxy.util.PageUtil;

import java.util.List;

public class SongService {
    private SongDao songDao = new SongDaoImpl();

    //增加歌曲
    public int insertSong(Song song) {
        return songDao.insertSong(song);
    }

    //根据歌名来查询是否有重复的
    public Song checkBySongName(String songName) {
        return songDao.checkSongName(songName);
    }

    //查询所有的歌曲数量
    public Integer queryCount() {
        return songDao.queryCount();
    }

    //将查询出来的歌曲数据按照分页进行划分
    public List<Song> queryUserByPage(PageUtil<Song> pageUtil) {
        return songDao.querySongByPage(pageUtil);
    }

    //根据id查询歌曲
    public Song checkById(Integer sid) {
        return songDao.checkById(sid);
    }

    //修改歌曲信息
    public int updateSong(Song song) {
        return songDao.updateSong(song);
    }

    //根据id删除单条歌曲信息
    public int deleteById(Integer sid) {
        return songDao.deleteById(sid);
    }

    //批量删除歌曲信息
    public int deleteByIds(String ids) {
        int num = 0;
        String[] idStr = ids.split(",");
        //遍历数组
        for (String id : idStr) {
            Integer uid = Integer.valueOf(id);
            //调用删除方法
            int row = songDao.deleteById(uid);
            num += row;
        }
        return num;
    }

    //根据歌曲名，歌手名，专辑id查询相关用户
    public List<Song> selectBySome(String songName, String singerName, String cidStr, PageUtil<Singer> pageUtil) {
        List<Song> songs = null;
        if (songName.trim().equals("")) {//歌曲名为空
            if (singerName.trim().equals("")) {//歌手名为空
                if (!cidStr.trim().equals("")) {//专辑id不为空
                    Integer cid = Integer.valueOf(cidStr);
                    //1.歌曲名和歌手名是空，根据专辑id来查
                    songs = songDao.selectBySome(cid, pageUtil);
                }
            } else {
                if (!cidStr.trim().equals("")) {//专辑id不为空
                    Integer cid = Integer.valueOf(cidStr);
                    //2.歌曲名是空，根据歌手名和专辑id来查
                    songs = songDao.selectBySome1(singerName, cid, pageUtil);
                } else {//专辑id为空
                    //3.歌曲名和专辑id都为空，根据歌手名来查
                    songs = songDao.selectBySome2(singerName, pageUtil);
                }
            }
        } else {
            if (!singerName.trim().equals("")) {//歌手名不为空
                if (!cidStr.trim().equals("")) {//专辑id不为空
                    Integer cid = Integer.valueOf(cidStr);
                    //4.根据歌曲名和歌手名，专辑id来查
                    songs = songDao.selectBySome3(songName, singerName, cid, pageUtil);
                } else {//专辑id为空
                    //5.根据歌手名和歌曲名来查询
                    songs = songDao.selectBySome4(songName, singerName, pageUtil);
                }
            } else {//歌手名为空
                if (!cidStr.trim().equals("")) {//专辑id不为空
                    Integer cid = Integer.valueOf(cidStr);
                    //6.歌手名是空，根据歌曲名和专辑id来查
                    songs = songDao.selectBySome5(songName, cid, pageUtil);
                } else {//专辑id为空
                    //7.歌手名和专辑id都为空，根据歌曲名来查
                    songs = songDao.selectBySome6(songName, pageUtil);
                }
            }
        }
        return songs;
    }

    //根据歌曲名，歌手名，专辑id查询总数
    public Integer queryCount1(String songName, String singerName, String cidStr) {
        Integer count = 0;
        if (songName.trim().equals("")) {//歌曲名为空
            if (singerName.trim().equals("")) {//歌手名为空
                if (!cidStr.trim().equals("")) {//专辑id不为空
                    Integer cid = Integer.valueOf(cidStr);
                    //1.歌曲名和歌手名是空，根据专辑id来查总数
                    count = songDao.queryCount1(cid);
                }
            } else {
                if (!cidStr.trim().equals("")) {//专辑id不为空
                    Integer cid = Integer.valueOf(cidStr);
                    //2.歌曲名是空，根据歌手名和专辑id来查总数
                    count = songDao.queryCount2(singerName, cid);
                } else {//专辑id为空
                    //3.歌曲名和专辑id都为空，根据歌手名来查总数
                    count = songDao.queryCount3(singerName);
                }
            }
        } else {
            if (!singerName.trim().equals("")) {//歌手名不为空
                if (!cidStr.trim().equals("")) {//专辑id不为空
                    Integer cid = Integer.valueOf(cidStr);
                    //4.根据歌曲名和歌手名，专辑id来查总数
                    count = songDao.queryCount4(songName, singerName, cid);
                } else {//专辑id为空
                    //5.根据歌手名和歌曲名来查询总数
                    count = songDao.queryCount5(songName, singerName);
                }
            } else {//歌手名为空
                if (!cidStr.trim().equals("")) {//专辑id不为空
                    Integer cid = Integer.valueOf(cidStr);
                    //6.歌手名是空，根据歌曲名和专辑id来查总数
                    count = songDao.queryCount6(songName, cid);
                } else {//专辑id为空
                    //7.歌手名和专辑id都为空，根据歌曲名来查总数
                    count = songDao.queryCount7(songName);
                }
            }
        }
        return count;
    }

    //获取歌曲热度排行榜
    public List<Song> queryPlayCount(int limlt) {
        return songDao.queryPlayCount(limlt);
    }

    //获取下载排行榜
    public List<Song> queryDownLoad(int limit) {
        return songDao.queryDownLoad(limit);
    }

    //获取热议排行榜
    public List<Song> queryDiscuss(int limit) {
        return songDao.queryDiscuss(limit);
    }

    //查询所有的歌曲按照播放次数来排列
    public List<Song> queryAllPlay() {
        return songDao.queryAllPlay();
    }

    //查询所有的歌曲按照下载次数来排列
    public List<Song> queryAllDowm() {
        return songDao.queryAllDowm();
    }

    //查询所有的歌曲按照评论次数来排列
    public List<Song> queryAllDis() {
        return songDao.queryAllDis();
    }

    //增加评论成功，歌曲评论数量加一
    public int updateCom(Song song) {
        return songDao.updateCom(song);
    }

    //删除评论成功，歌曲评论数量减一
    public int updateComment(Song song) {
        return songDao.updateComment(song);
    }

    //点击播放或者下载，歌曲的下载次数和播放次数都加一
    //1.播放
    public int updateSongByPlay(Song song) {
        return songDao.updateSongByPlay(song);
    }

    //2.下载
    public int updateSongByDown(Song song) {
        return songDao.updateSongByDown(song);
    }

    //获取下载好的数据
    public List<Song> queryAllDownLoad() { return songDao.queryAllDownLoad();   }
}
