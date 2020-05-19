<%--
  Created by IntelliJ IDEA.
  User: sj
  Date: 2020/5/11
  Time: 9:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css">
    <script type="text/javascript" src="layui/layui.js"></script>
</head>
<body class="layui-layout-body">
<div class="layui-container" style="margin-top: 50px;">

    <table class="layui-hide" id="test" lay-filter="test"></table>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="agree">同意</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="disagree">拒绝</a>
    </script>
    <script>
        layui.use('table', function(){
            var table = layui.table;

            table.render({
                elem: '#test'
                ,url:'bookAdmin?method=getBorrows'
                ,title: '借书列表'
                ,cols: [[
                    {field:'id', width:50, title: 'ID', sort: true}
                    ,{field:'bookName', width:110, title: '图书'}
                    ,{field:'readerName', width:60, title: '读者'}
                    ,{field:'borrowTime', width:130, title: '借书时间'}
                    ,{field:'returnTime',width:130,  title: '还书时间'}
                    ,{field:'state',width:120,  title: '状态'}
                    ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:120}
                ]]
                ,page: true
            });

            //监听行工具事件
            table.on('tool(test)', function(obj){
                var data = obj.data;
                if(obj.event === 'agree'){
                    layer.confirm('确定通过？', function(index){
                        window.location.href="bookAdmin?method=agreeBorrow&id="+data.id;
                        layer.close(index);
                    });
                } else if(obj.event === 'disagree'){
                    layer.confirm('确定拒绝？', function(index){
                        window.location.href="bookAdmin?method=disAgreeBorrow&id="+data.id+"&bookId="+data.bookId;
                        layer.close(index);
                    });
                }
            });
        });
    </script>

</div>
<script>
    //二级菜单联动
    layui.use('element', function(){
        var element = layui.element;

    });
</script>
</body>
</html>