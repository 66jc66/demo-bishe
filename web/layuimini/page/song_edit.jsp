<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>修改歌曲信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge  ,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layuimini/lib/layui-v2.6.3/css/layui.css"
          media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layuimini/css/public.css" media="all">
    <style>
        body {
            background-color: #ffffff;
        }
    </style>
</head>
<body>
<div class="layui-form layuimini-form" lay-filier="layuiAdmin-form">
    <form id="deptForm" action="${pageContext.request.contextPath}/song?key=updateSong" method="post" enctype="multipart/form-data">
        <input type="hidden" name="sid" value="${songs.sid}">
        <div class="layui-form-item">
            <label class="layui-form-label required">歌曲名</label>
            <div class="layui-input-block">
                <input type="text" name="songName" id="songName" lay-verify="required" lay-reqtext="歌曲名不能为空"
                       placeholder="请输入歌曲名"
                       value="${songs.songName}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">歌手名</label>
            <div class="layui-input-block">
                <input type="text" name="singerName" lay-verify="required" lay-reqtext="歌手名不能为空"
                       placeholder="请输入歌手名" value="${songs.singerName}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">专辑编号</label>
            <div class="layui-input-block">
                <input type="number" name="cdId" lay-verify="required" lay-reqtext="专辑号不能为空" placeholder="请输入专辑号"
                       value="${songs.cdId}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">下载次数</label>
            <div class="layui-input-block">
                <input type="number" name="downloadCount" lay-verify="required" lay-reqtext="下载次数不能为空" placeholder="请输入下载次数"
                       value="${songs.downloadCount}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">播放次数</label>
            <div class="layui-input-block">
                <input type="number" name="playCount" lay-verify="required" lay-reqtext="播放次数不能为空" placeholder="请输入播放次数"
                       value="${songs.playCount}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">评论次数</label>
            <div class="layui-input-block">
                <input type="number" name="discussNum" lay-verify="required" lay-reqtext="评论次数不能为空" placeholder="请输入评论次数"
                       value="${songs.discussNum}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">语言</label>
            <div class="layui-input-block">
                <input type="text" name="language" lay-verify="required" lay-reqtext="语言不能为空" placeholder="请输入语言"
                       value="${songs.language}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">歌词</label>
            <div class="layui-input-block">
                <input type="text" name="geci" lay-verify="required" lay-reqtext="歌词不能为空" placeholder="请输入歌词"
                       value="${songs.songgc}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">发行时间</label>
            <div class="layui-input-block">
                <input type="datetime" name="faDate" placeholder="请输入发行时间" value="${songs.faDate}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">歌曲上传</label>
            <div class="layui-input-block">
                <input type="file" class="layui-btn" name="songUrl" id="songUrl">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">歌曲时长</label>
            <div class="layui-input-block">
                <input type="time" name="songTime" placeholder="请输入歌曲时长" value="${songs.songTime}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
            </div>
        </div>
    </form>
</div>

<script src="${pageContext.request.contextPath}/layuimini/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form'], function () {
        var form = layui.form,
            layer = layui.layer,
            $ = layui.jquery;

        //监听提交
        form.on('submit(saveBtn)', function (data) {
            //在页面上用弹框显示要操作的数据
            var index = layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            }, function () {
                // 关闭弹出层
                layer.close(index);
                //提交表单
                document.getElementById('deptForm').submit();
                //获取到页面的相关信息
                var iframeIndex = parent.layer.getFrameIndex(window.name);
                //操作完成后父刷新页面
                parent.layer.close(iframeIndex);

            });
            return false;
        });

    });
</script>
</body>
</html>