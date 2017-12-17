<%@ taglib prefix="ckeditor" uri="http://ckeditor.com" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="_header_for_main.jsp"></jsp:include>
<html>
<head>
    <title>天文论坛|${blo.getBlogTitle()}</title>
    <script>
        var click=0;
    </script>
</head>
<body>
<div class="row" style="clear: both;margin-left: 20px">
    <div class="span9">
        <div class="blog_block" >
            <h2 style="background-color: #1d75b3;color: white"><span style="color: black">【标题】</span>${blo.getBlogTitle()}</h2>
                    ${blo.getContent()}
        </div>
    </div>
    <hr size="2px" color="#000000"/>
    <c:forEach begin="${beginpage}" end="${endpage}" items="${lis}" var="com" varStatus="vs">
        <p style="color: #0000cc"><span style="color: #0000cc">用户：${com} </span></p><button type="button" style="margin-left: 20px" value="${vs.index}" class="shanchu">删除</button>
        <button class="huifu" style="margin-left:20px" value="${com}">回复此楼</button></p><hr style="color: #2e383c">
    </c:forEach>
</div>
<hr/>
<div style="align-content: center">
    <span><a href="/archive/${blo.getBlogTitle()}_${prevpage}">上一页</a></span><span><a href="/archive/${blo.getBlogTitle()}_${lastpage}">下一页</a></span>
</div>
<form id="form" action="/archive/${blo.getBlogTitle()}_${pag}" method="post">
    <p><textarea  id="ckeditor"  name="comment"></textarea></p>
    <div  style="text-align: center">
        <input type="submit" value="我要评论">
    </div>
</form>
<ckeditor:replace basePath="/ckeditor/" replace="ckeditor"></ckeditor:replace>
<script>
    $('.shanchu').click(function(){
        var str=$(this).val();
        var title=${blo.getBlogTitle()};
        var page=${pag};
        $.ajax({
            url:"/deleteComm",
            data:{"index":str,"title":title,"page":page},
            type:"get",
            success:function (data) {
                location.reload();
            }
        });
    })
</script>
<script>
    $('.huifu').click(function () {
            click=click+1;
           if(click%2==1){
               var str=$(this).val();
               $(this).addClass("green");
               var writer=str.split("|")[0];
               var comment=$.trim(str.split("|")[1]);
               var scomment="<fieldset><legend>引用"+writer+"的回复</legend>"+comment+" </fieldset>";
               CKEDITOR.instances.ckeditor.insertHtml(scomment);
               $(".huifu").attr("disabled",true);
               $(this).attr("disabled",false);
           }
            else{
               CKEDITOR.instances.ckeditor.setData("");
               $(this).removeClass("green");
               $(".huifu").attr("disabled",false);
           }
        }
    )
</script>
</body>
</html>
