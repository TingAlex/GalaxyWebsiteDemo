<%@ taglib prefix="ckeditor" uri="http://ckeditor.com" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="_header.jsp"></jsp:include>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>新建帖子</title>
</head>
<body>
<div class="container">
    <div class="blog-header">
        <h1 id="blog_title">
            <a href="${pageContext.servletContext.contextPath}">天文平台</a>
        </h1>

    </div>
    <!-- Main hero unit for a primary marketing message or call to action -->
    <!-- Example row of columns -->
    <div class="row" style="clear: both">
        <div class="span9">
            <div class="blog_block">
                <form action="/test/submitinsert" method="post">
                    <fieldset>
                        <input name="oldhead" type="hidden" value="${blo.getBlogTitle()}">
                        <p>
                            <label id="inputlabel"> 名称:</label>
                            <input type="text" name="title" value="${blo.getBlogTitle()}" readonly="readonly">
                        </p>
                        <p>
                            <label id="inputlabe2"> tag:</label>
                            <input type="text" name="tag" value="${blo.getTag()}">
                        </p>
                        <p>
                            <label>Category</label>
                            <select name="category">
                                <option>--请选择--</option>
                                <option value="观测">观测</option>
                                <option value="星象" selected="selected">星象</option>
                                <option value="热点">热点</option>
                            </select>
                        </p>
                        <p><textarea id="ckeditor" name="content">${blo.getContent()}
                        </textarea></p>
                        <div style="text-align: center">
                            <input type="submit" value="Submit" class="btn"/>
                        </div>
                    </fieldset>
                </form>
                <ckeditor:replace replace="ckeditor" basePath="/ckeditor/"/>
            </div>
        </div>
    </div>
    <hr>
    <footer>
        <p style="text-align: center;">&copy; bootstrap based design</p>
    </footer>
</div> <!-- /container -->
</body>
</html>
