<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="static" uri="staticf"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false"%>

${static:refreshall()}
<!DOCTYPE html>
<html>
<head>
    <title>论坛首页</title>
    <jsp:include page="_header.jsp"></jsp:include>
</head>
<body>
<div class="container">
        <h1 id="blog_title"><a href="${pageContext.request.contextPath}">天文论坛</a> </h1>
        <div id="loginlink">
            <p>
                <a href="/test/createNewPost">发布新帖</a>
            </p>
            <p>
                <a id="me" class="hide" href="/my/${cookie.ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE.value}">我的帖子</a>
                <a id="login" href="#">login</a>
            </p>
        </div>
    <div class="row">
        <div class="span3">
            <div class="blog_block">
                    <li><i class="icon-list-alt"></i> 标签墙</li>
                <c:forEach  var="i" items="${static:getTagSet()}">
                    <a href="${pageContext.servletContext.contextPath}/tag/${i}">${i}</a>&nbsp;
                </c:forEach>
            </div>
            <div class="blog_block">
                <ul class="nav nav-list">
                    <li>
                        <i class="icon-list-alt"></i>
                        分类
                    </li>
                    <c:forEach  var="i" items="${static:getCategorySet()}">
                        <a href="${pageContext.servletContext.contextPath}/category/${i}">${i}</a>
                    </c:forEach>
                </ul>
            </div>
            <div class="blog_block">
                <ul  class="nav nav-list">
                    <li> <i class="icon-list-alt"></i>
                        时间轴
                    </li>
                    <c:forEach items="${static:getDateMap().entrySet()}" var="dateMapEntry">
                        <a href="${pageContext.servletContext.contextPath}/date/${dateMapEntry.key}">${dateMapEntry.key}</a>&nbsp;(${dateMapEntry.value})
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <hr>
    <footer>
        <p style="text-align: center;">Galaxy高校天文平台</p>
    </footer>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        if("${cookie.isLogin.value}"=="Y"){
            $("#login").hide();
            $("#me").removeClass("hide");
        }
    })
</script>
</body>
</html>