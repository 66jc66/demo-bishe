<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>
    <script src="${pageContext.request.contextPath}/index/js/jquery-3.2.1.js" type="text/javascript"></script>
    <style>
        .register{
            width:100%;
            height:100%;
            background: url("./images/9.jpg") center ;
        }
        .register_head{
            width: 1130px;
            height: 30px;
            margin: 0 auto;
            padding-top:11px;
        }
        .register_head img{
            float: left;
        }
        .register_head_right{
            width: 200px;;
            height: 20px;
            float: left;
            margin-left: 20px;
        }
        .register_head_right_p1{
            font-size: 26px;
            color: #333;
        }

        .register_boby{
            width: 29%;
            height: 645px;
            /*background: pink;*/
            /*opacity: 0.5;*/
            margin: 0 auto;
        }
        .register_boby_min{
            width: 410px;
            height: 524px;
            float: right;
            background: white;
            margin-top: 20px;
        }
        .register_boby_no1_in{
            margin-top: 23px;
            margin-left: 42px;

        }

        .register_boby_no1{
            width:410px;
            height:83px;
            margin-top: 0px;
            float: right;
            font-size: 29px;
            color: #cccccc;

        }
        .register_boby_no2{
            width: 410px;
            height: 230px;
            float: right;
        }
        .register_boby_no2 input{
            width: 346px;
            height: 22px;
            line-height: 22px;
            padding: 13px 16px 13px 14px;
            display: block;
            margin: 10px auto;
        }
        .register_boby_no2_div{
            width: 376px;
            height: 48px;
            background: #ff6700;
            margin: 0 auto;
        }
        .register_boby_no2_div span{
            text-align: center;
            line-height: 48px;
            color: white;
            display: block;
            margin: 0 auto;
            cursor: pointer;
        }

        .register_boby_no2 p{
            color: #e0e0e0;
            font-size: 8px;
        }
        .register_boby_no3{
            width: 376px;
            font-size: 14px;
            margin:0 auto;

        }
        .register_boby_no3_span{
            float: right;
            color: #5f5750;
        }


    </style>

</head>
<body>
<div class="register_head_on">

</div>
<div class="register_head">
    <div class="register_head_right">
        <p class="register_head_right_p1">用户登录</p>
    </div>
</div>
<div class="register">
    <div class="register_boby">
        <div class="register_boby_min">
            <div class="register_boby_no1">
                <div class="register_boby_no1_in">
                    <span style="color: #ff6700">用户密码登录 </span>
                </div>
            </div>
            <form id="f3" action="${pageContext.request.contextPath}/user" method="post">
                <%--使用隐藏域传入key--%>
                <input type="hidden" name="key" value="checkUser">
                <span id="s_msg"></span>

                <!-- fs区分的方法 -->
                <input name="fs" value="checkCode" type="hidden">
                <div class="register_boby_no2">
                    <span id="msg" style="color: red;font-size: 12px;margin-left: 20px;"></span>
                    <input id="loginid" name="loginid" type="text" placeholder="用户账号">

                    <input name="password" id="password" type="password" placeholder="用户密码">
                    <div style="clear: both;">
                        <div class="register_boby_no2_div">
                            <span id="sub" onclick="sub()">登录</span>
                        </div>
                    </div>
                </div>
            </form>
            <div class="register_boby_no3">
                <a href="login.jsp" style="color: #ff6700">手机登录</a>
                <sapn class="register_boby_no3_span">
                    <a href="Register.jsp">立即注册</a>
                    <span>|</span>
                    <a href="login.jsp">忘记密码?</a>
                </sapn>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    //验证手机号
    function sub(){
        var num = $("#loginid").val();
        var pass=$("#password").val();
        //判断输入内容是否为空
        if (num == null || $.trim(num).length == 0) {
            //给出错误提示
            $("#s_msg").html("账号不能为空").css("color", "red");
            return;
        }

        if (pass == null || $.trim(pass).length == 0) {
            //给出错误提示
            $("#s_msg").html("密码不能为空").css("color", "red");
            return;
        }
        //提交表单
        $("#f3").submit();
    }
    $("#loginid").blur(function() {
        //获取输入的手机内容
        var num = $("#num").val();
        var pass=$("#pass").val();
        //判断输入内容是否为空
        if (num == null || $.trim(num).length == 0) {
            //给出错误提示
            $("#s_msg").html("账号不能为空").css("color", "red");
            return;
        }
        $.ajax({
            url:"${pageContext.request.contextPath}/user",//需要使用绝对路径
           data:{
                "key":"checkUser",
                "loginid":loginid
            },
            type:"post",
            dataType:"json",
            success:function (obj) {
                if (obj=="fail"){
                    //当前账号未注册，不能登录
                    $("#s_msg").html("请先注册再登录！").css("color","red");
                    return;
                }
            }
        })
        if (pass == null || $.trim(pass).length == 0) {
            //给出错误提示
            $("#s_msg").html("密码不能为空").css("color", "red");
            return;
        }

    })


</script>
</html>