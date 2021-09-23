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
    <title>专辑修改</title>
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
    <form id="deptForm" action="${pageContext.request.contextPath}/cd?key=updateCd" method="post"
          enctype="multipart/form-data">
        <input type="hidden" name="cid" value="${cd.cid}">
        <div class="layui-form-item">
            <label class="layui-form-label required">专辑名</label>
            <div class="layui-input-block">
                <input type="text" name="cdName" id="cdName" lay-verify="required" lay-reqtext="专辑名不能为空"
                       placeholder="请输入专辑名"
                       value="${cd.cdName}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">歌手</label>
            <div class="layui-input-block">
                <input type="text" name="singer" lay-verify="required" lay-reqtext="歌手不能为空"
                       placeholder="请输入歌手" value="${cd.singer}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">地区</label>
            <div class="layui-input-block">
                <select name="city" lay-verify="" >
                    <option value=""></option>
                    <option value="华语" <c:if test="${cd.city=='华语'}">selected</c:if>>华语</option>
                    <option value="英语" <c:if test="${cd.city=='英语'}">selected</c:if>>英语</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">年代</label>
            <div class="layui-input-block">
                <select name="cyear" lay-verify="" >
                    <option value=""></option>
                    <option value="2020" <c:if test="${cd.cyear==2020}">selected</c:if>>2020</option>
                    <option value="2019" <c:if test="${cd.cyear==2019}">selected</c:if>>2019</option>
                    <option value="2018" <c:if test="${cd.cyear==2018}">selected</c:if>>2018</option>
                    <option value="2017" <c:if test="${cd.cyear==2017}">selected</c:if>>2017</option>
                    <option value="2016" <c:if test="${cd.cyear==2016}">selected</c:if>>2016</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">是否热门</label>
            <div class="layui-input-block">
                <c:if test="${cd.popular==1}">
                    <input type="radio" name="popular" value="1" title="是" checked="">
                    <input type="radio" name="popular" value="0" title="否">
                </c:if>
                <c:if test="${cd.popular==0}">
                    <input type="radio" name="popular" value="1" title="是">
                    <input type="radio" name="popular" value="0" title="否" checked="">
                </c:if>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label required">专辑图片上传</label>
            <div class="layui-input-block">
                <input type="file" class="layui-btn" name="cphoto" id="cphoto">
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
