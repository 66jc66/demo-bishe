<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>修改用户</title>
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
    <form id="deptForm" action="${pageContext.request.contextPath}/user?key=updateUser" method="post">
        <input type="hidden" name="uid" value="${users.uid}">
        <div class="layui-form-item">
            <label class="layui-form-label required">用户名</label>
            <div class="layui-input-block">
                <input type="text" name="loginid" lay-verify="required" lay-reqtext="账号不能为空" placeholder="请输入账号"
                       value="${users.loginid}" class="layui-input">
                <tip></tip>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">手机</label>
            <div class="layui-input-block">
                <input type="number" name="phoneno" lay-verify="required" lay-reqtext="手机不能为空" placeholder="请输入手机"
                       value="${users.phoneno}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">昵称</label>
            <div class="layui-input-block">
                <input type="text" name="username" lay-verify="required" lay-reqtext="昵称不能为空" placeholder="请输入昵称"
                       value="${users.username}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">性别</label>
            <div class="layui-input-block">
                <c:if test="${users.usersex==1}">
                    <input type="radio" name="usersex" value="1" title="男" checked="">
                    <input type="radio" name="usersex" value="0" title="女">
                </c:if>
                <c:if test="${users.usersex==0}">
                    <input type="radio" name="usersex" value="1" title="男">
                    <input type="radio" name="usersex" value="0" title="女" checked="">
                </c:if>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-block">
                <input type="email" name="email" placeholder="请输入邮箱" value="${users.email}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">个签</label>
            <div class="layui-input-block">
                <input type="text" name="signs" placeholder="请输入你的个性签名" value="${users.signs}" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">权限</label>
            <div class="layui-input-block">
                <c:if test="${users.role==1}">
                    <input type="radio" name="role" value="1" title="管理员" checked="">
                    <input type="radio" name="role" value="0" title="会员用户">
                </c:if>
                <c:if test="${users.role==0}">
                    <input type="radio" name="role" value="1" title="管理员">
                    <input type="radio" name="role" value="0" title="会员用户" checked="">
                </c:if>
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
            // $.ajax({
            //     url: '/user?key=updateUser',
            //     type: 'POST',
            //     dataType: 'json',
            //     data: {},
            //     success:function (data) {
            //         form.val("layuiAdmin-form", {
            //             loginid: data.field.loginid,
            //             phoneno: data.field.phoneno,
            //             username: data.field.username,
            //             usersex: data.field.usersex,
            //             email: data.field.email,
            //             signs: data.field.signs,
            //             role: data.field.role
            //         });
            //     }
            // });
            return false;
        });

    });
</script>
</body>
</html>