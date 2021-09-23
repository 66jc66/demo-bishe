<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<html>
<head>
    <title>页面展示</title>
    <link rel="stylesheet" type="text/css" href="css/IndexIframe.css">
</head>
<script src="${pageContext.request.contextPath}/index/js/lunbo.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/index/js/jquery-3.2.1.js" type="text/javascript"></script>

<body id="indexIframe_body">
<!-- 轮播图开始 -->

<div id="zong_div">
    <div id="container">
        <div id="list" style="left: -1000px;">
            <img src="images/5.jpg"/>
            <img src="images/1.jpg"/>
            <img src="images/2.jpg"/>
            <img src="images/3.jpg"/>
            <img src="images/4.jpg"/>
            <img src="images/5.jpg"/>
            <img src="images/1.jpg"/>
        </div>
        <div id="buttons">
            <span index="1" class="on"></span>
            <span index="2"></span>
            <span index="3"></span>
            <span index="4"></span>
            <span index="5"></span>
        </div>
        <a href="javascript:;" class="arrow" id="prev">
            <</a>
        <a href="javascript:;" class="arrow" id="next">></a>
    </div>
</div>
<!-- 轮播图结束 -->
<div class="index_singer_info">
    <div class="clear"></div>
    <div class="index_singer_info_left">
        <div style="clear:both;"></div>
        <!--歌曲排行榜-->
        <div id="song_ranking_list">
            <div class="index_reco_1">
                <i></i><span>歌曲排行榜</span>
            </div>
            <div class="index_songCountList">
                <!--播放次数歌曲显示-->
                <div class="song_playcount_list">
                    <div class="songCount_header playCount_header">热度榜</div>
                    <table class="songList_table" cellpadding="0" cellspacing="0" id="playCount_song_table">
                        <c:forEach items="${songList1}" var="song" varStatus="sg">
                            <tr>
                                <td>${sg.count}:${song.songName}</td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td><a href="${pageContext.request.contextPath}/index?key=queryAllPlay">查看全部</a></td>
                        </tr>
                    </table>
                </div>
                <!--下载次数歌曲显示-->
                <div class="song_downloadcount_list">
                    <div class="songCount_header downloadCount_header">
                        下载榜
                    </div>
                    <table class="songList_table" cellpadding="0" cellspacing="0" id="downloadCount_song_table">
                        <c:forEach items="${songList2}" var="song" varStatus="sg">
                            <tr>
                                <td>${sg.count}:${song.songName}</td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td><a href="${pageContext.request.contextPath}/index?key=queryAllDowm">查看全部</a></td>
                        </tr>
                    </table>
                </div>
                <!--评论次数歌曲显示-->
                <div class="song_collectioncount_list">
                    <div class="songCount_header collectionCount_header">热议榜</div>
                    <table class="songList_table" cellpadding="0" cellspacing="0" id="collectionCount_song_table">
                        <c:forEach items="${songList3}" var="song" varStatus="sg">
                            <tr>
                                <td>${sg.count}:${song.songName}</td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td><a href="${pageContext.request.contextPath}/index?key=queryAllDis">查看全部</a></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>

    </div>
    <div class="index_singer_info_right">

    </div>
    <div class="clear"></div>
</div>
</body>
</html>