package com.xyxy.dao;

import com.xyxy.pojo.Singer;
import com.xyxy.pojo.Song;
import com.xyxy.util.PageUtil;
import java.util.List;

public interface SongDao {
    //增加歌曲信息
    int insertSong(Song song);

    //根据歌名查询歌曲
    Song checkSongName(String songName);

    //将查询出来的歌曲数据按照分页进行划分
    List<Song> querySongByPage(PageUtil<Song> pageUtil);

    //根据id查询歌曲
    Song checkById(Integer sid);

    //修改歌曲信息
    int updateSong(Song song);

    //根据id删除单条歌曲信息
    int deleteById(Integer sid);

    //1.歌曲名和歌手名是空，根据专辑id来查
    List<Song> selectBySome(Integer cid, PageUtil<Singer> pageUtil);

    //2.歌曲名是空，根据歌手名和专辑id来查
    List<Song> selectBySome1(String singerName, Integer cid, PageUtil<Singer> pageUtil);

    //3.歌曲名和专辑id都为空，根据歌手名来查
    List<Song> selectBySome2(String singerName, PageUtil<Singer> pageUtil);

    //4.根据歌曲名和歌手名，专辑id来查
    List<Song> selectBySome3(String songName, String singerName, Integer cid, PageUtil<Singer> pageUtil);

    //5.根据歌手名和歌曲名来查询
    List<Song> selectBySome4(String songName, String singerName, PageUtil<Singer> pageUtil);

    //6.歌手名是空，根据歌曲名和专辑id来查
    List<Song> selectBySome5(String songName, Integer cid, PageUtil<Singer> pageUtil);

    //7.歌手名和专辑id都为空，根据歌曲名来查
    List<Song> selectBySome6(String songName, PageUtil<Singer> pageUtil);


    //查询所有的歌曲数量
    Integer queryCount();
    //1.根据专辑id来查总数
    Integer queryCount1(Integer cid);
    //2.根据歌手名，专辑id来查总数
    Integer queryCount2(String singerName, Integer cid);
    //3.根据歌手名来查总数
    Integer queryCount3(String singerName);
    //4.根据歌曲名和歌手名，专辑id来查总数
    Integer queryCount4(String songName, String singerName, Integer cid);
    //5.根据歌曲名和歌手名来查总数
    Integer queryCount5(String songName, String singerName);
    //6.根据歌曲名和专辑id来查总数
    Integer queryCount6(String songName, Integer cid);
    //7.根据歌曲名来查总数
    Integer queryCount7(String songName);


    //获取歌曲热度排行榜十条数据
    List<Song> queryPlayCount(int limlt);
    //获取下载排行榜十条数据
    List<Song> queryDownLoad(int limit);
    //获取热议排行榜十条数据
    List<Song> queryDiscuss(int limit);

    //查询所有的歌曲按照播放次数来排列
    List<Song> queryAllPlay();
    //查询所有的歌曲按照下载次数来排列
    List<Song> queryAllDowm();
    //查询所有的歌曲按照评论次数来排列
    List<Song> queryAllDis();

    //增加评论成功，歌曲评论数量加一
    int updateCom(Song song);
    //删除评论成功，歌曲评论数量减一
    int updateComment(Song song);
    //点击播放或者下载，歌曲的下载次数和播放次数都加一
    int updateSongByPlay(Song song);
    int updateSongByDown(Song song);

    //获取下载好的数据
    List<Song> queryAllDownLoad();

    List<Song> checkAllBySingerName(String singerName);

    List<Song> checkByCid(Integer cid);
}
