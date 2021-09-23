package com.xyxy.servlet;

import com.alibaba.fastjson.JSON;
import com.xyxy.pojo.ResultInfo;
import com.xyxy.pojo.Users;
import com.xyxy.service.UserService;
import com.xyxy.util.PageUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserService();
    private ResultInfo resultInfo = new ResultInfo();

    //管理员登录
    public void checkAdmin(HttpServletRequest request, HttpServletResponse response) {
        //获取输入框中的输入的值
        String username = request.getParameter("username");
        String psd = request.getParameter("password");
        String code = request.getParameter("captcha");
        //调用service方法查询是否有数据存在
        Users users = userService.checkAdmin(username, psd);
        try {
            //从会话session中获取到之前生成存储的验证码
            HttpSession session = request.getSession();
            String varCode = (String) session.getAttribute("verCode");
            //比较两次验证码是否一致
            PrintWriter writer = response.getWriter();
            if (users != null) {
                if (code.equalsIgnoreCase(varCode)) {
                    //将查询到的对象存放到作用域中
                    session = request.getSession();
                    session.setAttribute("adminUser", users);
                    response.getWriter().write(JSON.toJSONString("ok"));
                } else {
                    response.getWriter().write(JSON.toJSONString("fail"));
                }
            } else {
                response.getWriter().write(JSON.toJSONString("fail"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //管理员退出
    public void adminLoginOut(HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        //将数据从作用域移除
        HttpSession session = request.getSession();
        session.removeAttribute("adminUser");
        response.sendRedirect("layuimini/page/login-2.jsp");
    }

    //增加管理员或者用户
    public void insertAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取表单中的数据
        String loginid = request.getParameter("loginid");
        String password = request.getParameter("password");
        String phoneno = request.getParameter("phoneno");
        String username = request.getParameter("username");
        String usersexStr = request.getParameter("usersex");
        Integer usersex = Integer.valueOf(usersexStr);
        String email = request.getParameter("email");
        String signs = request.getParameter("signs");
        Integer role = Integer.valueOf(request.getParameter("role"));
        //封装一个成对象
        Users users = new Users(usersex, loginid, password, phoneno, username, email, signs, role);
        //调用service中的添加方法
        int row = userService.insertUser(users);
    }

    //验证手机号是否唯一
    public void checkPhone(HttpServletRequest request, HttpServletResponse response) {
        //获取手机号码
        String phone = request.getParameter("phoneno");
        //调用service查询方法
        Users users = userService.checkPhone(phone);
        //根据返回结果判断
        try {
            if (users == null) {
                //手机号可以注册
                response.getWriter().write(JSON.toJSONString("ok"));
            } else {
                //手机号已经注册
                response.getWriter().write(JSON.toJSONString("fail"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //验证账号是否唯一
    public void checkLoginid(HttpServletRequest request, HttpServletResponse response) {
        //获取手机号码
        String loginid = request.getParameter("loginid");
        //调用service查询方法
        Users users = userService.checkLoginid(loginid);
        //根据返回结果判断
        try {
            if (users == null) {
                //账号可以注册
                response.getWriter().write(JSON.toJSONString("ok"));
            } else {
                //账号已经注册
                response.getWriter().write(JSON.toJSONString("fail"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //查询所有用户或管理员
    public void queryUsersAll(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        //获取页数和限制每页显示的数据
        Integer limit = Integer.valueOf(request.getParameter("limit"));
        String page = request.getParameter("page");
        //调用方法查询所有的数据数量
        Integer count = userService.queryCount();
        //使用分页工具类进行分页
        PageUtil<Users> pageUtil = new PageUtil<Users>(count, page, limit);
        List<Users> usersList = userService.queryUserByPage(pageUtil);
        //设置返回结果信息
        resultInfo.setCode(0);
        resultInfo.setCount(count);
        resultInfo.setResult(usersList);
        response.getWriter().write(JSON.toJSONString(resultInfo));
        //System.out.println(JSON.toJSONString(resultInfo));
    }

    //根据用户昵称，id，性别查询相关用户
    public void selectBySome(HttpServletRequest request,
                             HttpServletResponse response) throws Exception{
        //获取搜索框中的值进行查询
        String username = request.getParameter("username");
        String usersexStr = request.getParameter("usersex");
        String uidStr = request.getParameter("uid");
        //获取页数和限制每页显示的数据
        Integer limit = Integer.valueOf(request.getParameter("limit"));
        String page = request.getParameter("page");
        //调用方法查询所有的数据数量
        Integer count = userService.queryCount1(username,usersexStr,uidStr);
        //使用分页工具类进行分页
        PageUtil<Users> pageUtil = new PageUtil<Users>(count, page, limit);
        List<Users> users = userService.queryBySome(username, usersexStr, uidStr, pageUtil);
        //通过resultInfo来对json数据进行设计
        resultInfo.setCode(0);
        resultInfo.setCount(count);
        resultInfo.setResult(users);
        response.getWriter().write(JSON.toJSONString(resultInfo));
    }

    //根据id查询管理员或用户
    public void findById(HttpServletRequest request,
                         HttpServletResponse response) throws Exception {
        //获取去请求传过来的id值
        Integer uid = Integer.valueOf(request.getParameter("uid"));
        //调用方法查询到对象
        Users users = userService.checkById(uid);
        //将查询到的对象放入作用域
        HttpSession session = request.getSession();
        session.setAttribute("users", users);
        request.getRequestDispatcher("layuimini/page/user_edit.jsp").forward(request, response);
    }

    //修改用户或管理员信息
    public void updateUser(HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
        //获取表单中的数据
        Integer uid = Integer.valueOf(request.getParameter("uid"));
        String loginid = request.getParameter("loginid");
        String phoneno = request.getParameter("phoneno");
        String username = request.getParameter("username");
        String usersexStr = request.getParameter("usersex");
        Integer usersex = Integer.valueOf(usersexStr);
        String email = request.getParameter("email");
        String signs = request.getParameter("signs");
        Integer role = Integer.valueOf(request.getParameter("role"));
        //封装一个成对象
        Users users = new Users(uid, usersex, role, loginid, phoneno, username, email, signs);
        int row = userService.updateUser(users);
    }

    //根据id来删除管理员信息
    public void deleteById(HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
        //获取id值
        Integer uid = Integer.valueOf(request.getParameter("uid"));
        int row = userService.deleteById(uid);
        if (row > 0) {
            //删除成功返回200
            response.getWriter().write(JSON.toJSONString(200));
        } else {
            //删除失败返回201
            response.getWriter().write(JSON.toJSONString(201));
        }
    }

    //批量删除用户或者管理员
    public void batchDelete(HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
        //获取到勾选的字符串组合
        String ids = request.getParameter("ids");
        int row = userService.deleteByIds(ids);
        if (row > 0) {
            //删除成功返回200
            response.getWriter().write(JSON.toJSONString("200"));
        } else {
            //删除失败返回201
            response.getWriter().write(JSON.toJSONString("201"));
        }
    }

    //后台管理修改密码
   public void updatePsd(HttpServletRequest request,
                         HttpServletResponse response) throws Exception {
        //获取表单中的密码
       Integer uid = Integer.valueOf(request.getParameter("uid"));
       String password = request.getParameter("again_password");
       String password1 = request.getParameter("password");
      if(password.equals(password1)){
          //如果新旧密码一样就返回fail
          response.getWriter().write(JSON.toJSONString("fail"));
      }else{
          //如果不一样，调用service方法来修改密码
          int row= userService.updatePsd(password,uid);
          response.getWriter().write(JSON.toJSONString("ok"));
      }
   }




    //用户退出
    public void userLoginOut(HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        session.removeAttribute("indexUser");
        response.sendRedirect("index/index.jsp");

    }

    //根据返回结果判断
    //根据账号查询
    //验证账号是否正确
    public void checkUser(HttpServletRequest request, HttpServletResponse response) {
        //获取账号信息
        String loginid = request.getParameter("loginid");
        //调用userService查询方法
        Users users = userService.checkUser(loginid);
        HttpSession session = request.getSession();
        try {
            if (users == null) {
                //账号不可以登录
                response.getWriter().write(JSON.toJSONString("fail"));
            } else {
                //账号可以登录
                response.getWriter().write(JSON.toJSONString("ok"));
                session.setAttribute("indexUser", users);
                response.sendRedirect("index/index.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //增加用户
    public void insertUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取表单中的数据
        String loginid = request.getParameter("loginid");
        String password = request.getParameter("password");
        String phoneno = request.getParameter("phoneno");
        String username = request.getParameter("username");
        String usersexStr = request.getParameter("usersex");
        Integer usersex = Integer.valueOf(usersexStr);
        String email = request.getParameter("email");
        String signs = request.getParameter("signs");
        //封装一个成对象
        Users users = new Users(usersex, loginid, password, phoneno, username, email, signs);
        //调用service中的添加方法
        int row = userService.insertUser(users);
        if (row > 0) {
            response.sendRedirect("index/login.jsp");
        }
    }


    /*使用手机号验证码登录*/
    public void checkCode(HttpServletRequest request,
                          HttpServletResponse response) {
        //获取手机号和验证码
        String phone = request.getParameter("phoneno");
        String code = request.getParameter("code");
        String newCode = phone + "---" + code;
        //获取session存储的手机验证码值
        HttpSession session = request.getSession();
        String loginCode = (String) session.getAttribute("loginCode");
        //比较手机和验证码
        try {
            //登录成功,跳转首页
            if (newCode.equals(loginCode)) {
                //根据手机号查询当前的登录账号
                Users users = userService.checkPhone(phone);
                //将当前的登录账号存储session
                session.setAttribute("indexUser", users);
                response.sendRedirect("index/index.jsp");
            } else {
                //登录失败,继续登录
                response.sendRedirect("index/login.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*发送验证码*/
    public void sendCode(HttpServletRequest request, HttpServletResponse response) {
        //获取请求中的手机号
        String phone = request.getParameter("phoneno");
        //根据手机号发送验证码：调用工具类
        //int code = SendSms.sendCode(phone);
        int code = 445869;
        try {
            if (code == 0) {
                response.getWriter().write(JSON.toJSONString("fail"));
            } else {
                //验证码发送成功，将手机号和验证码保存到session中
                HttpSession session = request.getSession();
                String loginCode = phone + "---" + code;
                session.setAttribute("loginCode", loginCode);
                response.getWriter().write(JSON.toJSONString("ok"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
