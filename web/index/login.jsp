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
            background: url("/index/images/9.jpg") center ;
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
<script type="text/javascript">
//读秒的方法
var iTime = 59;
var Account;
function RemainTime(){
	document.getElementById('zphone').disabled = true;
	var iSecond,sSecond="",sTime="";
	if (iTime >= 0){
		iSecond = parseInt(iTime%60);
		iMinute = parseInt(iTime/60)
		if (iSecond >= 0){
			if(iMinute>0){
				sSecond = iMinute + "分" + iSecond + "秒";
			}else{
				sSecond = iSecond + "秒";
			}
		}
		sTime=sSecond;
		if(iTime==0){
			clearTimeout(Account);
			sTime='获取手机验证码';
			iTime = 59;
			document.getElementById('zphone').disabled = false;
		}else{
			Account = setTimeout("RemainTime()",1000);
			iTime=iTime-1;
		}
	}else{
		sTime='没有倒计时';
	}
	document.getElementById('zphone').value = sTime;
}
</script>
</head>
<body>
<div class="register_head_on">

</div>
<div class="register_head">
    <a href="index.html"><img src="" alt=""></a>
    <div class="register_head_right">
        <p class="register_head_right_p1">用户登录</p>
    </div>

</div>

<div class="register">
    <div class="register_boby">
        <div class="register_boby_min">
            <div class="register_boby_no1">
                <div class="register_boby_no1_in">
                    <span style="color: #ff6700">手机验证码登录 </span>
                </div>
            </div>
            <form id="f3" action="${pageContext.request.contextPath}/user" method="post">
                <%--使用隐藏域传入key--%>
                <input type="hidden" name="key" value="checkCode">
                <span id="s_msg"></span>

            <!-- fs区分的方法 -->
            <input name="fs" value="checkCode" type="hidden">
            
            <div class="register_boby_no2">
            	<span id="msg" style="color: red;font-size: 12px;margin-left: 20px;"></span>
                <input id="phoneno" name="phoneno" type="text" placeholder="手机号码">
                
                <input name="code" id="code" type="password" placeholder="手机校验码" style="width: 200px; margin-left: 15px;float: left;">
                <!-- 新增加 -->
                <input id="zphone" type="button" value=" 获取手机验证码 "
                       style="width: 138px;float: left;height: 53px;margin-left: 5px;"       >
                <div style="clear: both;">
                
                <div class="register_boby_no2_div">
                    <span id="sub">登录</span>
                </div>
            </div>
            </div>
            </form>
            
            <div class="register_boby_no3">
                <a href="/index/numlogin.jsp" style="color: #ff6700">账号密码登录</a>
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
    $("#sub").click(function() {
        //获取输入的手机内容
        var phone = $("#phoneno").val();
        var code=$("#code").val();
        //判断输入内容是否为空
        if (phone == null || $.trim(phone).length == 0) {
            //给出错误提示
            $("#s_msg").html("手机号不能为空").css("color", "red");
            return;
        }
        if (code == null || $.trim(code).length == 0) {
            //给出错误提示
            $("#s_msg").html("验证码不能为空").css("color", "red");
            return;
        }
        //提交表单
        $("#f3").submit();
    })
    //点击获取手机验证码
    //绑定一个点击事件
    $("#zphone").click(function (){
        //验证输入的手机号不能为空，并且已经注册过
        //获取输入的手机内容
        var phoneno=$("#phoneno").val();
        //判断输入内容是否为空
        if(phoneno==null||$.trim(phoneno).length==0){
            //给出错误提示
            $("#s_msg").html("手机号不能为空！").css("color","red");
            return;
        }
        //输入的手机格式验证：正则表达式
        var reg=/^1[3456789]\d{9}$/;
        if(!reg.test(phoneno)){
            //给出错误提示
            $("#s_msg").html("手机格式错误！").css("color","red");
            return;
        }
        //验证手机号码是否已经注册：使用ajax实现
        $.ajax({
            url:"${pageContext.request.contextPath}/user",//需要使用绝对路径
            data:{
                "key":"checkPhone",
                "phoneno":phoneno
            },
            type:"post",
            dataType:"json",
            success:function (obj) {
                if (obj=="ok"){
                    //当前手机未注册，不能登录
                    $("#s_msg").html("请先注册再登录！").css("color","red");
                    return;
                } else{
                    //倒计时开始
                    RemainTime();
                    //当前手机已经注册过可以接收验证码
                    //使用ajax请求后台发送验证码到手机上
                    createCode(phoneno);
                }
            }
        })
    })
    //封装一个发送验证码的函数
    function createCode (phoneno) {
        $.ajax({
            url:"${pageContext.request.contextPath}/user",
            data:{
                "key":"sendCode",
                "phoneno":phoneno
            },
            type:"post",
            dataType: "json",
            success:function (obj) {
                if(obj=="ok"){
                    $("#s_msg").html("验证码发送成功！").css("color","green");
                }else{
                    $("#s_msg").html("验证码发送失败！").css("color","red");
                }

            }
        })
    }
</script>
</html>