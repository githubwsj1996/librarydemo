<%--
  Created by IntelliJ IDEA.
  User: sj
  Date: 2020/5/10
  Time: 18:28
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
        <form class="layui-form" action="/bookAdmin?method=bookEdit" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label">图书编号：</label>
                <div class="layui-inline">
                    <input type="text" name="id" lay-verify="required" autocomplete="off" placeholder="" class="layui-input" readonly value="${requestScope.book.id}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">图书名称：</label>
                <div class="layui-inline">
                    <input type="text" name="name" lay-verify="required" autocomplete="off" placeholder="请输入图书名称" class="layui-input" value="${requestScope.book.name}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">图书作者：</label>
                <div class="layui-inline">
                    <input type="text" name="author" lay-verify="required" placeholder="请输入图书作者" autocomplete="off" class="layui-input" value="${requestScope.book.author}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">出版社：</label>
                <div class="layui-inline">
                    <input type="text" name="publish" lay-verify="required" placeholder="请输入出版社" autocomplete="off" class="layui-input" value="${requestScope.book.publish}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">总页数：</label>
                <div class="layui-inline">
                    <input type="text" name="pages" lay-verify="pages" placeholder="请输入总页数" autocomplete="off" class="layui-input" value="${requestScope.book.pages}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">价格：</label>
                <div class="layui-inline">
                    <input type="text" name="price" lay-verify="price" placeholder="请输入价格" autocomplete="off" class="layui-input" value="${requestScope.book.price}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">图书分类：</label>
                <div class="layui-input-inline">
                    <select name="bookCaseId">
                        <option value="">请选择分类</option>
                        <c:forEach items="${list}" var="bookCase">
                            <c:if test="${requestScope.book.bookCaseId==bookCase.id}">
                                <option value="${bookCase.id}" selected >${bookCase.name}</option>
                            </c:if>
                            <c:if test="${requestScope.book.bookCaseId!=bookCase.id}">
                                <option value="${bookCase.id}">${bookCase.name}</option>
                            </c:if>
                        </c:forEach>
                    </select>
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
            pages: [/^[0-9]*$/, "请输入正确的页数"],
            price: [/(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/, "请输入正确的价格"]
        });

    });


</script>
</body>
</html>

