<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
  <title></title>
  <link rel="stylesheet" href="layui/css/layui.css"  media="all">
</head>
<body>
<div class="layui-container" style="width: 620px;height: 600px;margin-top: 0px;padding-top: 60px;">
  <div style="margin-left: 160px; width: 500px;">
    <a href="index.jsp">首页</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="/reader?method=exportBorrowed">导出数据</a>&nbsp;&nbsp;|&nbsp;&nbsp;欢迎回来！<a href="reader_borrowed.jsp">${reader.name}</a><a href="/account?method=logout">&nbsp;&nbsp;&nbsp;&nbsp;<button class="layui-btn layui-btn-warm layui-btn-radius">注销</button></a>
  </div>
  <table class="layui-hide" id="test" lay-filter="test"></table>
</div>
<script src="layui/layui.js" charset="utf-8"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test'
            ,url:'/reader?method=getBorrowed'
            ,title: '图书列表'
            ,cols: [[
                {field:'id', width:50, title: 'ID', sort: true}
                ,{field:'bookName', width:110, title: '图书'}
                ,{field:'readerName', width:60, title: '读者'}
                ,{field:'borrowTime', width:130, title: '借书时间'}
                ,{field:'returnTime',width:130,  title: '还书时间'}
                ,{field:'state',width:140,  title: '状态'}
            ]]
            ,page: true
        });
    });
</script>

</body>
</html>
