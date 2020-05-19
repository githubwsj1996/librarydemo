<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/layui/css/layui.css"  media="all">
</head>
<body>
<div class="layui-container" style="width: 500px;height: 330px;margin-top: 100px;border: 1px solid gray;padding-top: 60px;">
    <form class="layui-form" action="/account?method=login" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-inline">
                <input type="text" name="username" lay-verify="username" autocomplete="off" placeholder="请输入用户名" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-inline">
                <input type="password" name="password" lay-verify="password" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" pane="">
            <label class="layui-form-label">单选框</label>
            <div class="layui-input-block">
                <input type="radio" name="type" value="reader" title="读者" checked="">
                <input type="radio" name="type" value="bookadmin" title="管理员">
            </div>
        </div>
        <div class="layui-form-item">
            <button class="layui-btn" lay-submit="" lay-filter="demo2" style="margin-left: 160px;">登陆</button>
        </div>
    </form>
</div>
    <script src="/layui/layui.js" charset="utf-8"></script>
    <script>
        layui.use(['form'], function(){
            var form = layui.form;

            //自定义验证规则
            form.verify({
                username: function(value){
                    if(value.length == 0){
                        return '用户名不能为空';
                    }
                }
                ,password: [/^[A-Za-z0-9]{6,16}$/, '密码必须6到12位']
            });

        });
    </script>
</body>
</html>
