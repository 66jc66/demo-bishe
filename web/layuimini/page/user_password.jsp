<%--
  Created by IntelliJ IDEA.
  User: 19041
  Date: 2021/8/9
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改密码</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="../css/public.css" media="all">
    <style>
        .layui-form-item .layui-input-company {
            width: auto;
            padding-right: 10px;
            line-height: 38px;
        }
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <%--        <form id="deptForm" action="${pageContext.request.contextPath}/user?key=updatePsd" method="post">--%>

        <div class="layui-form layuimini-form">
            <div class="layui-form-item">
                <label class="layui-form-label required">旧的密码</label>
                <div class="layui-input-block">
                    <input type="text" name="password" id="password" lay-verify="required" lay-reqtext="旧的密码不能为空"
                           placeholder="请输入旧的密码" value="" class="layui-input">
                    <tip>填写自己账号的旧的密码。</tip>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label required">新的密码</label>
                <div class="layui-input-block">
                    <input type="text" name="new_password" id="new_password" lay-verify="required"
                           lay-reqtext="新的密码不能为空" placeholder="请输入新的密码" value="" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">新的密码</label>
                <div class="layui-input-block">
                    <input type="text" name="again_password" id="again_password" lay-verify="required"
                           lay-reqtext="新的密码不能为空" placeholder="请确定新的密码" value="" class="layui-input">
                    <p><span id="msg">*</span></p>
                </div>

            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
                </div>
            </div>
        </div>

    </div>
</div>
<script src="../lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script src="../js/lay-config.js?v=1.0.4" charset="utf-8"></script>
<script>
    layui.use(['form', 'miniTab'], function () {
        var form = layui.form,
            layer = layui.layer,
            $ = layui.jquery,
            miniTab = layui.miniTab;
        $("#again_password").blur(function () {
            var psd1 = $("#new_password").val();
            var psd2 = $("#again_password").val();
            if (psd1 == psd2) {
                $("#msg").html("密码一致,请继续").css("color", "green");
            } else {
                $("#msg").html("两次密码不一致").css("color", "red");
            }
        })

        //监听提交
        form.on('submit(saveBtn)', function (data) {
            var index = layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            }, function () {
                layer.close(index);
                miniTab.deleteCurrentByIframe();
            });
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/user",
                data: {
                    "key": "updatePsd",
                    "password": $("#password").val(),
                    "uid": ${sessionScope.adminUser.uid},
                    "again_password": $("#again_password").val()
                },
                dataType: "json",//返回数据类型
                success: function (obj) {
                    if (obj == "fail") {
                        layer.msg('你的新旧密码一致，请输入一个不一样的密码', function () {

                        });
                    }
                    if (obj == "ok") {
                        layer.msg('修改成功', function () {
                        })
                    }
                }
            });
            return false;
        });

    });
</script>
</body>
</html>
