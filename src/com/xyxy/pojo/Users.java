package com.xyxy.pojo;

public class Users {
    private Integer uid;
    private Integer usersex;
    private Integer role;
    private String loginid;
    private String password;
    private String phoneno;
    private String username;
    private String email;
    private String signs;
//增加管理员信息
    public Users(Integer usersex, String loginid, String password, String phoneno, String username, String email, String signs, Integer role) {
        this.usersex = usersex;
        this.loginid = loginid;
        this.password=password;
        this.phoneno = phoneno;
        this.username = username;
        this.email = email;
        this.signs = signs;
        this.role=role;
    }
//修改后台管理员数据
    public Users(Integer uid, Integer usersex, Integer role, String loginid, String phoneno, String username, String email, String signs) {
        this.uid = uid;
        this.usersex = usersex;
        this.role = role;
        this.loginid = loginid;
        this.phoneno = phoneno;
        this.username = username;
        this.email = email;
        this.signs = signs;
    }
//增加用户信息
    public Users(Integer usersex, String loginid, String password, String phoneno, String username, String email, String signs) {
        this.usersex = usersex;
        this.loginid = loginid;
        this.phoneno = phoneno;
        this.username = username;
        this.email = email;
        this.signs = signs;
        this.password=password;

    }

    @Override
    public String toString() {
        return "Users{" +
                "uid=" + uid +
                ", usersex=" + usersex +
                ", role=" + role +
                ", loginid='" + loginid + '\'' +
                ", password='" + password + '\'' +
                ", phoneno='" + phoneno + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", signs='" + signs + '\'' +
                '}';
    }

    public Users() {
    }

    public Users(Integer uid, Integer usersex, Integer role, String loginid, String password, String phoneno, String username, String email, String signs) {
        this.uid = uid;
        this.usersex = usersex;
        this.role = role;
        this.loginid = loginid;
        this.password = password;
        this.phoneno = phoneno;
        this.username = username;
        this.email = email;
        this.signs = signs;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getUsersex() {
        return usersex;
    }

    public void setUsersex(Integer usersex) {
        this.usersex = usersex;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSigns() {
        return signs;
    }

    public void setSigns(String signs) {
        this.signs = signs;
    }
}
