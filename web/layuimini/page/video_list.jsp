<%--
  Created by IntelliJ IDEA.
  User: 19041
  Date: 2021/8/11
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>MV信息</title>
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

        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">MV名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="viName" id="viName" autocomplete="off"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">歌手名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="singer" id="singer" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <button type="button" class="layui-btn layui-btn-primary" lay-submit
                                    lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
<%--                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 添加</button>--%>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 批量删除
                </button>
            </div>
        </script>

        <table class="layui-hide layui-table-cell " id="currentTableId" lay-filter="currentTableFilter"></table>

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
            url: '/video?key=queryVideoAll',
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],

            cols: [[
                {type: "checkbox", width: 50},
                {field: 'vid', width: 120, title: 'ID', sort: true},
                {field: 'viName', width: 120, title: 'mv名称'},
                {
                    field: 'vphoto', width: 120, title: 'mv图片',
                    templet: function (obj) {
                        //imgs是虚拟服务器（挂载服务器）需要部署在Tomcat服务器中
                        return '<img src="/MV/' + obj.vphoto + '" width = "50px" height = "50px" > ';
                    }
                },
                {field: 'singer', width: 120, title: '歌手'},
                {field: 'viurl', width: 120, title: 'MV地址'},
                {field: 'vcity', width: 140, title: '地区'},
                {field: 'vyear', width: 120, title: '年代', sort: true},
                {
                    field: 'popular', width: 120, title: '是否热门', sort: true,
                    templet: function (obj) {
                        return obj.popular === 0 ? "否" : "是";
                    }
                },
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

        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            var result = JSON.stringify(data.field);
            var singer = $('#singer');
            var viName = $('#viName');

            layer.alert('MV名: ' + data.field.viName + ' 歌手名: ' + data.field.singer, {
                title: '最终的搜索信息'
            });
            //执行搜索重载
            table.reload('currentTableId', {
                url: "/video?key=selectBySome",
                type: "post",
                page: {
                    curr: 1
                }
                , where: {
                    singer: singer.val(),
                    viName: viName.val()

                }
            }, 'data');

            return false;
        });
        $('#btn.layui-btn').on('click', function () {
            var type = $(this).data('type');
            console.log(type)
            active[type] ? active[type].call(this) : '';
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
                        ids = ids + "," + data[i].vid;
                    }
                    // console.log(ids);//,1,2,3
                    //将最开始的逗号截取
                    ids = ids.substring(1);
                    // console.log(ids);//1,2,3
                    $.ajax({
                        url: "${pageContext.request.contextPath}/video",
                        type: "post",
                        data: {
                            "key": "batchDelete",
                            "ids": ids,
                        },
                        dataType: "json",
                        success: function (object) {
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
                    title: '编辑MV',
                    type: 2,
                    shade: 0.2,
                    maxmin: true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '${pageContext.request.contextPath}/video?key=findVideoById&vid=' + data.vid
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            } else if (obj.event === 'delete') {
                layer.confirm('真的删除: ' + data.vid + "么", function (index) {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/video?key=deleteById',
                        type: 'get',
                        data: {
                            'vid': data.vid
                        },//向服务端发送删除的id
                        success: function (suc) {
                            if (suc == 200) {
                                console.log(suc)
                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                                layer.close(index);
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


