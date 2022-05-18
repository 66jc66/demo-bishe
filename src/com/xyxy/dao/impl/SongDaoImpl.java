package com.xyxy.dao.impl;

import com.xyxy.dao.BaseDao;
import com.xyxy.dao.SongDao;
import com.xyxy.pojo.Singer;
import com.xyxy.pojo.Song;
import com.xyxy.pojo.Users;
import com.xyxy.util.PageUtil;

import java.util.List;

public class SongDaoImpl extends BaseDao<Song> implements SongDao {

    //增加歌曲
    @Override
    public int insertSong(Song s) {
        String sql = "INSERT INTO `music`.`song` (\n" +
                "  `songName`,\n" +
                "  `singerName`,\n" +
                "  `cdId`,\n" +
                "  `language`,\n" +
                "  `songgc`,\n" +
                "  `faDate`,\n" +
                "  `songUrl`,\n" +
                "  `songTime`\n" +
                ") \n" +
                "VALUES (?,?,?,?,?,?,?)";
        int row = update(sql, s.getSongName(), s.getSingerName(), s.getCdId(), s.getLanguage(),s.getSonggc(), s.getFaDate(), s.getSongUrl(), s.getSongTime());
        return row;
    }

    //根据歌名查询歌曲
    @Override
    public Song checkSongName(String songName) {
        String sql = "SELECT * FROM song WHERE songName=?;";
        Song song = getBean(sql, songName);
        return song;
    }

    //查询所有的歌曲
    @Override
    public Integer queryCount() {
        String sql = "SELECT COUNT(*) FROM song";
        int number = getNumber(sql);
        return number;
    }

    //1.根据专辑id来查总数
    @Override
    public Integer queryCount1(Integer cid) {
        String sql = "SELECT COUNT(*) FROM song WHERE cdId=?";
        int number = getNumber(sql,cid);
        return number;
    }

    //2.根据歌手名，专辑id来查总数
    @Override
    public Integer queryCount2(String singerName, Integer cid) {
        String sql = "SELECT COUNT(*) FROM song WHERE  singerName=? AND cdId=?";
        int number = getNumber(sql,singerName,cid);
        return number;
    }

    //3.根据歌手名来查总数
    @Override
    public Integer queryCount3(String singerName) {
        String sql = "SELECT COUNT(*) FROM song WHERE singerName=? ";
        int number = getNumber(sql,singerName);
        return number;
    }

    //4.根据歌曲名和歌手名，专辑id来查总数
    @Override
    public Integer queryCount4(String songName, String singerName, Integer cid) {
        String sql = "SELECT COUNT(*) FROM song WHERE songName=? AND singerName=? AND cdId=?";
        int number = getNumber(sql,songName,singerName,cid);
        return number;
    }

    //5.根据歌曲名和歌手名来查总数
    @Override
    public Integer queryCount5(String songName, String singerName) {
        String sql = "SELECT COUNT(*) FROM song WHERE songName=? AND singerName=? ";
        int number = getNumber(sql,songName,singerName);
        return number;
    }

    //6.根据歌曲名和专辑id来查总数
    @Override
    public Integer queryCount6(String songName, Integer cid) {
        String sql = "SELECT COUNT(*) FROM song WHERE songName=? AND cdId=?";
        int number = getNumber(sql,songName,cid);
        return number;
    }

    //7.根据歌曲名来查总数
    @Override
    public Integer queryCount7(String songName) {
        String sql = "SELECT COUNT(*) FROM song WHERE songName=? ";
        int number = getNumber(sql,songName);
        return number;
    }

    //获取歌曲热度排行榜
    //部分
    @Override
    public List<Song> queryPlayCount(int limlt) {
        String sql="SELECT * FROM song  ORDER BY playCount DESC LIMIT ?";
        List<Song> songList = getBeanList(sql, limlt);
        return songList;
    }
    //全部
    //按照播放次数查询
    @Override
    public List<Song> queryAllPlay() {
        String sql="SELECT * FROM song  ORDER BY playCount DESC";
        List<Song> songList = getBeanList(sql);
        return songList;
    }
    //按照下载次数查询
    @Override
    public List<Song> queryAllDowm() {
        String sql="SELECT * FROM song  ORDER BY downloadCount DESC";
        List<Song> songList = getBeanList(sql);
        return songList;
    }
    //按照评论次数来排列
    @Override
    public List<Song> queryAllDis() {
        String sql="SELECT * FROM song  ORDER BY discussNum DESC";
        List<Song> songList = getBeanList(sql);
        return songList;
    }

