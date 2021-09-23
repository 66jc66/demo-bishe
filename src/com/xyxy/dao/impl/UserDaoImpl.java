package com.xyxy.dao.impl;

import com.xyxy.dao.BaseDao;
import com.xyxy.dao.UserDao;
import com.xyxy.pojo.Users;
import com.xyxy.util.PageUtil;

import java.util.List;

public class UserDaoImpl extends BaseDao<Users> implements UserDao {
    //增加用户
    @Override
    public int insertUser(Users users) {
        String sql = "INSERT INTO `music`.`users` (\n" +
                "  `loginid`,\n" +
                "  `password`,\n" +
                "  `phoneno`,\n" +
                "  `username`,\n" +
                "  `usersex`,\n" +
                "  `email`,\n" +
                "  `signs`,\n" +
                "  `role`\n" +
                ") \n" +
                "VALUES (?,?,?,?,?,?,?,?)";
        int row = update(sql, users.getLoginid(), users.getPassword(), users.getPhoneno(), users.getUsername(),
                users.getUsersex(), users.getEmail(), users.getSigns(), users.getRole());
        return row;
    }

    //查询用户通过手机号
    @Override
    public Users checkPhone(String phone) {
        String sql = "SELECT * FROM users WHERE phoneno=?";
        Users users = getBean(sql, phone);
        return users;
    }

    //查询用户账号
    @Override
    public Users checkUser(String loginid) {
        String sql = "SELECT * FROM users WHERE loginid=?";
        Users users = getBean(sql, loginid);
        return users;
    }

