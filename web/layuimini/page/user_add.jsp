<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户添加</title>
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
    <div class="layui-form-item">
        <label class="layui-form-label required">用户名</label>
        <div class="layui-input-block">
            <input type="text" name="loginid" id="loginid" lay-verify="required" lay-reqtext="账号不能为空"
                   placeholder="请输入账号" value="" class="layui-input">
            <tip>填写自己管理账号的名称。</tip>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">密码</label>
        <div class="layui-input-block">
            <input type="number" name="password" lay-verify="required" lay-reqtext="密码不能为空" placeholder="请输入密码" value=""
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">手机</label>
        <div class="layui-input-block">
            <input type="number" name="phoneno" id="phoneno" lay-verify="required" lay-reqtext="手机不能为空"
                   placeholder="请输入手机" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">昵称</label>
        <div class="layui-input-block">
            <input type="text" name="username" lay-verify="required" lay-reqtext="昵称不能为空" placeholder="请输入昵称" value=""
                   class="layui-input">
            <tip>填写自己的昵称。</tip>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">性别</label>
        <div class="layui-input-block">
            <input type="radio" name="usersex" value="1" title="男" checked="">
            <input type="radio" name="usersex" value="0" title="女">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-block">
            <input type="email" name="email" placeholder="请输入邮箱" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">个签</label>
        <div class="layui-input-block">
            <input type="text" name="signs" placeholder="请输入你的个性签名" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">权限</label>
        <div class="layui-input-block">
            <input type="radio" name="role" value="1" title="管理员" checked="">
            <input type="radio" name="role" value="0" title="会员用户">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
        </div>
    </div>
</div>
<script src="../lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form'], function () {
        var form = layui.form,
            layer = layui.layer,
            $ = layui.jquery;
         //验证账号
        //账号绑定一个失去焦点事件
        $("#loginid").blur(function () {
            //获取账户信息
            var loginid = $("#loginid").val();
            //验证账号是否为空
            if (loginid == null || $.trim(loginid).length == 0) {
                layer.msg("账号不能为空");
                return;
            }
            //验证账号是否已经注册：使用ajax异步
            $.ajax({
                url: "${pageContext.request.contextPath}/user",//需要使用绝对路径
                type: "post",
                data: {
                    "key": "checkLoginid",
                    "loginid": loginid
                },
                dataType: "json",
                success: function (obj) {
                    console.log(obj);
                    if (obj == "ok") {
                        layer.msg("账号可以注册");
                    } else {
                        layer.msg("账号已经存在不能注册");
                    }
                }
            })
        });
        //验证手机号
        $("#phoneno").blur(function () {
            //获取输入的手机内容
            var phoneno = $("#phoneno").val();
            //判断输入内容是否为空
            if (phoneno == null || $.trim(phoneno).length == 0) {
                //给出错误提示
                layer.msg("该手机号不能为空");
                return;
            }
            //输入的手机格式验证：正则表达式
            var reg = /^1[3456789]\d{9}$/;
            if (!reg.test(phoneno)) {
                //给出错误提示
                layer.msg("该手机号格式错误");
                return;
            }
            //验证手机号码是否已经注册：使用ajax实现
            $.ajax({
                url: "${pageContext.request.contextPath}/user",//需要使用绝对路径
                type: "post",
                data: {
                    "key": "checkPhone",
                    "phoneno": phoneno
                },
                dataType: "json",
                success: function (obj) {
                    if (obj == "ok") {
                        layer.msg("该手机号没有可以注册");
                    } else {
                        layer.msg("该手机号已经存在不能注册");
                    }
                }
            });
        });
        //监听提交
        form.on('submit(saveBtn)', function (data) {
            //在页面上用弹框显示要操作的数据
            var index = layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            }, function () {
                // 关闭弹出层
                layer.close(index);
                //获取到页面的相关信息
                var iframeIndex = parent.layer.getFrameIndex(window.name);
                //操作完成后父刷新页面
                parent.layer.close(iframeIndex);
            });
            if (data.field.loginid == '') {
                layer.msg('用户id不能为空');
                return false;
            }
            if (data.field.password == '') {
                layer.msg('用户密码不能为空');
                return false;
            }
            ;
            if (data.field.phoneno == '') {
                layer.msg('用户电话不能为空');
                return false;
            }
            ;
            if (data.field.username == '') {
                layer.msg('用户昵称不能为空');
                return false;
            }
            ;
            if (data.field.email == '') {
                layer.msg('用户邮箱不能为空');
                return false;
            }
            ;
            if (data.field.signs == '') {
                layer.msg('用户个签不能为空');
                return false;
            };
            //进行异步数据的添加操作
                  $.ajax({
                      url:'/user?key=insertAdmin',
                      type:'POST',
                      dataType:'json',
                      data:{
                          loginid:data.field.loginid,
                          password:data.field.password,
                          phoneno:data.field.phoneno,
                          username:data.field.username,
                          usersex:data.field.usersex,
                          email:data.field.email,
                          signs:data.field.signs,
                          role:data.field.role
                      },
                      success:function (msg) {
                          var returnCode=msg.returnCode;
                          if(returnCode==200){
                              layer.closeAll('loading');
                              layer.load(2);
                              layer.msg("添加成功", {icon: 6});
                              setTimeout(function(){
                                  window.parent.location.reload();//修改成功后刷新父界面
                              }, 1000);
                              //加载层-风格

                          }else{
                              layer.msg("已经存在该用户,不支持重复添加", {icon: 5});
                          }
                      }
                  });
            return false;
        });

    });
</script>
</body>
</html>