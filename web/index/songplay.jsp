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
    <meta charset="utf-8">
    <link rel="shortcut icon" href="#"/>
    <link rel="stylesheet" type="text/css" href="./css/common.css"/>
    <link rel="stylesheet" type="text/css" href="./css/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="./css/index.css"/>
    <script type="text/javascript" src="./js/selector.js"></script>
    <script type="text/javascript" src="./js/script.js"></script>
<%--    <script type="text/javascript" src="./js/jquery-3.2.1.js"></script>--%>
    <title>Music</title>
</head>
<body oncontextmenu="return false;" onselectstart="return false">
<textarea name="" id="txt0" cols="30" rows="10" style="display: none;">
    ${song.songgc}
</textarea>
<div class="page Opage">
    <div class="main">
        <div class="Lbox l">
            <img src="./images/0.jpg"><br>
        </div>

        <div class="Rbox r">
            <h2 class="songName"></h2>
            <span class="singer"></span>
            <div class="Lyric"></div>
        </div>

    </div>
    <div class="footer">
        <div class="main">

            <div class="l left">
                <span class="last iconfont icon-kuaitui" title="上一首"></span>&nbsp;&nbsp;
                <span class="play iconfont Iconfont icon-zanting" title="播放"></span>&nbsp;&nbsp;
                <span class="next iconfont icon-kuaijin" title="下一首"></span>
            </div>

            <div class="content clearfix">
                <div class="Box">
                    <span class="songName l" id="sn"></span><span class="l">&nbsp;-&nbsp;</span><span class="singer l" id="sen"></span>
                    <span class="r">
								<span class="realTime">00:00</span>&nbsp;/&nbsp;<span class="totalTime"></span>
							</span><br>
                    <div class="strip">
                        <div class="progress"></div>
                        <span class="spot"></span>
                    </div>
                </div>
                <div class="volumeBox" style="display: none;">
                    <div class="groove">
                        <div class="volume"></div>
                        <span class="volumeSpot"></span>
                    </div>
                    <div class="triangle"></div>
                </div>&nbsp;
                <span class="iconfont icon-shengyin"></span>
            </div>

            <div class="r right">
                <div class="list" style="display: none;">
                    <div class="Song"></div>
                </div>
                <div class="listTriangle" style="display: none;"></div>
                <div class="gengduoBox" style="display: none;">
                    <span class="project iconfont icon-fenxiang" title="分享"></span>
                    <div class="boxTriangle" style="display: none;"></div>
                </div>
                <span class="iconfont loop icon-liebiaoxunhuan" title="循环方式"></span>&nbsp;&nbsp;&nbsp;
                <span class="iconfont icon-liebiao liebiao" title="播放列表" style="font-size: 26px;"></span>&nbsp;&nbsp;&nbsp;
                <span class="iconfont icon-shezhi gengduo" title="更多" style="font-size: 26px;"></span>
            </div>

        </div>
    </div>
</div>
<audio id="myMusic" src="/songurl/${song.songUrl}"></audio>
</body>
<script>
    var songName = "${song.songName}"
    var singers = "${song.singerName}"
</script>
</html>
