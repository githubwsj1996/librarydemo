<%--
  Created by IntelliJ IDEA.
  User: southwind
  Date: 2020/5/12
  Time: 16:26 PM
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

<div class="layui-container" style="margin-top: 50px;">

    <table class="layui-hide" id="test" lay-filter="test"></table>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
    <script>
        layui.use('table', function(){
            var table = layui.table;

            table.render({
                elem: '#test'
                ,url:'reader?method=findAll'
                ,title: '读者列表'
                ,cols: [[
                    {field:'id', width:60, title: 'ID', sort: true}
                    ,{field:'username', width:100, title: '用户名'}
                    ,{field:'name', width:100, title: '真实姓名'}
                    ,{field:'tel', width:120, title: '联系电话'}
                    ,{field:'cardid',width:150,  title: '证件编号'}
                    ,{field:'gender',width:150,  title: '性别'}
                    ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:130}
                ]]
                ,page: true
            });

            //监听行工具事件
            table.on('tool(test)', function(obj){
                var data = obj.data;
                if(obj.event === 'del'){
                    layer.confirm('确认要删除吗？', function(index){
                        window.location.href="reader?method=deleteReader&id="+data.id;
                        layer.close(index);
                    });
                } else if(obj.event === 'edit'){
                    window.location.href="reader?method=findReaderById&id="+data.id;
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