    //增加评论成功，歌曲评论数量加一
    @Override
    public int updateCom(Song song) {
        String sql="UPDATE song SET discussNum=discussNum+1 WHERE sid=?;";
        int row = update(sql, song.getSid());
        return row;
    }
    //删除评论成功，歌曲评论数量减一
    @Override
    public int updateComment(Song song) {
        String sql="UPDATE song SET discussNum=discussNum-1 WHERE sid=?;";
        int row = update(sql, song.getSid());
        return row;
    }
    //点击播放或者下载，歌曲的下载次数和播放次数都加一
    @Override//播放
    public int updateSongByPlay(Song song) {
        String sql="UPDATE song SET playCount=playCount+1 WHERE sid=?;";
        int row = update(sql, song.getSid());
        return row;
    }
    @Override//下载
    public int updateSongByDown(Song song) {
        String sql="UPDATE song SET downloadCount=downloadCount+1 WHERE songName=?;";
        int row = update(sql, song.getSongName());
        return row;
    }

    @Override
    public List<Song> queryAllDownLoad() {
        String sql="SELECT * FROM song WHERE downloadCount>0;";
        List<Song> songDown = getBeanList(sql);
        return songDown;
    }

    //根据歌手查询歌曲
    @Override
    public List<Song> checkAllBySingerName(String singerName) {
            String sql = "SELECT * FROM song WHERE singerName=? ";
        List<Song> song = getBeanList(sql,singerName);
        return song;

    }

    //根据专辑查询歌曲
    @Override
    public List<Song> checkByCid(Integer cid) {
        String sql = "SELECT * FROM song WHERE cdId=? ";
        List<Song> song = getBeanList(sql,cid);
        return song;
    }

    //获取下载排行榜
    @Override
    public List<Song> queryDownLoad(int limit) {
        String sql="SELECT * FROM song  ORDER BY downloadCount DESC LIMIT ?";
        List<Song> songList1 = getBeanList(sql,limit);
        return songList1;
    }
    //获取热议排行榜
    @Override
    public List<Song> queryDiscuss(int limit) {
        String sql="SELECT * FROM song ORDER BY discussNum DESC LIMIT ?";
        List<Song> songList = getBeanList(sql, limit);
        return songList;
    }



    //将查询出来的歌曲数据按照分页进行划分
    @Override
    public List<Song> querySongByPage(PageUtil<Song> pageUtil) {
        String sql = "SELECT * FROM song LIMIT ?,?";
        List<Song> beanList = getBeanList(sql, pageUtil.getStartIndex(), pageUtil.getPageSize());
        return beanList;
    }

    //根据id查询歌曲
    @Override
    public Song checkById(Integer sid) {
        String sql = "SELECT * FROM song WHERE sid=?;";
        Song song = getBean(sql, sid);
        return song;
    }

    //修改歌曲信息
    @Override
    public int updateSong(Song s) {
        int row = 0;
        if (s.getSongUrl() == null) {
            String sql = "UPDATE  song  SET \n" +
                    "  `songName` = ?,\n" +
                    "  `singerName` = ?,\n" +
                    "  `cdId` = ?,\n" +
                    "  `language` = ?,\n" +
                    "  `songgc` = ?,\n" +
                    "  `playCount` = ?,\n" +
                    "  `downloadCount` = ?,\n" +
                    "  `faDate` = ?,\n" +
                    "  `songTime` = ?,\n" +
                    "  `discussNum` = ?\n" +
                    "WHERE `sid` = ? ;";
            row = update(sql, s.getSongName(), s.getSingerName(), s.getCdId(), s.getLanguage(), s.getSonggc(),s.getPlayCount(), s.getDownloadCount(), s.getFaDate()
                    , s.getSongTime(), s.getDiscussNum(), s.getSid());
        } else {
            String sql = "UPDATE  song  SET \n" +
                    "  `songName` = ?,\n" +
                    "  `singerName` = ?,\n" +
                    "  `cdId` = ?,\n" +
                    "  `language` = ?,\n" +
                    "  `songgc` = ?,\n" +
                    "  `playCount` = ?,\n" +
                    "  `downloadCount` = ?,\n" +
                    "  `faDate` = ?,\n" +
                    "  `songUrl` = ?,\n" +
                    "  `songTime` = ?,\n" +
                    "  `discussNum` = ?\n" +
                    "WHERE `sid` = ? ;";
            row = update(sql, s.getSongName(), s.getSingerName(), s.getCdId(), s.getLanguage(),s.getSonggc(), s.getPlayCount(), s.getDownloadCount(), s.getFaDate()
                    , s.getSongUrl(), s.getSongTime(), s.getDiscussNum(), s.getSid());
        }

        return row;
    }

