package com.xyxy.servlet;



import com.xyxy.util.VerifyCodeUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
 * 生成验证码
 */
@WebServlet("/authImage")
public class AuthImage extends HttpServlet{

    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //告知浏览器不要缓存验证码
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");//响应的数据类型

        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        /*
         	此时在Servlet中生成的验证码为什么要存储到会话Session这个域对象中呢？
         	1、我们生成完验证码，显示在页面后，紧接着我们需要手动输入在图形中的内容，然后进行比较，是否输入正确
         		所以肯定需要将生成的验证码进行存储
         	2、后台的域对象总共有四个：
         		pageContext仅限于jsp页面，而验证码是在Servlet中生成的，故不行！
         		request仅限于一次请求，那么生成验证码的请求结束后，验证码数据随之就丢失了，故不行！
         		验证码的存储时间，不需要在整个项目运行期间，所以不需要用到全局管理者！
         	3、而会话结束后，肯定需要重新登录，所以我们选取Session作为验证码存储的域对象
         */
        //存入会话session
        HttpSession session = request.getSession();
        //删除以前的
        session.removeAttribute("verCode");
        //存储会话的目的是为了输入后进行比较
        session.setAttribute("verCode", verifyCode);
        //System.out.println(session.getAttribute("verCode"));
        //生成图片
        int w = 100, h = 30;
        /*
         	w 是响应图片的宽度
         	h 是响应图片的高度
         	response.getOutputStream() 响应图片的流对象
         	verifyCode 内嵌在图片中的验证码信息，就是那生成的验证码字符串！
         */
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }
}



