package servlet.Forum;

import bean.Blog;
import utils.BlogRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "ListMyServlet",urlPatterns = {"/my/*"})
public class ListMyServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String writer=null;
        Cookie[] cookies=request.getCookies();
        for(int i=0;i<cookies.length;i++){
            if(cookies[i].getName().equals("ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE")){
                writer=cookies[i].getValue();
                break;
            }
        }
        List<Blog> bl=null;
        String regEx = "^/(.+)";
        String str=request.getPathInfo();
        Pattern pattern= Pattern.compile(regEx);
        Matcher matcher=pattern.matcher(str);
        if(matcher.find()){
            if(!writer.equals(matcher.group(1))){
                response.sendRedirect("/Home/Forum/errorpage.jsp");
                return;
            }
            bl= BlogRepositoryImpl.listBlogByWriter(matcher.group(1));
            if(bl.size()==0){
                response.getWriter().write("<h1>抱歉，您还未发布任何帖子</h1>");
                return;
            }
        }
        response.getWriter().write("帖子列表：");
        for(int i=0;i<bl.size();i++){
            response.getWriter().write("<p><a href=\""+"/archive/"+bl.get(i).getBlogTitle()+"_1"+"\">"+bl.get(i).getBlogTitle()+"</a>");
            response.getWriter().write("&nbsp;<a href=\""+"/edit/"+bl.get(i).getBlogTitle()+"\">修改</a>");
            response.getWriter().write("&nbsp;<a href=\""+"/delete/"+bl.get(i).getBlogTitle()+"\">删除</a></p><hr/>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        processRequest(request, response);
    }
}
