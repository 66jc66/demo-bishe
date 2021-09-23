<%--
  Created by IntelliJ IDEA.
  User: 19041
  Date: 2021/8/15
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>相关排行榜</title>
</head>
<style>
    body {
        margin: 0;
    }

    /*最外层盒子*/

    .songRanking_content {
        width: 1200px;
        height: auto;
        margin: 0 auto;
        padding: 20px;
    }

    /*左边导航栏，显示“播放排行榜”、“下载排行榜”、“收藏排行榜”*/

    .songRanking_left_type {
        width: 250px;
        height: 100%;
        float: left;
        padding: 30px;
        border: 1px solid #696969;
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
        width: 250px;
        height: 70px;
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

    .songRanking_type_ul_selectes {
        font-size: 16px !important;
        font-weight: 700;
        background: #E8E8E8;
    }

    /*右侧，显示每种导航下的歌曲信息*/

    .songRanking_rihgt_songList {
        width: 749px;
        height: auto;
        float: left;
        padding-bottom: 90px;
        padding-left: 20px;
    }

    #songRanking_table {
        margin-top: 20px;
        width: 100%;
    }

    #songRanking_table tr {
        height: 35px;
        text-align: center;
    }

    #songRanking_table tr:hover {
        background: #E2E2E2 !important;
    }

    #songRanking_table tr th td:nth-child(1) {
        width: 30px;
        text-align: center;
        font-size: 16px;
        font-weight: 600;
    }

    #btn {
        border-bottom: 1px solid #C44969;
        padding: 10px;
    }
</style>
<script src="index/js/jquery-3.2.1.js" type="text/javascript"></script>

<body>
<!--用户把点击加入歌单显示页面-->
<div class="addSongList_showDiv">
    <div class="addSongList_songList" songId>
        <!--用于显示用户创建的歌单-->
    </div>
</div>
<div class="songRanking_content">

    <!--左侧导航栏-->
    <div class="songRanking_left_type">
        <div>
            <h2>全部歌曲榜单</h2>
        </div>
        <div>
            <ul id="songRanking_type_ul">
                <h3>歌曲榜单</h3>
                <li><a href="/index?key=queryAllPlay">热度排行榜</a></li>
                <li><a href="/index?key=queryAllDowm">下载排行榜</a></li>
                <li><a href="/index?key=queryAllDis">热议排行榜</a></li>
            </ul>
        </div>
        <div>
            <ul id="songRanking_type_ul">
                <h3>地区榜单</h3>
                <li>华语排行榜</li>
                <li>英语排行榜</li>
            </ul>
        </div>
    </div>
    <!--右侧显示歌曲列表-->
    <div class="songRanking_rihgt_songList">
        <div id="btn">
            <button>榜单</button>
        </div>
        <table id="songRanking_table" cellpadding="0" cellspacing="0">
            <tr>
                <th>排名</th>
                <th>歌曲名</th>
                <th>歌手名</th>
                <th>时长</th>
            </tr>
                <c:forEach items="${songList}" var="song" varStatus="sg">
                    <tr>
                        <td>${sg.count}</td>
                        <td><a href="${pageContext.request.contextPath}/index?key=querySongDetail&sid=${song.sid}">${song.songName}</a></td>
                        <td>${song.singerName}</td>
                        <td>${song.songTime}</td>
                    </tr>
                </c:forEach>
        </table>
    </div>
    <div style="clear: both;"></div>
</div>
</body>

</html>
