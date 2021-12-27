<%--
  Created by IntelliJ IDEA.
  User: 19041
  Date: 2021/8/16
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>歌曲详情</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/index/css/songdetail.css">
</head>
<style>
    #comment_ul {
        width: 700px;
        margin: 0 auto;
        padding: 0;
        border-bottom: 2px solid #00FFFF;
        margin-bottom: 30px;
    }

    .text {
        display: block;
        float: left;
        list-style-type: none;
        margin-left: 20px;

        height: auto;

    }

    .time {
        display: block;
        list-style-type: none;
        padding: 30px 0 10px 70px;
    }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/index/js/jquery-3.2.1.js"></script>
<body>
<!-- 我的音乐，iframe加载页面 -->
<div class="mymusic_content">

    <div class="mymusic_content_right">
        <div id="showOrNot">
            <div class="music_right_header">
                <div class="songListImgDiv">
                    <img alt="图片" src="/index/images/songlistimg.jpg" height="200" width="200">
                </div>
                <div class="music_right_header_songlist_info">
                    <p>
                        <img src="${pageContext.request.contextPath}/index/images/play.jpg" height="25" width="25"><a
                            href="javascript:void(0);" onclick="songplay(${song.sid})">播放</a>
                    </p>
                </div>
            </div>
            <h2 class="songList_song_title">歌曲列表</h2>
            <div class="songList_song">
                <table id="song_list_table" cellspacing="0" cellpadding="0" border="0">
                    <tr class="table_title">
                        <td></td>
                        <td>歌曲标题</td>
                        <td>时长</td>
                        <td>歌手</td>
                        <td>专辑</td>
                        <td>试听</td>
                        <td>下载</td>
                    </tr>
                    <tr>
                        <td style="text-align: center">*</td>
                        <td>${song.songName}</td>
                        <td>${song.songTime}</td>
                        <td>${song.singerName}</td>
                        <td>${Cd.cdName}</td>
                        <td>
                            <audio controls>
                                <source src="/songurl/${song.songUrl}" type="audio/mp3">
                            </audio>
                        </td>
                        <td>
                            <c:if test="${sessionScope.indexUser==null}">
                            <img onclick="songdown()" src="images/down.jpg" style="width: 20px;height: 20px"></td>
                        </c:if>
                        <c:if test="${sessionScope.indexUser!=null}">
                            <img onclick="load('${song.songName}','${song.songUrl}')" src="images/down.jpg"
                                 style="width: 20px;height: 20px"></td>
                        </c:if>

                    </tr>

                </table>
            </div>
            <h2 class="songList_comment_title">评论</h2>
            <div class="write_songlist_comment">
                <img align="top" id="user_header_photo" src="${pageContext.request.contextPath}/index/images/01.jpg"
                     width="50" height="50"/>
                <textarea id="songlist_comment_textarea" placeholder="评论" rows="4" cols="85"></textarea>
                <c:if test="${sessionScope.indexUser!=null}">
                    <button id="comment_submit" type="button"
                            onclick="songcom(${sessionScope.indexUser.uid},${song.sid})">评论
                    </button>
                </c:if>
                <c:if test="${sessionScope.indexUser==null}">
                    <button id="comment_submit" type="button" onclick="songcomment()">评论</button>
                </c:if>
            </div>
            <c:forEach items="${comList}" var="com" varStatus="co">
                <ul id="comment_ul">
                    <div style="height: 70px;width: 700px;margin-top: 20px">
                        <img src="images/user.jpg" style="float: left;width: 50px;height: 50px">
                        <li class="text">${com.users.username}:${com.comtext}</li>
                        <li class="time">评论时间：${com.comtime}
                            <c:if test="${sessionScope.indexUser!=null}">
                                <img onclick="comdelete('${com.comtime}',${song.sid})" src="images/delete.jpg"
                                     style="padding-left: 300px">
                            </c:if>
                            <c:if test="${sessionScope.indexUser==null}">
                                <img onclick="comtip()" src="images/delete.jpg" style="padding-left: 300px">
                            </c:if>

                        </li>

                    </div>
                </ul>
            </c:forEach>
        </div>
    </div>
</div>
</body>
<script>
    function songcomment() {
        alert("请先登录后，再进行评论");
    }

    function songdown() {
        alert("请先登录后，再进行下载");
    }
    function comtip() {
        alert("请先登录后，再进行操作");
    }

    function songcom(uid, sid) {
        $.ajax({
            url: "${pageContext.request.contextPath}/com",//需要使用绝对路径
            type: "post",
            data: {
                "key": "insertComment",
                "uid": uid,
                "sid": sid
            },
            dataType: "json",
            success: function (obj) {
                console.log(obj);
            }
        })
    }

    function comdelete(comtime, sid) {
        $.ajax({
            url: "${pageContext.request.contextPath}/com",//需要使用绝对路径
            type: "post",
            data: {
                "key": "deleteByComtime",
                "comtime": comtime,
                "sid": sid
            },
            dataType: "json",
            success: function (obj) {
                console.log(obj);
                if (obj == 200) {
                    alert("你确定要删除评论吗");
                    window.location = '${pageContext.request.contextPath}/index?key=querySongDetail&sid=${song.sid}';
                }
            }
        });
    }

    function songplay(sid) {
        $.ajax({
            url: "${pageContext.request.contextPath}/index",//需要使用绝对路径
            type: "post",
            data: {
                "key": "updateSongPlay",
                "sid": sid
            },
            dataType: "json",
            success: function (obj) {
                console.log(obj);
                location.href = "${pageContext.request.contextPath}/index/songplay.jsp";
            }
        });
    }

    function load(songname, songurl) {
        alert("是否下载")
        $.ajax({
            url: "${pageContext.request.contextPath}/index",//需要使用绝对路径
            type: "post",
            data: {
                "key": "updateSongDown",
                "songName": songname,
                "songUrl": songurl
            },
            dataType: "json",
            success: function (obj) {
                console.log(obj);
                if (obj == 200) {
                    alert("下载成功")
                } else if (obj == 201) {
                    alert("文件不存在，下载失败")
                } else if (obj == 202) {
                    alert("文件已经存在，不用重复下载")
                }
            }
        });
    }
</script>
</html>
