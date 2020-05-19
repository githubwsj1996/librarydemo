<%--
  Created by IntelliJ IDEA.
  User: sj
  Date: 2020/5/12
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/echarts.common.min.js"></script>
</head>
<body>

<div id="main" style="width: 800px;height:400px;"></div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    $.ajax({
        url:'/bookAdmin?method=getBarData2',
        type:'POST',
        dataType:'json',
        success:function (data) {

            // 指定图表的配置项和数据
            var option = {

                title: {
                    text: '图书借阅数据统计'
                },
                tooltip: {},
                legend: {
                    data:['借阅量']
                },
                xAxis: {
                    data: data.name
                },
                yAxis: {},
                series: [{
                    name: '借阅量',
                    type: 'bar',
                    data: data.count
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });



</script>
</body>
</html>
