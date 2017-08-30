<%--
  Created by IntelliJ IDEA.
  User: Ting
  Date: 2017/8/7
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert</title>
</head>
<body>
<script>
    function showHint(str)
    {
        var xmlhttp;
        if (str.length==0)
        {
            document.getElementById("txtHint").innerHTML="";
            return;
        }
        if (window.XMLHttpRequest)
        {
            // IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
            xmlhttp=new XMLHttpRequest();
        }
        else
        {
            // IE6, IE5 浏览器执行代码
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
                var jsonobj=JSON.parse(xmlhttp.responseText);
                document.getElementById("txtHint").innerHTML=jsonobj.school;
            }
        };
        xmlhttp.open("GET","servletDemo");
        xmlhttp.send();
    }
</script>
<h3>在输入框中尝试输入字母 a:</h3>
<form action="">
    输入姓名: <input type="text" id="txt1" onkeyup="showHint(this.value)" />
</form>
<p>提示信息: <span id="txtHint"></span></p>
</body>
</html>
