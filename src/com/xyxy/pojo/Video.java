package com.xyxy.pojo;

public class Video {
    private Integer vid;
    private Integer vyear;
    private Integer popular;
    private String viName;
    private String vphoto;
    private String singer;
    private String vcity;
    private String viurl;

    //构造函数
    //1.有参构造
    public Video(Integer vid, Integer vyear, Integer popular, String viName, String vphoto, String singer, String vcity, String viurl) {
        this.vid = vid;
        this.vyear = vyear;
        this.popular = popular;
        this.viName = viName;
        this.vphoto = vphoto;
        this.singer = singer;
        this.vcity = vcity;
        this.viurl = viurl;
    }
    //2.无参构造函数
    public Video() {
    }
    //3.增加的有参构造
    public Video( Integer vyear, Integer popular, String viName, String vphoto, String singer, String vcity, String viurl) {
        this.vyear = vyear;
        this.popular = popular;
        this.viName = viName;
        this.vphoto = vphoto;
        this.singer = singer;
        this.vcity = vcity;
        this.viurl = viurl;
    }
    //4.不修改图片的构造函数
    public Video(Integer vid, Integer vyear,Integer popular, String viName, String singer, String vcity) {
        this.vid = vid;
        this.vyear = vyear;
        this.popular = popular;
        this.viName = viName;
        this.singer = singer;
        this.vcity = vcity;
    }
    //4.修改图片的构造函数
    public Video(Integer vid, Integer vyear, Integer popular, String viName, String vphoto, String singer, String vcity) {
        this.vid = vid;
        this.vyear = vyear;
        this.popular = popular;
        this.viName = viName;
        this.vphoto = vphoto;
        this.singer = singer;
        this.vcity = vcity;
    }

    @Override
    public String toString() {
        return "Video{" +
                "vid=" + vid +
                ", vyear=" + vyear +
                ", popular=" + popular +
                ", viName='" + viName + '\'' +
                ", vphoto='" + vphoto + '\'' +
                ", singer='" + singer + '\'' +
                ", vcity='" + vcity + '\'' +
                ", viurl='" + viurl + '\'' +
                '}';
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Integer getVyear() {
        return vyear;
    }

    public void setVyear(Integer vyear) {
        this.vyear = vyear;
    }

    public Integer getPopular() {
        return popular;
    }

    public void setPopular(Integer popular) {
        this.popular = popular;
    }

    public String getViName() {
        return viName;
    }

    public void setViName(String viName) {
        this.viName = viName;
    }

    public String getVphoto() {
        return vphoto;
    }

    public void setVphoto(String vphoto) {
        this.vphoto = vphoto;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getVcity() {
        return vcity;
    }

    public void setVcity(String vcity) {
        this.vcity = vcity;
    }

    public String getViurl() {
        return viurl;
    }

    public void setViurl(String viurl) {
        this.viurl = viurl;
    }
}
