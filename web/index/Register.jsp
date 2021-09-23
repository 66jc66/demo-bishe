<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" href="css/Register.css">
</head>
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
<body>
<div class="divShang"></div>
<div class="divXia">
    <form id="form1" name="form1" action="${pageContext.request.contextPath}/user" method="post">
        <input type="hidden" name="key" value="insertUser">
        <table border="0" align="center" cellpadding="0" cellspacing="0">
            <caption>用户注册</caption>
            <tr>
                <td>
                    <input type="text" placeholder="用户账号" name="loginid" id="loginid" required="required"
                           pattern="[a-zA-Z][a-zA-Z0-9_]{5,17}"/>
                    <p id="loginId_text"><span>*</span>&nbsp;&nbsp;6-18个字符，包括字母、数字、下划线,字母开头</p>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="password" placeholder="密码" name="password" id="password" required="required"
                           pattern="[a-zA-Z0-9_!@#$%^&*]{6,16}"/>
                    <p><span>*</span>&nbsp;&nbsp;6~16个字符，区分大小写</p>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="password" placeholder="确认密码" name="psd2" id="psd2"/>
                    <p><span id="msg">*</span></p>
                    <p id="psd_text"><span>*</span>&nbsp;&nbsp;与密码保持一致</p>
                </td>
            </tr>
            <tr>
                <td>
                    <input name="phoneno" placeholder="手机号码" id="phoneno" pattern="1[3|5|7|8][0-9]{9}"/>
                    <p id="phone_text">密码遗忘或被盗时，可以通过手机短信找回密码</p>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="username" placeholder="昵称" id="username" required="required">
                    <p><span>*</span>&nbsp;&nbsp;中英文都可</p>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="radio" name="usersex" id="usersexman" value="0" checked="checked">
                    <label for="usersexman">男</label>
                    <input type="radio" name="usersex" style="margin-left:25px;" id="usersexwoman" value="1">
                    <label for="usersexwoman">女</label>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="email" placeholder="用户邮箱" id="email">
                    <p id="email_text">用户邮箱</p>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="signs" placeholder="个人签名" id="signs">
                    <p>个人签名</p>
                </td>
            </tr>

            <tr>
                <td><input type="submit" id="register_button" value="立即注册"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
<script>
    $("#psd2").blur(function () {
        var psd1=$("#password").val();
        var psd2=$("#psd2").val();
        if(psd1==psd2){
            $("#msg").html("密码一致,请继续").css("color","green");
        }else {
            $("#msg").html("两次密码不一致").css("color","red");
        }
    })

</script>
</html>
