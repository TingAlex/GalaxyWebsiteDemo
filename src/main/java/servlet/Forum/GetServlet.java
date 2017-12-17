package servlet.Forum;


import bean.Blog;
import bean.UserInfo;
import utils.BlogRepositoryImpl;
import utils.MyUtils;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "PageGetServlet", urlPatterns = {"/archive/*"})
public class GetServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page=null;
        Blog blog=null;
        List<String> list=null;
        String regEx = "^/(.*)?_(.+)";
        String str=request.getPathInfo();
        Pattern pattern= Pattern.compile(regEx);
        Matcher matcher=pattern.matcher(str);
        if(matcher.find()){
            blog= BlogRepositoryImpl.getBlogByTitle(matcher.group(1));
            list= BlogRepositoryImpl.getCommentsByTitle(matcher.group(1));
            page=matcher.group(2);
            if(blog.getBlogTitle()==null){
                response.sendRedirect("/Home/Forum/errorpage.jsp");
                return;
            }
        }
       try{
           request.setAttribute("beginpage",(Integer.parseInt(page)-1)*5);
           if(Integer.parseInt(page)*5>list.size()){
               request.setAttribute("endpage",list.size());
           }
           else{
               request.setAttribute("endpage",Integer.parseInt(page)*5-1);
           }
       }catch (Exception e){
           response.sendRedirect("/Home/Forum/errorpage.jsp");
           return;
       }
       if(Integer.parseInt(page)>1){
           request.setAttribute("prevpage",Integer.parseInt(page)-1);
       }else {
           request.setAttribute("prevpage",Integer.parseInt(page));
       }
        if(Integer.parseInt(page)<list.size()/5.0){
            request.setAttribute("lastpage",Integer.parseInt(page)+1);
        }else {
            request.setAttribute("lastpage",Integer.parseInt(page));
        }
        request.setAttribute("pag",page);
        request.setAttribute("blo",blog);
        request.setAttribute("lis",list);
        request.getRequestDispatcher("/Home/Forum/main.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setDateHeader("Expires",-1); //IE游览器支持的

        //保证兼容性
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragme", "no-cache");
        processRequest(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setDateHeader("Expires",-1); //IE游览器支持的

        //保证兼容性
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragme", "no-cache");
        String comment=request.getParameter("comment");
        String page=null;
        String writer=null;
        UserInfo user = MyUtils.getLoginedUser(request.getSession());
        if (user == null) {
            System.out.println("can't find logined user");
        } else {
            System.out.println("find logined user");
            writer = user.getUID();
            System.out.println("writer uid is : " + writer);
        }
        String title=null;
        String regEx = "^/(.*)?_(.+)";
        String str=request.getPathInfo();
        Pattern pattern= Pattern.compile(regEx);
        Matcher matcher=pattern.matcher(str);
        if(matcher.find()){
            title=matcher.group(1);
            page=matcher.group(2);
        }
        BlogRepositoryImpl.addComment(writer,title,comment);
        response.sendRedirect("/archive/"+title+"_"+page);
    }

}
