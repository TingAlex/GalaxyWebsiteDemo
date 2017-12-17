/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.Forum;


import bean.Blog;
import utils.BlogRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet(name = "ListCategoryServlet", urlPatterns = {"/category/*"})
public class ListCategoryServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Blog> bl=null;
        String regEx = "^/(.+)";
        String str=request.getPathInfo();
        Pattern pattern= Pattern.compile(regEx);
        Matcher matcher=pattern.matcher(str);
        if(matcher.find()){
            bl= BlogRepositoryImpl.listBlogByCategory(matcher.group(1));
            if(bl.size()==0){
                response.sendRedirect("/Home/Forum/errorpage.jsp");
                return;
            }
        }
        response.getWriter().write("帖子列表：");
        for(int i=0;i<bl.size();i++){
            response.getWriter().write("<p><a href=\""+"/archive/"+bl.get(i).getBlogTitle()+"_1"+"\">"+bl.get(i).getBlogTitle()+"</a></p><hr/>");
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
