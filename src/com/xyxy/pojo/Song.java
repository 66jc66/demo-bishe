package com.xyxy.pojo;

public class Song {
    private Integer sid;
    private Integer discussNum;
    private String singerName;
    private Integer cdId;
    private Integer playCount;
    private Integer downloadCount;
    private String songName;
    private String language;
    private String faDate;
    private String songUrl;
    private String songTime;


    private Cd cd;

    public Cd getCd() {
        return cd;
    }

    public void setCd(Cd cd) {
        this.cd = cd;
    }

    //无参函数
    public Song() {
    }
    public Song(Integer sid) {
        this.sid = sid;
    }
    public Song(String songName) {
        this.songName = songName;
    }
    //当文件上传新的时候的构造函数
    public Song(Integer sid, Integer discussNum, String singerName, Integer cdId, Integer playCount, Integer downloadCount, String songName, String language, String faDate, String songUrl, String songTime) {
        this.sid = sid;
        this.discussNum = discussNum;
        this.singerName = singerName;
        this.cdId = cdId;
        this.playCount = playCount;
        this.downloadCount = downloadCount;
        this.songName = songName;
        this.language = language;
        this.faDate = faDate;
        this.songUrl = songUrl;
        this.songTime = songTime;
    }

    //增加的song构造函数
    public Song(String songName, String singerName, Integer cdId, String language, String faDate, String songUrl, String songTime) {
        this.songName = songName;
        this.singerName = singerName;
        this.cdId = cdId;
        this.language = language;
        this.faDate = faDate;
        this.songUrl = songUrl;
        this.songTime = songTime;
    }

    //当文件没有进行修改时改的构造函数
    public Song(Integer sid, Integer discussNum, String songName, String singerName, Integer cdId, Integer playCount, Integer downloadCount, String language, String faDate, String songTime) {
        this.sid = sid;
        this.discussNum = discussNum;
        this.singerName = singerName;
        this.cdId = cdId;
        this.playCount = playCount;
        this.downloadCount = downloadCount;
        this.songName = songName;
        this.language = language;
        this.faDate = faDate;
        this.songTime = songTime;
    }


    @Override
    public String toString() {
        return "Song{" +
                "sid=" + sid +
                ", discussNum=" + discussNum +
                ", singerName='" + singerName + '\'' +
                ", cdId=" + cdId +
                ", playCount=" + playCount +
                ", downloadCount=" + downloadCount +
                ", songName='" + songName + '\'' +
                ", language='" + language + '\'' +
                ", faDate='" + faDate + '\'' +
                ", songUrl='" + songUrl + '\'' +
                ", songTime='" + songTime + '\'' +
                '}';
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getDiscussNum() {
        return discussNum;
    }

    public void setDiscussNum(Integer discussNum) {
        this.discussNum = discussNum;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public Integer getCdId() {
        return cdId;
    }

    public void setCdId(Integer cdId) {
        this.cdId = cdId;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFaDate() {
        return faDate;
    }

    public void setFaDate(String faDate) {
        this.faDate = faDate;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public String getSongTime() {
        return songTime;
    }

    public void setSongTime(String songTime) {
        this.songTime = songTime;
    }
}
