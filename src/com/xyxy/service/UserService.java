package com.xyxy.service;

import com.alibaba.fastjson.JSON;
import com.xyxy.dao.UserDao;
import com.xyxy.dao.impl.UserDaoImpl;
import com.xyxy.pojo.ResultInfo;
import com.xyxy.pojo.Users;
import com.xyxy.util.PageUtil;

import java.util.List;

public class UserService {
    private ResultInfo resultInfo = new ResultInfo();
    private UserDao userDao = new UserDaoImpl();

    //增加用户
    public int insertUser(Users users) {
        return userDao.insertUser(users);
    }

    //根据手机号查询
    public Users checkPhone(String phone) {
        return userDao.checkPhone(phone);
    }

    //根据用户账号查询
    public Users checkUser(String loginid) {
        return userDao.checkUser(loginid);
    }

    //查询管理员
    public Users checkAdmin(String username, String psd) {
        return userDao.checkByAdmin(username, psd);
    }

    //查询数据条数
    public Integer queryCount() {
        return userDao.queryCount();
    }

    //分页查询
    public List<Users> queryUserByPage(PageUtil<Users> pageUtil) {
        return userDao.queryUserByPage(pageUtil);
    }

    //批量删除用户
    public int deleteByIds(String ids) {
        int num = 0;
        String[] idStr = ids.split(",");
        //遍历数组
        for (String id : idStr) {
            Integer uid = Integer.valueOf(id);
            //调用删除方法
            int row = userDao.deleteById(uid);
            num += row;
        }
        return num;
    }

    //根据用户id来查询
    public Users checkById(Integer uid) {
        return userDao.checkById(uid);
    }

    //修改用户信息
    public int updateUser(Users users) {
        return userDao.updateUser(users);
    }

    //查询所有用户
    public ResultInfo queryUserAll() {
        //调用查询所有方法
        List<Users> usersList = userDao.queryListAll();
        //查询记录总条数
        Integer count = queryCount();
        //设置返回结果信息
        resultInfo.setCode(0);
        resultInfo.setCount(count);
        resultInfo.setResult(usersList);
        return resultInfo;
    }

    //根据id来删除单条数据
    public int deleteById(Integer uid) {
        return userDao.deleteById(uid);
    }

    //根据账号来查询
    public Users checkLoginid(String loginid) {
        return userDao.checkLoginid(loginid);
    }

    //根据用户昵称，id，性别查询相关用户
    public List<Users> queryBySome(String username, String usersexStr, String uidStr, PageUtil<Users> pageUtil) {
        Integer usersex;
        List<Users> usersList = null;
        //判断输入框的值不能为空
    /*    boolean flag=!username.trim().equals("");//false
        boolean flag1=username.equals( null);//false
        boolean flag2=usersexStr.trim().equals("");//true
        boolean flag3=usersexStr .equals( null);//false*/
        if (username.trim().equals("")) {//username.trim().equals("")代表的含义是昵称为空
            if (!usersexStr.trim().equals("")) {//性别非空
                if (usersexStr.equals("男")) {
                    usersex = 1;
                } else {
                    usersex = 0;
                }
                if (!uidStr.trim().equals("")) {//uid不空
                    Integer uid=Integer.valueOf(uidStr);
                    //1.昵称搜索框为空，性别，uid搜索框不为空
                    usersList = userDao.queryBySome1(usersex, uid,pageUtil);
                }
                if(uidStr.trim().equals("")){//uid空
                    //2.性别搜索框不为空，昵称，uid搜索框为空
                    usersList = userDao.queryBySome2(usersex,pageUtil);
                }
            } else {//性别为空
                if(!uidStr.trim().equals("")){//uid非空
                    Integer uid=Integer.valueOf(uidStr);
                    //3.性别，昵称搜索框为空，uid搜索框不为空
                    usersList = userDao.queryBySome3(uid,pageUtil);
                    // isUserNull(usersList);
                }
            }
        } else {//昵称非空
            if (!usersexStr.trim().equals("")) {//性别非空
                if (usersexStr.equals("男")) {
                    usersex = 1;
                } else {
                    usersex = 0;
                }
                if (!uidStr.trim().equals("")) {//uid非空
                    Integer uid=Integer.valueOf(uidStr);
                    //4.三个搜索框都不为空
                    usersList = userDao.queryBySome4(username, usersex, uid,pageUtil);
                } else {//uid为空
                    // 5.昵称，性别搜索框不为空，uid搜索框为空
                    usersList = userDao.queryBySome5(username, usersex,pageUtil);
                }
            } else {
                if (uidStr.trim().equals("")) {
                    //6.昵称搜索框不为空，性别，uid搜索框为空
                    usersList = userDao.queryBySome6(username,pageUtil);
                } else {
                    Integer uid=Integer.valueOf(uidStr);
                    //7.昵称，uid搜索框不为空，性别搜索框为空
                    usersList = userDao.queryBySome7(username, uid,pageUtil);
                }
            }
        }
        return usersList;
   }
    //修改用户密码
    public int updatePsd(String password,Integer uid) {
        return userDao.updatePsd(password,uid);
    }


    //根据用户昵称，id，性别查询相关用户
    public Integer queryCount1(String username, String usersexStr, String uidStr) {
        Integer count=0;
        Integer usersex;
        if (username.trim().equals("")) {//username.trim().equals("")代表的含义是昵称为空
            if (!usersexStr.trim().equals("")) {//性别非空
                if (usersexStr.equals("男")) {
                    usersex = 1;
                } else {
                    usersex = 0;
                }
                if (!uidStr.trim().equals("")) {//uid不空
                    Integer uid=Integer.valueOf(uidStr);
                    //1.昵称搜索框为空，性别，uid搜索框不为空,查询总数
                    count = userDao.queryCount1(usersex, uid);
                }
                if(uidStr.trim().equals("")){//uid空
                    //2.性别搜索框不为空，昵称，uid搜索框为空,查询总数
                    count = userDao.queryCount2(usersex);
                }
            } else {//性别为空
                if(!uidStr.trim().equals("")){//uid非空
                    Integer uid=Integer.valueOf(uidStr);
                    //3.性别，昵称搜索框为空，uid搜索框不为空,查询总数
                    count = userDao.queryCount3(uid);
                    // isUserNull(usersList);
                }
            }
        } else {//昵称非空
            if (!usersexStr.trim().equals("")) {//性别非空
                if (usersexStr.equals("男")) {
                    usersex = 1;
                } else {
                    usersex = 0;
                }
                if (!uidStr.trim().equals("")) {//uid非空
                    Integer uid=Integer.valueOf(uidStr);
                    //4.三个搜索框都不为空,查询总数
                    count = userDao.queryCount4(username, usersex, uid);
                } else {//uid为空
                    // 5.昵称，性别搜索框不为空，uid搜索框为空,查询总数
                    count = userDao.queryCount5(username, usersex);
                }
            } else {
                if (uidStr.trim().equals("")) {
                    //6.昵称搜索框不为空，性别，uid搜索框为空,查询总数
                    count = userDao.queryCount6(username);
                } else {
                    Integer uid=Integer.valueOf(uidStr);
                    //7.昵称，uid搜索框不为空，性别搜索框为空,查询总数
                    count = userDao.queryCount7(username, uid);
                }
            }
        }
        return count;
    }
}