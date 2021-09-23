package com.xyxy.pojo;

//评论实体类
public class Comment {
    private Integer comid;
    private Integer uid;
    private Integer sid;
    private String comtext;
    private String comtime;

    private Users users;
    private Song song;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    //参数构造
    public Comment() {
    }

    public Comment(Integer comid, Integer uid, Integer sid, String comtext, String comtime) {
        this.comid = comid;
        this.uid = uid;
        this.sid = sid;
        this.comtext = comtext;
        this.comtime = comtime;
    }

    //增加的实体类
    public Comment(Integer uid, Integer sid, String comtext, String comtime) {
        this.uid = uid;
        this.sid = sid;
        this.comtext = comtext;
        this.comtime = comtime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "comid=" + comid +
                ", uid=" + uid +
                ", sid=" + sid +
                ", comtext='" + comtext + '\'' +
                ", comtime='" + comtime + '\'' +
                '}';
    }

    public Integer getComid() {
        return comid;
    }

    public void setComid(Integer comid) {
        this.comid = comid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getComtext() {
        return comtext;
    }

    public void setComtext(String comtext) {
        this.comtext = comtext;
    }

    public String getComtime() {
        return comtime;
    }

    public void setComtime(String comtime) {
        this.comtime = comtime;
    }
}
