package com.xyxy.pojo;

/*
CREATE TABLE cd(
cid INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
cdName VARCHAR(225),#专辑名
cphoto VARCHAR(225),#专辑图片
singer VARCHAR(225),#歌手
city VARCHAR(225),#专辑所属
cyear INT(225),#专辑年代
popular INT(225)
);
*/
public class Cd {
    private Integer cid;
    private Integer cyear;
    private Integer popular;
    private String cdName;
    private String cphoto;
    private String city;
    private String singer;

    //构造函数
    //1.有参构造,同时是修改的构造函数
    public Cd(Integer cid, Integer cyear, Integer popular, String cdName, String cphoto, String city, String singer) {
        this.cid = cid;
        this.cyear = cyear;
        this.popular = popular;
        this.cdName = cdName;
        this.cphoto = cphoto;
        this.city = city;
        this.singer = singer;
    }

    //2.无参构造
    public Cd() {
    }

    //3.增加的构造函数
    public Cd(Integer cyear, Integer popular, String cdName, String cphoto, String city, String singer) {
        this.cyear = cyear;
        this.popular = popular;
        this.cdName = cdName;
        this.cphoto = cphoto;
        this.city = city;
        this.singer = singer;
    }

    //4.修改的构造函数
    public Cd(Integer cid, Integer cyear, Integer popular, String cdName, String city, String singer) {
        this.cid = cid;
        this.cyear = cyear;
        this.popular = popular;
        this.cdName = cdName;
        this.city = city;
        this.singer = singer;
    }

    @Override
    public String toString() {
        return "Cd{" +
                "cid=" + cid +
                ", cyear=" + cyear +
                ", popular=" + popular +
                ", cdName='" + cdName + '\'' +
                ", cphoto='" + cphoto + '\'' +
                ", city='" + city + '\'' +
                ", singer='" + singer + '\'' +
                '}';
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getCyear() {
        return cyear;
    }

    public void setCyear(Integer cyear) {
        this.cyear = cyear;
    }

    public Integer getPopular() {
        return popular;
    }

    public void setPopular(Integer popular) {
        this.popular = popular;
    }

    public String getCdName() {
        return cdName;
    }

    public void setCdName(String cdName) {
        this.cdName = cdName;
    }

    public String getCphoto() {
        return cphoto;
    }

    public void setCphoto(String cphoto) {
        this.cphoto = cphoto;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
