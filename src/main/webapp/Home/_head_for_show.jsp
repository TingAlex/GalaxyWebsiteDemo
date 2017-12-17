<%--
  Created by IntelliJ IDEA.
  User: Ting
  Date: 2017/8/30
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="_header.jsp"></jsp:include>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Galaxy</a>
            <button class="navbar-toggle" type="button" data-toggle="collapse"
                    data-target="#head_hide">
                <span class="sr-only">Toggle Navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse navbar-responsive-collapse" id="head_hide">
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group"><input type="text" class="form-control" placeholder="Search"></div>
                <button type="submit" class="btn btn-default">Google</button>
            </form>
            <ul class="nav navbar-nav">
                <li><a href="/test/goForum">论坛</a></li>
                <li><a href="#">市场</a></li>
            </ul>
            <%--这里改成了navbar-right--%>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle hide" data-toggle="dropdown" href="#" id="account">Account
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Forum</a></li>
                        <li><a href="#">Market</a></li>
                        <li><a href="#">Wiki</a></li>
                    </ul>
                </li>
            </ul>
            <div class="pull-right">
                <button id="signin" data-toggle="modal" data-target="#SignInModel" class="btn navbar-btn btn-primary">
                    Sign in
                </button>
                <%--target="_blank"--%>
                <a class="hide" href="/test/userpage" target="_blank" id="username">${sessionScope.user.name}</a>&nbsp;
                <button id="signup" data-toggle="modal" data-target="#SignUpModel" class="btn navbar-btn btn-primary">
                    Sign up
                </button>
                <a id="quit" class="btn navbar-btn btn-primary fade" href="/test/quit">
                    Quit
                </a>
            </div>
        </div>
    </div>
</nav>
<script>
    //尝试删除原来登陆注册的button元素，修改为用户名和退出的a标签,在登陆或者注册成功后触发
    function transTologin(username) {
        $("#signin").hide();
        $("#signup").hide();
        $("#username").text(username);
        $("#username").removeClass("hide");
        $("#quit").removeClass("fade");
    }
    //把登陆注册的按钮再改回来，退出按钮触发
    function undologin() {
        $("#username").addClass("hide");
        $("#quit").addClass("hide");
        $("#signin").show();
        $("#signup").show();
    }
</script>