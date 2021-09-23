package com.xyxy.pojo;

public class Singer {
    private Integer srid;
    private String singerName;
    private Integer gender;
    private String photo;
    private String descp;
    private String typeName;
    private String firstCode;
    private Integer popular;


    public Singer() {
    }

    //原始的构造函数
    public Singer(Integer srid, String singerName, Integer gender, String photo, String descp, String typeName, String firstCode, Integer popular) {
        this.srid = srid;
        this.singerName = singerName;
        this.gender = gender;
        this.photo = photo;
        this.typeName = typeName;
        this.firstCode = firstCode;
        this.popular = popular;
        this.descp = descp;
    }
    //修改歌手信息的构造函数
    public Singer(Integer srid, String singerName, Integer gender,  String descp, String typeName, String firstCode, Integer popular) {
        this.srid = srid;
        this.singerName = singerName;
        this.gender = gender;
        this.typeName = typeName;
        this.firstCode = firstCode;
        this.popular = popular;
        this.descp = descp;
    }
    //增加歌手的构造函数
    public Singer(String singerName, Integer gender, String photo, String descp, String typeName, String firstCode, Integer popular) {
        this.singerName = singerName;
        this.gender = gender;
        this.photo = photo;
        this.typeName = typeName;
        this.firstCode = firstCode;
        this.popular = popular;
        this.descp = descp;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "srid=" + srid +
                ", singerName='" + singerName + '\'' +
                ", gender=" + gender +
                ", photo='" + photo + '\'' +
                ", typeName='" + typeName + '\'' +
                ", firstCode='" + firstCode + '\'' +
                ", popular='" + popular + '\'' +
                ", descp='" + descp + '\'' +
                '}';
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public Integer getSrid() {
        return srid;
    }

    public void setSrid(Integer srid) {
        this.srid = srid;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getFirstCode() {
        return firstCode;
    }

    public void setFirstCode(String firstCode) {
        this.firstCode = firstCode;
    }

    public Integer getPopular() {
        return popular;
    }

    public void setPopular(Integer popular) {
        this.popular = popular;
    }
}
