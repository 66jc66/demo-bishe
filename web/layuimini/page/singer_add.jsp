<%--
  Created by IntelliJ IDEA.
  User: 19041
  Date: 2021/8/9
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>歌手添加</title>
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
    <form id="detForm" action="${pageContext.request.contextPath}/singer?key=insertSinger" method="post" enctype="multipart/form-data">
        <div class="layui-form-item">
            <label class="layui-form-label required">歌手名</label>
            <div class="layui-input-block">
                <input type="text" name="singerName" id="singerName" lay-verify="required" lay-reqtext="歌手名不能为空"
                       placeholder="请输入歌手名"
                       value="" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="gender" value="1" title="男" checked="">
                <input type="radio" name="gender" value="0" title="女">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">歌手描述</label>
            <div class="layui-input-block">
                <input type="text" name="descp" lay-verify="required" lay-reqtext="歌手描述不能为空" placeholder="请输入歌手描述"
                       value="" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">类型名称</label>
            <div class="layui-input-block">
                <input type="text" name="typeName" lay-verify="required" lay-reqtext="类型名称不能为空" placeholder="请输入类型名称"
                       value="" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">首字母</label>
            <div class="layui-input-block">
                <input type="datetime" name="firstCode" placeholder="请输入首字母" value="" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">歌手图片上传</label>
            <div class="layui-input-block">
                <input type="file" class="layui-btn" name="photo" id="photo" value="">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">是否热门</label>
            <div class="layui-input-block">
                <input type="radio" name="popular" value="1" title="是" checked="">
                <input type="radio" name="popular" value="0" title="否">
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
            layer = layui.layer,
            $ = layui.jquery;
        //账号绑定一个失去焦点事件
        $("#singerName").blur(function () {
            //获取账户信息
            var singerName = $("#singerName").val();
            //验证账号是否为空
            if (singerName == null || $.trim(singerName).length == 0) {
                layer.msg("歌手名不能为空");
                return;
            }
            //验证账号是否已经注册：使用ajax异步
            $.ajax({
                url: "${pageContext.request.contextPath}/singer",//需要使用绝对路径
                type: "post",
                data: {
                    "key": "checkBySingerName",
                    "singerName": singerName
                },
                dataType: "json",
                success: function (obj) {
                    console.log(obj);
                    if (obj == "ok") {
                        layer.msg("歌手无重复，可以添加 ");
                    } else {
                        layer.msg("歌手已有，请添加新的歌手");
                    }
                }
            })
        });
        //监听提交
        form.on('submit(saveBtn)', function (data) {
            //获取数据，并使用弹框显示在页面上
            var index = layer.alert(JSON.stringify(data.field), {
                    title: '最终的提交信息'
                },
                //提交表单
                document.getElementById('detForm').submit()
                // function () {
                //      // 关闭弹出层
                //     layer.close(index);
                //     var iframeIndex = parent.layer.getFrameIndex(window.name);
                //     parent.layer.close(iframeIndex);
                // }

            );
            return false;
        });

    });
</script>
</body>
</html>

