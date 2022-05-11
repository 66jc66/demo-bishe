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
    <title>我的音乐</title>
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
                    <img alt="图片" src="${pageContext.request.contextPath}/index/images/01.jpg" height="200" width="200">
                </div>
                <div class="music_right_header_songlist_info">
                    <p>
                        <img src="${pageContext.request.contextPath}/index/images/user.jpg" height="40" width="40">
                    <p class="user">${sessionScope.indexUser.username}</p>
                    </p>

                    <p class="sign">
                        个性签名：${sessionScope.indexUser.signs}
                    </p>
                </div>

            </div>
            <h2 class="songList_song_title">本地下载</h2>
            <div class="songList_song">
                <table id="song_list_table" cellspacing="0" cellpadding="0" border="0">
                    <tr class="table_title">
                        <td></td>
                        <td>歌曲标题</td>
                        <td>时长</td>
                        <td>歌手</td>
                        <td>专辑</td>
                    </tr>
                    <c:forEach items="${songDown}" var="song" varStatus="sg">
                        <tr>
                            <td style="text-align: center">${sg.count}</td>
                            <td><a href="${pageContext.request.contextPath}/index?key=querySongDetail&sid=${song.sid}">${song.songName}</a></td>
                            <td>${song.songTime}</td>
                            <td>${song.singerName}</td>
                            <td>${song.cd.cdName}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
</body>

</html>

