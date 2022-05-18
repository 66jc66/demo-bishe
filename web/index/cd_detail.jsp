<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 19041
  Date: 2021/8/17
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>歌手介绍</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/index/css/mymusic.css">
</head>
<style>
    .mymusic_content_right {
        border-left: 1px solid #00FFFF;
        border-right: 1px solid #00FFFF;
        overflow: auto;
    }

    .user {
        float: left;
        margin-left: 25px;
    }

    .sign {
        width: 495px;
        margin-top: 40px;
        margin-left: 25px;
        float: left;
        font-size: 15px;
    }
</style>

<script type="text/javascript" src="${pageContext.request.contextPath}/index/js/jquery-3.2.1.js"></script>
<body>
<div class="mymusic_content">

    <div class="mymusic_content_right">
        <div id="showOrNot">
            <div class="music_right_header">
                <div class="songListImgDiv">
                    <img src="/imgs/${Cd.cphoto}" height="200" width="200">
                </div>

            </div>
            <h2 class="songList_song_title">个人歌曲</h2>
            <div class="songList_song">
                <table id="song_list_table" cellspacing="0" cellpadding="0" border="0">
                    <tr class="table_title">
                        <td>序号</td>
                        <td>歌曲标题</td>
                        <td>时长</td>
                        <td>发行时间</td>
                        <td>评论次数</td>
                    </tr>
                    <c:forEach items="${songcds}" var="song" varStatus="sg">
                        <tr>
                            <td style="text-align: center">${sg.count}</td>
                            <td><a href="${pageContext.request.contextPath}/index?key=querySongDetail&sid=${song.sid}">${song.songName}</a></td>
                            <td>${song.songTime}</td>
                            <td>${song.faDate}</td>
                            <td>${song.discussNum}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
</body>

</html>

