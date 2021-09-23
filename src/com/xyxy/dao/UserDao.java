package com.xyxy.dao;

import com.xyxy.pojo.Users;
import com.xyxy.util.PageUtil;

import java.util.List;

public interface UserDao {
    //增加用户
    int insertUser(Users users);

    //根据手机号查询
    Users checkPhone(String phone);

    //根据账号查询
    Users checkUser(String loginid);

    //管理员查询
    Users checkByAdmin(String username, String psd);

    //查询总数
    Integer queryCount();
    //1.性别，uid搜索框不为空，昵称搜索框为空,查询总数
    Integer queryCount1(Integer usersex, Integer uid);
    //2.昵称，uid搜索框为空，性别搜索框不为空,查询总数
    Integer queryCount2(Integer usersex);
    //3.uid搜索框不为空，昵称和性别搜索框为空,查询总数
    Integer queryCount3(Integer uid);
    //4.三个搜索框都不为空,性别搜索框为空,查询总数
    Integer queryCount4(String username, Integer usersex, Integer uid);
    //5.昵称，性别搜索框不为空，uid搜索框为空,查询总数
    Integer queryCount5(String username, Integer usersex);
    //6.昵称搜索框不为空，性别，uid搜索框为空,查询总数
    Integer queryCount6(String username);
    //7.昵称，uid搜索框不为空，性别搜索框为空,查询总数
    Integer queryCount7(String username, Integer uid);


    //分页查询用户
    List<Users> queryUserByPage(PageUtil<Users> pageUtil);

    //删除用户
    int deleteById(Integer uid);

    //id查询
    Users checkById(Integer uid);

    //修改用户
    int updateUser(Users users);

    //查询所有用户
    List<Users> queryListAll();

    //查询账号是否唯一
    Users checkLoginid(String loginid);

    //1.根据用户uid,性别查询相关用户
    List<Users> queryBySome1(Integer usersex, Integer uid,PageUtil<Users> pageUtil);
    //2.根据用户性别查询相关用户
    List<Users> queryBySome2(Integer usersex,PageUtil<Users> pageUtil);
    //3.根据用户uid查询相关用户
    List<Users> queryBySome3(Integer uid,PageUtil<Users> pageUtil);
    //4.根据用户昵称，id，性别查询相关用户
    List<Users> queryBySome4(String username, Integer usersex, Integer uid,PageUtil<Users> pageUtil);
    //5.根据用户昵称，性别查询相关用户
    List<Users> queryBySome5(String username, Integer usersex,PageUtil<Users> pageUtil);
    //6.根据用户昵称查询相关用户
    List<Users> queryBySome6(String username,PageUtil<Users> pageUtil);
    //7.根据用户昵称，uid查询相关用户
    List<Users> queryBySome7(String username, Integer uid,PageUtil<Users> pageUtil);

    //修改用户密码
    int updatePsd(String password,Integer uid);



}
