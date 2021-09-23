<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 19041
  Date: 2021/8/17
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>歌手排行榜</title>
</head>
<style>
    body {
        margin: 0;
    }
    /*最外层盒子*/
    .songRanking_content {
        width: 1400px;
        height: auto;
        margin: 0 auto;
        padding: 20px;
    }

    .songRanking_left_type {
        width: 200px;
        float: left;
        padding: 30px;
        border: 1px solid #C44969;
    }

    /*左侧显示分类类型，ul*/

    #songRanking_type_ul {
        padding: 0;
        margin: 0;
        border-top: 1px solid #C44969;
        border-bottom: 1px solid #C44969;
    }

    #songRanking_type_ul li {
        list-style: none;
        width: 180px;
        height: 40px;
        line-height: 50px;
        text-align: center;
        font-size: 14px;
        transition: all 0.3s;
    }

    #songRanking_type_ul li:hover {
        cursor: pointer;
        font-size: 16px;
        font-weight: 700;
        background: #E8E8E8;
    }

    /*右侧，显示每种导航下的歌手信息*/

    .songRanking_rihgt_songList {
        width: 1106px;
        height: auto;
        float: left;
        padding-bottom: 90px;
        padding-left: 20px;
    }

    .nav li {
        border-bottom: 1px solid #C44969;
        padding: 6px 14px;
        float: left;
        list-style: none;
    }
</style>
<script src="${pageContext.request.contextPath}/index/js/jquery-3.2.1.js" type="text/javascript"></script>

<body>
<div class="songRanking_content">

    <!--左侧导航栏-->
    <div class="songRanking_left_type">
        <div>
            <h2>全部歌手</h2>
        </div>
        <div>
            <ul id="songRanking_type_ul">
                <h3>华语歌手</h3>
                <li>
                    <a href="${pageContext.request.contextPath}/index?key=queryManSinger">华语男歌手</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/index?key=queryWomanSinger">华语女歌手</a>
                </li>
                <li>华语组合</li>
            </ul>
        </div>
        <div>
            <ul id="songRanking_type_ul">
                <h3>日韩歌手</h3>
                <li>日韩男歌手</li>
                <li>日韩女歌手</li>
                <li>日韩组合</li>
            </ul>
        </div>
        <div>
            <ul id="songRanking_type_ul">
                <h3>欧美歌手</h3>
                <li>欧美男歌手</li>
                <li>欧美女歌手</li>
                <li>欧美组合</li>
            </ul>
        </div>
    </div>
    <!--右侧显示歌曲列表-->
    <div class="songRanking_rihgt_songList">
        <div>
            <ul class=nav>
                <li><a href="${pageContext.request.contextPath}/index?key=queryAllSinger">全部</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=A">A</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=B">B</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=C">C</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=D">D</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=E">E</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=F">F</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=G">G</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=H">H</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=I">I</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=J">J</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=K">K</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=L">L</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=M">M</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=N">N</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=O">O</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=P">P</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=Q">Q</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=R">R</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=S">S</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=T">T</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=U">U</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=V">V</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=W">W</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=X">X</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=Y">Y</a></li>
                <li><a href="${pageContext.request.contextPath}/index?key=queryByFirstCode&firstCode=Z">Z</a></li>
            </ul>
        </div>
        <div>
            <c:forEach items="${singerList}" var="singer" varStatus="s">
                <div>
                    <div style=" width: 160px ;height: 200px;float: left;margin:30px ;">
                        <img src="/imgs/${singer.photo}"
                             style="width: 160px ;height: 160px; border-radius: 50%;">
                        <div style="width: 160px ;height: 20px; text-align: center; float: left;">
                            <p> ${singer.singerName}</p></div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <div style="clear: both;"></div>
</div>
</body>

</html>
