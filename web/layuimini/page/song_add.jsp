<%--
  Created by IntelliJ IDEA.
  User: 19041
  Date: 2021/8/7
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>歌曲添加</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="../css/public.css" media="all">
    <style>
        body {
            background-color: #ffffff;
        }
    </style>
</head>
<body>
<div class="layui-form layuimini-form">
    <form id="detForm" action="${pageContext.request.contextPath}/song?key=insertSong" method="post" enctype="multipart/form-data">
        <div class="layui-form-item">
            <label class="layui-form-label required">歌曲名</label>
            <div class="layui-input-block">
                <input type="text" name="songName" id="songName" lay-verify="required" lay-reqtext="歌曲名不能为空"
                       placeholder="请输入歌曲名"
                       value="" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">歌手名</label>
            <div class="layui-input-block">
                <input type="text" name="singerName" lay-verify="required" lay-reqtext="歌手名不能为空"
                       placeholder="请输入歌手名" value="" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">专辑编号</label>
            <div class="layui-input-block">
                <input type="number" name="cdId" lay-verify="required" lay-reqtext="专辑号不能为空" placeholder="请输入专辑号"
                       value="" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">语言</label>
            <div class="layui-input-block">
                <input type="text" name="language" lay-verify="required" lay-reqtext="语言不能为空" placeholder="请输入语言"
                       value="" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">歌词</label>
            <div class="layui-input-block">
                <input type="text" name="geci" lay-verify="required" lay-reqtext="歌词不能为空" placeholder="请输入歌词"
                       value="" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">发行时间</label>
            <div class="layui-input-block">
                <input type="datetime" name="faDate" placeholder="请输入发行时间" value="" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">歌曲上传</label>
            <div class="layui-input-block">
                <input type="file" class="layui-btn" name="songUrl" id="songUrl" value="">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">歌曲时长</label>
            <div class="layui-input-block">
                <input type="time" name="songTime" placeholder="请输入歌曲时长" value="" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
            </div>
        </div>
    </form>
</div>

<script src="../lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'upload'], function () {
        var form = layui.form,
            upload = layui.upload,
            layer = layui.layer,
            $ = layui.jquery;
        //账号绑定一个失去焦点事件
        $("#songName").blur(function () {
            //获取账户信息
            var songName = $("#songName").val();
            //验证账号是否为空
            if (songName == null || $.trim(songName).length == 0) {
                layer.msg("歌曲名不能为空");
                return;
            }
            //验证账号是否已经注册：使用ajax异步
            $.ajax({
                url: "${pageContext.request.contextPath}/song",//需要使用绝对路径
                type: "post",
                data: {
                    "key": "checkSongName",
                    "songName": songName
                },
                dataType: "json",
                success: function (obj) {
                    console.log(obj);
                    if (obj == "ok") {
                        layer.msg("歌曲名无重复，可以添加 ");
                    } else {
                        layer.msg("歌曲已有，请添加新的歌曲");
                    }
                }
            })
        });
        //监听提交
        form.on('submit(saveBtn)', function (data) {
            var index = layer.alert(JSON.stringify(data.field), {
                    title: '最终的提交信息'
                },
                document.getElementById('detForm').submit()
                // function () {
                //      // 关闭弹出层
                //     layer.close(index);
                //     var iframeIndex = parent.layer.getFrameIndex(window.name);
                //     parent.layer.close(iframeIndex);
                // }

            );
            /* $.ajax({
                 url: '/song?key=insertSong',
                 type: 'POST',
                 dataType: 'json',
                 data: {
                     songName: data.field.songName,
                     singerName: data.field.singerName,
                     cdId: data.field.cdId,
                     language: data.field.language,
                     faDate: data.field.faDate
                 },
                 success: function (msg) {
                     var returnCode = msg.returnCode;
                     if (returnCode == 200) {
                         layer.closeAll('loading');
                         layer.load(2);
                         layer.msg("添加成功", {icon: 6});
                         setTimeout(function () {
                             window.parent.location.reload();//修改成功后刷新父界面
                         }, 1000);
                         //加载层-风格

                     } else {
                         layer.msg("已经存在该歌曲,不支持重复添加", {icon: 5});
                     }
                 }
             });*/
            return false;
        });

    });
</script>
</body>
</html>
