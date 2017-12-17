package servlet.Forum;

import bean.Blog;
import bean.UserInfo;
import utils.BlogRepositoryImpl;
import utils.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "submitinsert", urlPatterns = {"/test/addpost"})
public class newPost extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String category = request.getParameter("category");
        String tag = request.getParameter("tag");
        String content = request.getParameter("content");
        String writer = null;
        UserInfo user = MyUtils.getLoginedUser(request.getSession());
        if (user == null) {
            System.out.println("can't find logined user");
        } else {
            System.out.println("find logined user");
            writer = user.getUID();
            System.out.println("writer uid is : " + writer);
        }
        Blog blog = new Blog();
        blog.setBlogTitle(title);
        blog.setCategory(category);
        blog.setContent(content);
        blog.setTag(tag);
        blog.setPostDate(new Date());
        blog.setWriter(writer);
        BlogRepositoryImpl.addBlog(blog);
        response.sendRedirect("/Home/Forum/Forum.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
