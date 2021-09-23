<%--
  Created by IntelliJ IDEA.
  User: 19041
  Date: 2021/8/19
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>视频MV播放</title>
    <link href="${pageContext.request.contextPath}/index/css/video-js.min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/index/js/video.min.js"></script>
    <style type="text/css">
        .video-js .vjs-big-play-button {
            font-size: 2.5em;
            line-height: 2.3em;
            height: 2.5em;
            width: 2.5em;
            -webkit-border-radius: 2.5em;
            -moz-border-radius: 2.5em;
            border-radius: 2.5em;
            background-color: #73859f;
            background-color: rgba(115, 133, 159, .5);
            border-width: 0.15em;
            margin-top: -1.25em;
            margin-left: -1.75em;
        }
        /* 中间的播放箭头 */

        .vjs-big-play-button .vjs-icon-placeholder {
            font-size: 1.63em;
        }
        /* 加载圆圈 */

        .vjs-loading-spinner {
            font-size: 2.5em;
            width: 2em;
            height: 2em;
            border-radius: 1em;
            margin-top: -1em;
            margin-left: -1.5em;
        }
        /** 播放器显示控制 */

        .myVideo-dimensions {
            margin: 0 auto;
        }

        .ul {
            width: 1200px;
            height: 50px;
            border-bottom: 1px solid #AAAAFF;
            padding-top: 20px;
            margin-left: 10px;
        }

        .all {
            width: 1300px;
            height: 700px;
            border: 1px solid green;
            position: absolute;
            left: 7%;
        }
    </style>
</head>

<body oncontextmenu="self.event.returnValue=false" onselectstart="return false">
<div class="all">
    <div>
        <ul>
            <li class="ul">我的位置><a href="${pageContext.request.contextPath}/index/mv_list.jsp">MV播放</a>>>${video.viName}</li>
        </ul>
    </div>
    <div>
        <video id="myVideo" class="video-js vjs-big-play-centered" controls preload="auto" width="1200"
               height="500" poster="/MV/${video.vphoto}" data-setup='{"example_option":true}'>
            <source src="/MV/${video.viurl}" type='video/mp4' />
        </video>
    </div>
</div>

</body>

</html>