    //查询管理员
    @Override
    public Users checkByAdmin(String username, String psd) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ? AND role=1;";
        Users users = getBean(sql, username, psd);
        return users;
    }

    //查询用户总数
    @Override
    public Integer queryCount() {
        String sql = "SELECT COUNT(*) FROM users";
        int number = getNumber(sql);
        return number;
    }
    //1.性别，uid搜索框不为空，昵称搜索框为空,查询总数
    @Override
    public Integer queryCount1(Integer usersex, Integer uid) {
        String sql = "SELECT COUNT(*) FROM users WHERE usersex=? AND uid=?";
        int number = getNumber(sql,usersex,uid);
        return number;
    }
    //2.昵称，uid搜索框为空，性别搜索框不为空,查询总数
    @Override
    public Integer queryCount2(Integer usersex) {
        String sql = "SELECT COUNT(*) FROM users WHERE  usersex=? ";
        int number = getNumber(sql,usersex);
        return number;
    }
    //3.uid搜索框不为空，昵称和性别搜索框为空,查询总数
    @Override
    public Integer queryCount3(Integer uid) {
        String sql = "SELECT COUNT(*) FROM users WHERE  uid=?";
        int number = getNumber(sql,uid);
        return number;
    }
    //4.三个搜索框都不为空,性别搜索框为空,查询总数
    @Override
    public Integer queryCount4(String username, Integer usersex, Integer uid) {
        String sql = "SELECT COUNT(*) FROM users WHERE username=? AND usersex=? AND uid=?";
        int number = getNumber(sql,username,usersex,uid);
        return number;
    }
    //5.昵称，性别搜索框不为空，uid搜索框为空,查询总数
    @Override
    public Integer queryCount5(String username, Integer usersex) {
        String sql = "SELECT COUNT(*) FROM users WHERE username=? AND usersex=? ";
        int number = getNumber(sql,username,usersex);
        return number;
    }
    //6.昵称搜索框不为空，性别，uid搜索框为空,查询总数
    @Override
    public Integer queryCount6(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username=?";
        int number = getNumber(sql,username);
        return number;
    }
    //7.昵称，uid搜索框不为空，性别搜索框为空,查询总数
    @Override
    public Integer queryCount7(String username, Integer uid) {
        String sql = "SELECT COUNT(*) FROM users WHERE username=?  AND uid=?";
        int number = getNumber(sql,username,uid);
        return number;
    }

    //分页查询显示
    @Override
    public List<Users> queryUserByPage(PageUtil<Users> pageUtil) {
        String sql = "SELECT * FROM users LIMIT ?,?";
        List<Users> beanList = getBeanList(sql, pageUtil.getStartIndex(), pageUtil.getPageSize());
        return beanList;
    }

    //删除用户
    @Override
    public int deleteById(Integer uid) {
        String sql = "DELETE  FROM users WHERE uid=?";
        int row = update(sql, uid);
        return row;
    }

    //根据id查询
    @Override
    public Users checkById(Integer uid) {
        String sql = "SELECT * FROM users WHERE uid=?";
        Users users = getBean(sql, uid);
        return users;
    }

    //更改管理员信息
    @Override
    public int updateUser(Users u) {
        String sql = "UPDATE users SET loginid=?,phoneno =?,username =?,usersex= ?,email= ?,signs= ?, role=? WHERE uid = ?";
        int row = update(sql, u.getLoginid(), u.getPhoneno(), u.getUsername(), u.getUsersex(), u.getEmail(), u.getSigns(), u.getRole(), u.getUid());
        return row;
    }

    //查询所有用户
    @Override
    public List<Users> queryListAll() {
        String sql = "SELECT * FROM users";
        List<Users> usersList = getBeanList(sql);
        return usersList;
    }

    //查询账号
    @Override
    public Users checkLoginid(String loginid) {
        String sql = "SELECT * FROM users WHERE loginid=?";
        Users users = getBean(sql, loginid);
        return users;
    }

    //1.根据用户性别，uid查询相关用户
    @Override
    public List<Users> queryBySome1(Integer usersex, Integer uid,PageUtil<Users> pageUtil) {
        String sql = "SELECT * FROM users WHERE usersex=? AND uid=? LIMIT ?,?";
        List<Users> users = getBeanList(sql, usersex,uid, pageUtil.getStartIndex(), pageUtil.getPageSize());
        return users;
    }

    //2.根据性别查询相关用户
    @Override
    public List<Users> queryBySome2(Integer usersex,PageUtil<Users> pageUtil) {
        String sql = "SELECT * FROM users WHERE usersex=? LIMIT ?,?";
        List<Users> users = getBeanList(sql, usersex,pageUtil.getStartIndex(), pageUtil.getPageSize());
        return users;
    }

    //3.根据用户uid查询相关用户
    @Override
    public List<Users> queryBySome3(Integer uid,PageUtil<Users> pageUtil) {
        String sql = "SELECT * FROM users WHERE uid=? LIMIT ?,?";
        List<Users> users = getBeanList(sql, uid, pageUtil.getStartIndex(), pageUtil.getPageSize());
        System.out.println(users);
        return users;
    }
    //4.根据用户昵称，id，性别查询相关用户
    @Override
    public List<Users> queryBySome4(String username, Integer usersex, Integer uid,PageUtil<Users> pageUtil) {
        String sql = "SELECT * FROM users WHERE username=? AND usersex=? AND uid=? LIMIT ?,?";
        List<Users> users = getBeanList(sql, username, usersex, uid, pageUtil.getStartIndex(), pageUtil.getPageSize());
        return users;
    }
    //5.根据用户昵称，性别查询相关用户
    @Override
    public List<Users> queryBySome5(String username, Integer usersex,PageUtil<Users> pageUtil) {
        String sql = "SELECT * FROM users WHERE username=? AND usersex=? LIMIT ?,?";
        List<Users> users = getBeanList(sql, username, usersex, pageUtil.getStartIndex(), pageUtil.getPageSize());
        return users;
    }

    //6.根据用户昵称查询相关用户
    @Override
    public List<Users> queryBySome6(String username,PageUtil<Users> pageUtil) {
        String sql = "SELECT * FROM users WHERE username=? LIMIT ?,?";
        List<Users> users = getBeanList(sql, username, pageUtil.getStartIndex(), pageUtil.getPageSize());
        return users;
    }

    //7.根据用户昵称，uid查询相关用户
    @Override
    public List<Users> queryBySome7(String username, Integer uid,PageUtil<Users> pageUtil) {
        String sql = "SELECT * FROM users WHERE username=? AND uid=? LIMIT ?,?";
        List<Users> users = getBeanList(sql, username, uid, pageUtil.getStartIndex(), pageUtil.getPageSize());
        return users;
    }

    //修改用户密码
    @Override
    public int updatePsd(String password,Integer uid) {
        String sql="UPDATE users SET password=? WHERE uid=?";
        int row = update(sql, password, uid);
        return row;
    }

}
