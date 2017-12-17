<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/10/28
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find Password</title>
    <jsp:include page="../_header.jsp"></jsp:include>
</head>
<body>
<form>
    <div class="form-group">
        <label for="exampleInputEmail1">Email address</label>
        <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
    </div>
    <button type="submit" class="btn btn-default" id="subbtn">Submit</button>
    <a class="btn btn-default" href="/test/returnHome">Return to homepage</a>
</form>
<script>
    $(document).ready(function(){
        $('#subbtn').click(function(){
            var email=$.trim($('#exampleInputEmail1').val());
            $.ajax({
                url:"/test/find",
                data:{"email":email},
                type:"get",
                dataType:"json",
                async:false,
                success:function (data) {
                    alert("密码已发送到您的邮箱，请注意查收！");
                },
            })
        })
    })
</script>
</body>
</html>
