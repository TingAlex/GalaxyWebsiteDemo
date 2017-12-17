package servlet.Forum;

import bean.Blog;
import bean.UserInfo;
import utils.BlogRepositoryImpl;
import utils.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "EditBlogServlet", urlPatterns = {"/test/submitinsert"})
public class EditBlogServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Blog blog = null;
        String regEx = "/(.+)";
        String str = request.getPathInfo();
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            blog = BlogRepositoryImpl.getBlogByTitle(matcher.group(1));
            if (blog.getBlogTitle() == null) {
                response.sendRedirect("/Home/Forum/errorpage.jsp");
                return;
            }
        }
        request.setAttribute("blo", blog);
        request.getRequestDispatcher("/Home/Forum/edit.jsp").forward(request, response);
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
        String oldhead = request.getParameter("oldhead");
        String title = request.getParameter("title");
        String category = request.getParameter("category");
        String tag = request.getParameter("tag");
        String content = request.getParameter("content");
        String writer = null;
        UserInfo user = MyUtils.getLoginedUser(request.getSession());
        if(user==null){
            System.out.println("can't find logined user");
        }else{
            System.out.println("find logined user");
            writer = user.getUID();
            System.out.println("writer uid is : "+writer);
        }
        Blog blog = new Blog();
        blog.setBlogTitle(title);
        blog.setCategory(category);
        blog.setContent(content);
        blog.setTag(tag);
        blog.setPostDate(new Date());
        blog.setWriter(writer);
        System.out.println("ready to write into");
        BlogRepositoryImpl.updateBlog(blog, oldhead);
        response.sendRedirect("/test/goForum");
    }
}
