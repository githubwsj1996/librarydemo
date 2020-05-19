<%--
  Created by IntelliJ IDEA.
  User: southwind
  Date: 2020/5/12
  Time: 16:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <div class="layui-container" style="width: 500px;height: 330px;padding-top: 60px;">
        <form class="layui-form" action="reader?method=addReader" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label">用户名：</label>
                <div class="layui-inline">
                    <input type="text" name="username" lay-verify="required" autocomplete="off" placeholder="请输入用户名" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码：</label>
                <div class="layui-inline">
                    <input type="password" name="password" lay-verify="password" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">真实姓名：</label>
                <div class="layui-inline">
                    <input type="text" name="name" lay-verify="name" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">联系电话：</label>
                <div class="layui-inline">
                    <input type="text" name="tel" lay-verify="tel" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">证件编号：</label>
                <div class="layui-inline">
                    <input type="text" name="cardid" lay-verify="cardid" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">性别：</label>
                <div class="layui-input-inline">
                    <input type="radio" name="gender" value="男" title="男" checked="">
                    <input type="radio" name="gender" value="女" title="女">
                </div>
            </div>
            <div class="layui-form-item">
                <button class="layui-btn" lay-submit="" lay-filter="demo2" style="margin-left: 160px;">提交</button>
            </div>
        </form>
    </div>

</div>
<script>
    layui.use(['form', 'element'], function(){
        var form = layui.form;
        var element = layui.element;

        //自定义验证规则
        form.verify({
            username: function(value){
                if(value.length == 0){
                    return "用户名不能为空";
                }
            },
            password: [/^[A-Za-z0-9]+$/,"密码必须由数字和字母组成"],
            name: [/^[\u4e00-\u9fa5]+$/,"请输入正确的姓名"],
            tel: [/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/,"请输入正确的电话"],
            cardid: [/^\d{15}|\d{}18$/,"请输入正确的证件编号"]
        });

    });


</script>
</body>
</html>
