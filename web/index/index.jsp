<%--
  Created by IntelliJ IDEA.
  User: 19041
  Date: 2021/8/2
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" href="/index/css/index.css">
    <style>
        .header_a1_little_menu {
            width: 100%;
            height: 35px;
            background: #99BDF9;
            display: flex;
            flex-flow: row;
            justify-content: center;
        }

        .index_singer {
            width: 100%;
            height: 575px;
            padding: 0;
            margin: 0;
            border: 0;
            background: #F5F5F5;
            overflow: auto;

        }

        .index_iframe {
            width: 100%;
            height: 575px;
            padding: 0;
            margin: 0;
            border: 0;
            background: #F5F5F5;
            overflow: auto;

        }

    </style>
</head>
<script type="text/javascript" src="/index/js/jquery-3.2.1.js"></script>

<body>
<div class="header">
    <!-- 顶部导航栏，发现音乐、我的音乐、动态、音乐人、搜索框、登录按钮 -->
    <header class="index_header" id="index_header">
        <!-- LOGO -->
        <div class="LOGO">
            <img src="/index/images/LOGO.png" align="center"><span>音乐广场</span>
        </div>
        <!-- 发现音乐、我的音乐、MV、专辑 -->
        <nav class="index_header_nav">
            <input type="radio" id="header_radio1" checked="checked" name="indexHeaderA"/>
            <a href="${pageContext.request.contextPath}/index/IndexIframe.jsp" target="main" id="header_a1">发现音乐</a>
            <c:if test="${sessionScope.indexUser==null}">
                <input type="radio" id="header_radio2" name="indexHeaderA"/>
                <a href="javascript:void(0);" onclick="mymusic(${sessionScope.indexUser.uid})" target="main"
                   id="header_a2">我的音乐</a>
            </c:if>
            <c:if test="${sessionScope.indexUser!=null}">
                <input type="radio" id="header_radio2" name="indexHeaderA"/>
                <a href="${pageContext.request.contextPath}/index?key=queryAllDownLoad" target="main" id="header_a2">
                    我的音乐</a>
            </c:if>
            <input type="radio" id="header_radio3" name="indexHeaderA"/>
            <a href="${pageContext.request.contextPath}/index?key=queryAllMv" target="main" id="header_a3">MV</a>
            <input type="radio" id="header_radio4" name="indexHeaderA"/>
            <a href="${pageContext.request.contextPath}/index?key=queryAllCd" target="main" id="header_a4">专辑</a>
        </nav>
        <!-- 搜索框 -->
        <div class="index_header_seek">
            <form id="for" action="##">
                <input type="text" placeholder="" id="searchSongName_input" align="center"/>
                <input type="button" id="search_button" value="搜索" align="center" />
            </form>
        </div>
        <!-- 登录前“登录按钮”， 登录后显示用户名及相关菜单 -->
        <div id="index_login_div" class="index_header_login">
            <div id="login_before" class="dropdown">
                <%-- 如果当前没有登录显示登录和注册按钮--%>
                <c:if test="${sessionScope.indexUser==null}">
                    <span>登录</span>
                    <div class="login_way">
                        <a href="${pageContext.request.contextPath}/index/login.jsp"><p><img id="phone" alt=""
                           src="/index/images/phone.png"  align="center" style="height: 17px;">
                            手机号登录</p></a>
                        <a href="${pageContext.request.contextPath}/index/numlogin.jsp"><p><img id="num" alt=""
                             src="/index/images/zhanghao.png"  align="center" style="height: 17px;">
                            账号登录</p></a>
                    </div>
                </c:if>
                <c:if test="${sessionScope.indexUser!=null}">
                    <span>个人中心</span>
                    <div class="login_way">
                        <p><span align="center">欢迎${sessionScope.indexUser.loginid}到来</span>
                        <p><span> <a href="${pageContext.request.contextPath}/user?key=userLoginOut"
                                     target="_parent">退出</a> </span>
                    </div>
                </c:if>
            </div>
        </div>
    </header>
</div>

<div class="clear"></div>
<div class="header_a1_little_menu">
    <div>
        <c:if test="${sessionScope.indexUser==null}">
            <input type="radio" id="little_menu_input1" checked="checked" name="menu_input"/>
            <a href="${pageContext.request.contextPath}/index/IndexIframe.jsp" target="main" id="menu_a1">推荐</a>
            <input type="radio" id="little_menu_input2" name="menu_input"/>
            <a href="${pageContext.request.contextPath}/index?key=queryAllPlay" target="main" id="menu_a2">排行榜</a>
            <input type="radio" id="little_menu_input1" checked="checked" name="menu_input"/>
            <a href="${pageContext.request.contextPath}/index?key=queryAllSinger" target="main" id="menu_a3">歌手</a>
            <input type="radio" id="little_menu_input1" checked="checked" name="menu_input"/>
            <a href="${pageContext.request.contextPath}/index/song_detail.jsp" target="main" id="menu_a4">歌曲</a>
        </c:if>
        <c:if test="${sessionScope.indexUser!=null}">
            <input type="radio" id="little_menu_input1" checked="checked" name="menu_input"/>
            <a href="IndexIframe.jsp" target="main" id="menu_a1">推荐</a>
            <input type="radio" id="little_menu_input2" name="menu_input"/>
            <a href="${pageContext.request.contextPath}/index?key=queryAllPlay" target="main" id="menu_a2">排行榜</a>
            <input type="radio" id="little_menu_input4" name="menu_input"/>
            <a href="${pageContext.request.contextPath}/index?key=queryAllSinger" target="main" id="menu_a3">歌手</a>
            <input type="radio" id="little_menu_input1" checked="checked" name="menu_input"/>
            <a href="${pageContext.request.contextPath}/index/song_detail.jsp" target="main" id="menu_a4">歌曲</a>
        </c:if>
    </div>
</div>
<iframe src="${pageContext.request.contextPath}/index/IndexIframe.jsp" name="main" id="main" class="index_iframe">
</iframe>
<div class="phone_login">
</div>

</body>
<script>
    $("#search_button").click(function () {
        var song = $("#searchSongName_input").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/index",
            dataType: "json",
            type: "post",
            data: {
                "key": "checkBySong",
                "song":song,
            },
            success: function (obj) {
                if (obj == "ok") {
                    console.log("查询成功") ;
                    //在当前页面跳转就可以，不需要跳转新的页面，只需要改变子页面的内容就可以
                    window.top.main.location = '${pageContext.request.contextPath}/index/song_search.jsp';
                    $("#searchSongName_input").val('');

                } else {
                    console.log("查询失败")
                }
            }
        })
    })

    function mymusic(uid) {
        if (uid == null) {
            alert("你还没有登录，请去登录后进行操作");
        }
    }
</script>
</html>
