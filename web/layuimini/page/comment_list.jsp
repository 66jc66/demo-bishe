<%--
  Created by IntelliJ IDEA.
  User: 19041
  Date: 2021/8/10
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>评论信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="../css/public.css" media="all">
    <style type="text/css">
        .layui-table-cell {
            height: 50px;
        }
    </style>
</head>

<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 批量删除
                </button>
            </div>
        </script>

        <table class="layui-hide layui-table-cell "  id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        </script>

    </div>
</div>
<script src="../lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;

        table.render({
            elem: '#currentTableId',
            url: '/com?key=queryComAll',
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],

            cols: [[
                {type: "checkbox", width: 50},
                {field: 'comid', width: 80, title: 'ID', sort: true},
                {field: 'uid', width: 80, title: '用户名'},
                {field: 'sid', width: 80, title: '歌曲名'},
                {field: 'comtext', width: 200, title: '评论'},
                {field: 'comtime', width: 160, title: '时间',sort: true},
                {title: '操作', minWidth: 100, toolbar: '#currentTableBar', align: "center"}
            ]],
            limits: [5, 10, 15, 20, 25, 50, 100],
            limit: 5,
            page: true,
            parseData: function (res) { //res 即为原始返回的数据
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.count, //解析数据长度
                    "data": res.result //解析数据列表
                };
            },
            skin: 'line'
        });


        /**
         * toolbar监听事件--增加，删除，修改
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'delete') {  // 监听删除操作
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
                layer.alert(JSON.stringify(data));
                if (data.length == 0) {
                    layer.alert("请选择要删除的记录");
                } else {
                    // layer.alert(JSON.stringify(data));
                    var ids = "";
                    for (var i in data) {
                        // console.log(data[i].srid);
                        //拼接uid
                        ids = ids + "," + data[i].comid;
                    }
                    // console.log(ids);//,1,2,3
                    //将最开始的逗号截取
                    ids = ids.substring(1);
                    // console.log(ids);//1,2,3
                    $.ajax({
                        url: "${pageContext.request.contextPath}/com",
                        type: "post",
                        data: {
                            "key": "batchDelete",
                            "ids": ids,
                        },
                        dataType: "json",
                        success: function (object) {
                            console.log(object);
                            if (object == "200") {
                                //已删除

                                layer.msg('删除成功！');
                            } else {

                                layer.msg('删除失败! ');
                            }
                            //删除成功后刷新本界面
                            window.location.reload();
                        }
                    });
                }
            }
        });

        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });

        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {

                var index = layer.open({
                    title: '编辑评论',
                    type: 2,
                    shade: 0.2,
                    maxmin: true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '${pageContext.request.contextPath}/com?key=findComById&comid=' + data.comid
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            } else if (obj.event === 'delete') {
                layer.confirm('真的删除: ' + data.comid + "么", function (index) {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/com?key=deleteById',
                        type: 'get',
                        data: {
                            'comid': data.comid
                        },//向服务端发送删除的id
                        success: function (suc) {
                            if (suc == 200) {
                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                layer.close(index);
                                console.log(index);
                                layer.msg("删除成功", {icon: 1});
                            } else {
                                layer.msg("删除失败", {icon: 5});
                            }
                        }
                    });
                    layer.close(index);
                });
            }
        });

    });
</script>

</body>
</html>

