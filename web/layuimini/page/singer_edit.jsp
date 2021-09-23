<%--
  Created by IntelliJ IDEA.
  User: 19041
  Date: 2021/8/9
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>修改歌手信息</title>
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
    <form id="deptForm" action="${pageContext.request.contextPath}/singer?key=updateSinger" method="post" enctype="multipart/form-data">
        <input type="hidden" name="srid" value="${singer.srid}">
        <div class="layui-form-item">
            <label class="layui-form-label required">歌手名</label>
            <div class="layui-input-block">
                <input type="text" name="singerName" id="singerName" lay-verify="required" lay-reqtext="歌手名不能为空"
                       placeholder="请输入歌手名"
                       value="${singer.singerName}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">歌手描述</label>
            <div class="layui-input-block">
                <input type="text" name="descp" lay-verify="required" lay-reqtext="歌手描述不能为空"
                       placeholder="请输入歌手描述" value="${singer.descp}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">类型名称</label>
            <div class="layui-input-block">
                <input type="text" name="typeName" lay-verify="required" lay-reqtext="类型名称不能为空" placeholder="请输入类型名称"
                       value="${singer.typeName}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">性别</label>
            <div class="layui-input-block">
                <c:if test="${singer.gender==1}">
                    <input type="radio" name="gender" value="1" title="男" checked="">
                    <input type="radio" name="gender" value="0" title="女">
                </c:if>
                <c:if test="${singer.gender==0}">
                    <input type="radio" name="gender" value="1" title="男">
                    <input type="radio" name="gender" value="0" title="女" checked="">
                </c:if>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">首字母</label>
            <div class="layui-input-block">
                <input type="text" name="firstCode" lay-verify="required" lay-reqtext="首字母不能为空" placeholder="请输入首字母"
                       value="${singer.firstCode}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">是否热门</label>
            <div class="layui-input-block">
                <c:if test="${singer.popular==1}">
                    <input type="radio" name="popular" value="1" title="是" checked="">
                    <input type="radio" name="popular" value="0" title="否">
                </c:if>
                <c:if test="${singer.popular==0}">
                    <input type="radio" name="popular" value="1" title="是">
                    <input type="radio" name="popular" value="0" title="否" checked="">
                </c:if>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label required">歌手图片上传</label>
            <div class="layui-input-block">
                <input type="file" class="layui-btn" name="photo" id="photo">
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