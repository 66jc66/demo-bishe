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
    <title>MV修改</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge  ,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layuimini/lib/layui-v2.6.3/css/layui.css"
          media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layuimini/css/public.css" media="all">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/layuimini/lib/jq-module/zyupload/zyupload-1.0.0.min.css" media="all">
    <style>
        body {
            background-color: #ffffff;
        }
    </style>
</head>
<body>
<div class="layui-form layuimini-form" lay-filier="layuiAdmin-form">
    <form id="deptForm" action="${pageContext.request.contextPath}/video?key=updateVideo" method="post"
          enctype="multipart/form-data">
        <input type="hidden" name="vid" value="${video.vid}">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label required">MV名称</label>
                <div class="layui-input-block">
                    <input type="text" name="viName" id="viName" lay-verify="required" lay-reqtext="MV名称不能为空"
                           placeholder="请输入MV名称"
                           value="${video.viName}" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">歌手</label>
                <div class="layui-input-inline">
                    <input type="text" name="singer" lay-verify="required" lay-reqtext="歌手不能为空" placeholder="请输入歌手"
                           value="${video.singer}" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label ">地区</label>
                <div class="layui-input-block">
                    <select name="vcity" lay-verify="">
                        <option value=""></option>
                        <option value="华语" <c:if test="${video.vcity=='华语'}">selected</c:if>>华语</option>
                        <option value="英语" <c:if test="${video.vcity=='英语'}">selected</c:if>>英语</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">年代</label>
                <div class="layui-input-block">
                    <select name="vyear" lay-verify="">
                        <option value=""></option>
                        <option value="2020" <c:if test="${video.vyear==2020}">selected</c:if>>2020</option>
                        <option value="2019" <c:if test="${video.vyear==2019}">selected</c:if>>2019</option>
                        <option value="2018" <c:if test="${video.vyear==2018}">selected</c:if>>2018</option>
                        <option value="2017" <c:if test="${video.vyear==2017}">selected</c:if>>2017</option>
                        <option value="2016" <c:if test="${video.vyear==2016}">selected</c:if>>2016</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label required">是否热门</label>
            <div class="layui-input-block">
                <c:if test="${video.popular==1}">
                    <input type="radio" name="popular" value="1" title="是" checked="">
                    <input type="radio" name="popular" value="0" title="否">
                </c:if>
                <c:if test="${video.popular==0}">
                    <input type="radio" name="popular" value="1" title="是">
                    <input type="radio" name="popular" value="0" title="否" checked="">
                </c:if>
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <div class="layui-inline">
                <label class="layui-form-label required">MV图片上传</label>
                <div class="layui-input-block">
                    <input type="file" class="layui-btn" name="vphoto" id="vphoto" value="">
                </div>
            </div>
        </div>

      <%--  <div class="layui-form-item layui-form-text">
            <div class="layui-inline">
                <label class="layui-form-label required">MV视频上传</label>
                                <div class="layui-input-block">
                                    <input type="file" class="layui-btn" name="viurl" id="viurl" value="">
                                </div>
                <div class="layui-input-block">
                    <div id="zyupload" class="zyupload"></div>
                </div>
            </div>
        </div>
--%>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
            </div>
        </div>
    </form>
</div>

<script src="${pageContext.request.contextPath}/layuimini/lib/jquery-3.4.1/jquery-3.4.1.min.js"
        charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/layuimini/lib/jq-module/zyupload/zyupload-1.0.0.min.js"
        charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/layuimini/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'upload'], function () {
        var form = layui.form,
            layer = layui.layer,
            upload = layui.upload,
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
    /*$(function () {
        // 初始化插件
        $("#zyupload").zyUpload({
            width: "650px",                 // 宽度
            height: "320px",                 // 宽度
            itemWidth: "140px",                 // 文件项的宽度
            itemHeight: "115px",                 // 文件项的高度
            url: "api/upload",  // 上传文件的路径
            fileType: ["jpg", "png", "txt", "js", "exe", "mp4"],// 上传文件的类型
            fileSize: 51200000,                // 上传文件的大小
            multiple: true,                    // 是否可以多个文件上传
            dragDrop: true,                    // 是否可以拖动上传文件
            tailor: true,                    // 是否可以裁剪图片
            /!* 外部获得的回调接口 *!/
            onSelect: function (selectFiles) {    // 选择文件的回调方法  selectFile:当前选中的文件
                console.info("当前选择了以下文件：");
                console.info(selectFiles);
            },
            onSuccess: function (file, response) {          // 文件上传成功的回调方法
                console.info("此文件上传成功：");
                console.info(file.name);
                console.info("此文件上传到服务器地址：");
                console.info(response);
                $("#uploadInf").append("<p>上传成功，文件地址是：" + response + "</p>");
            },
            onFailure: function (file, response) {          // 文件上传失败的回调方法
                console.info("此文件上传失败：");
                console.info(file.name);
            },
            onComplete: function (response) {           	  // 上传完成的回调方法
                console.info("文件上传完成");
                console.info(response);
            }
        });
    });*/
</script>
</body>
</html>
