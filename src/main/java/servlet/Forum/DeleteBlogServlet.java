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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "DeleteBlogServlet",urlPatterns = {"/delete/*"})
public class DeleteBlogServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name=null;
        Cookie[] cookies=request.getCookies();
        for(int i=0;i<cookies.length;i++){
            if(cookies[i].getName().equals("ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE")){
                name=cookies[i].getValue();
                break;
            }
        }
        Blog blog=null;
        String regEx = "^/(.+)";
        String str=request.getPathInfo();
        Pattern pattern= Pattern.compile(regEx);
        Matcher matcher=pattern.matcher(str);
        if(matcher.find()){
            blog= BlogRepositoryImpl.getBlogByTitle(matcher.group(1));
            if(blog.getBlogTitle()==null){
                response.sendRedirect("/Home/Forum/errorpage.jsp");
                return;
            }
        }
        String title=blog.getBlogTitle();
        BlogRepositoryImpl.deleteBlog(blog.getBlogTitle());
        response.sendRedirect("/my/"+name);
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
