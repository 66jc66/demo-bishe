<%--
  Created by IntelliJ IDEA.
  User: 19041
  Date: 2021/8/11
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>评论修改</title>
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
    <form id="deptForm" action="${pageContext.request.contextPath}/com?key=updateCom" method="post">
        <input type="hidden" name="comid" value="${comment.comid}">
        <div class="layui-form-item">
            <label class="layui-form-label required">用户id</label>
            <div class="layui-input-block">
                <input type="text" name="uid" id="uid" lay-verify="required" lay-reqtext="用户id不能为空"
                       placeholder="请输入用户id"
                       value="${comment.uid}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">歌曲id</label>
            <div class="layui-input-block">
                <input type="text" name="sid" lay-verify="required" lay-reqtext="歌曲id不能为空"
                       placeholder="请输入歌曲id" value="${comment.sid}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">内容</label>
            <div class="layui-input-block">
                <input type="text" name="comtext" lay-verify="required" value="${comment.comtext}" class="layui-input" >
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">评论时间</label>
            <div class="layui-input-block">
                <input type="text" name="comtime" lay-verify="required" value="${comment.comtime}" class="layui-input">
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