    //根据id删除单条歌曲信息
    @Override
    public int deleteById(Integer sid) {
        String sql = "DELETE  FROM song WHERE sid=?";
        int row = update(sql, sid);
        return row;

    }

    //1.歌曲名和歌手名是空，根据专辑id来查
    @Override
    public List<Song> selectBySome(Integer cid, PageUtil<Singer> pageUtil) {
        String sql = "SELECT * FROM song WHERE cdId=? LIMIT ?,?";
        List<Song> songs = getBeanList(sql, cid, pageUtil.getStartIndex(), pageUtil.getPageSize());
        return songs;
    }

    //2.歌曲名是空，根据专辑歌手名和id来查
    @Override
    public List<Song> selectBySome1(String singerName, Integer cid, PageUtil<Singer> pageUtil) {
        String sql = "SELECT * FROM song WHERE  singerName=? AND cdId=? LIMIT ?,?";
        List<Song> songs = getBeanList(sql, singerName, cid, pageUtil.getStartIndex(), pageUtil.getPageSize());
        return songs;
    }

    //3.歌曲名和专辑id都为空，根据歌手名来查
    @Override
    public List<Song> selectBySome2(String singerName, PageUtil<Singer> pageUtil) {
        String sql = "SELECT * FROM song WHERE singerName=? LIMIT ?,?";
        List<Song> songs = getBeanList(sql, singerName, pageUtil.getStartIndex(), pageUtil.getPageSize());
        return songs;
    }

    //4.根据歌曲名和歌手名，专辑id来查
    @Override
    public List<Song> selectBySome3(String songName, String singerName, Integer cid, PageUtil<Singer> pageUtil) {
        String sql = "SELECT * FROM song WHERE songName=? AND singerName=? AND cdId=? LIMIT ?,?";
        List<Song> songs = getBeanList(sql, songName, singerName, cid, pageUtil.getStartIndex(), pageUtil.getPageSize());
        return songs;
    }

    //5.根据歌手名和歌曲名来查询
    @Override
    public List<Song> selectBySome4(String songName, String singerName, PageUtil<Singer> pageUtil) {
        String sql = "SELECT * FROM song WHERE songName=? AND singerName=? LIMIT ?,?";
        List<Song> songs = getBeanList(sql, songName, singerName, pageUtil.getStartIndex(), pageUtil.getPageSize());
        return songs;
    }

    //6.歌手名是空，根据歌曲名和专辑id来查
    @Override
    public List<Song> selectBySome5(String songName, Integer cid, PageUtil<Singer> pageUtil) {
        String sql = "SELECT * FROM song WHERE songName=?  AND cdId=? LIMIT ?,?";
        List<Song> songs = getBeanList(sql, songName, cid, pageUtil.getStartIndex(), pageUtil.getPageSize());
        return songs;
    }

    //7.歌手名和专辑id都为空，根据歌曲名来查
    @Override
    public List<Song> selectBySome6(String songName, PageUtil<Singer> pageUtil) {
        String sql = "SELECT * FROM song WHERE songName=? LIMIT ?,?";
        List<Song> songs = getBeanList(sql, songName, pageUtil.getStartIndex(), pageUtil.getPageSize());
        return songs;
    }
}
