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
[00:18.980]好久没见了什么角色呢
[00:23.220]细心装扮着
[00:25.010]白色衬衫的袖扣是你送的
[00:31.680]尽量表现着像不在意的
[00:35.870]频繁暴露了自欺欺人者
[00:41.250]越掩饰越深刻
[00:44.290]你说我说听说
[00:47.570]忍着言不由衷的段落
[00:50.970]
[00:51.860]我反正决定自己难过
[00:58.430]
[01:01.200]我想摸你的头发
[01:03.400]只是简单的试探啊
[01:06.740]
[01:09.490]我想给你个拥抱
[01:11.830]像以前一样可以吗
[01:16.620]你退半步的动作认真的吗
[01:21.280]小小的动作伤害还那么大
[01:25.560]我只能扮演个绅士
[01:28.750]才能和你说说话
[01:32.840]
[01:34.540]我能送你回家吗
[01:37.150]可能外面要下雨啦
[01:40.340]
[01:43.220]我能给你个拥抱
[01:45.450]像朋友一样可以吗
[01:48.820]
[01:50.630]我忍不住从背后抱了一下
[01:55.010]尺度掌握在不能说想你啊
[01:58.990]你就当刚认识的绅士
[02:02.580]闹了个笑话吧
[02:07.650]
[02:21.060]尽量表现着善解人意的
[02:25.320]频繁暴露了不欲人知的
[02:30.880]越掩饰越深刻
[02:33.830]想说听说别说
[02:37.000]忍着言不由衷的段落
[02:41.130]我反正注定留在角落
[02:48.030]
[02:50.270]我想摸你的头发
[02:52.930]只是简单的试探啊
[02:57.690]
[02:58.700]我想给你个拥抱
[03:01.320]像以前一样可以吗
[03:05.020]
[03:06.440]你退半步的动作认真的吗
[03:10.860]小小的动作伤害还那么大
[03:14.980]我只能扮演个绅士
[03:18.270]才能和你说说话
[03:22.140]
[03:24.110]我能送你回家吗
[03:26.650]可能外面要下雨啦
[03:31.970]我能给你个拥抱
[03:35.020]像朋友一样可以吗
[03:38.640]
[03:40.090]我忍不住从背后抱了一下
[03:44.570]尺度掌握在不能说想你啊
[03:48.480]你就当刚认识的绅士
[03:52.130]闹了个笑话吧
[03:56.320]
[03:57.980]你能给我只左手
[04:00.230]牵你到马路那头吗
[04:03.880]
[04:05.790]我会像以前一样
[04:08.700]看着来往的车子啊
[04:13.890]我们的距离在眉间皱了下
[04:18.430]迅速还原成路人的样子啊
[04:22.680]越有礼貌我越害怕
[04:25.760]绅士要放得下
[04:39.760]
</textarea>
<textarea name="" id="txt1" cols="30" rows="10" style="display: none;"></textarea>
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
                <a class="l"><img src="./images/0.jpg" title="点击全屏"></a>&nbsp;
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
