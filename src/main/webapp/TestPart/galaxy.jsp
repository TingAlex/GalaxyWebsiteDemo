<%--
  Created by IntelliJ IDEA.
  User: Ting
  Date: 2017/8/9
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="main" style="height:700px"></div>
<!-- ECharts单文件引入 -->
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
<script src="jquery.min.js"></script>

<script type="text/javascript">
    // 路径配置
    require.config({
        paths: {
            echarts: 'http://echarts.baidu.com/build/dist'
        }
    });

    // 使用
    require(
            [
                'echarts',
                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main'));

                var option = {
                    tooltip: {
                        show: true
                    },
                    legend: {
                        data:['销量']
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            "name":"销量",
                            "type":"bar",
                            "data":[]
                        }
                    ]
                };
                $.get('galaxyDemo').done(function (data) {
                    myChart.setOption({
                        series:[{
                            data:data
                        }]
                    });
                    myChart.setOption(option);
                });
                // 为echarts对象加载数据
                myChart.setOption(option);
            }
    );
</script>
</body>
</html>
