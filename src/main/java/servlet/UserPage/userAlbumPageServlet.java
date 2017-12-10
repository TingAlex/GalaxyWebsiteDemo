package servlet.UserPage;

import bean.Album;
import bean.UserInfo;
import conn.ConnectionUtils;
import utils.DatabaseTest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Ting on 2017/8/30.
 */
@WebServlet(name = "userAlbumPageServlet",urlPatterns = {"/test/userAlbum"})
public class userAlbumPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setCharacterEncoding("UTF-8");
        HttpSession session=req.getSession();
        UserInfo user=(UserInfo)session.getAttribute("user");
        //在地址栏里返回参数：包括20个图片的UID和总计图片除以20的数目，之后让网页前端自己构建出src，从而自行访问returnImg来取得照片，
        //那么如何翻页呢？我们返回了页数pageNum在地址栏里，所以可以在页面下方通过js生成pageNum个小按钮<span>,并为每个按钮动态添加value，
        //这样点击按钮，我们就返回这个value值，用ajax的方式发送给/test/album/getAlbumPage，这个servlet收到value后就会计算出那一页图片的起始位置
        //然后在数据库中读取出来，然后把新的20个参数直接用js赋给哪些图片框的src
        req.getRequestDispatcher("/Home/UserPage/Album.jsp").forward(req,resp);
//        UserInfo user=(UserInfo) req.getSession().getAttribute("user");
//        GsonBuilder gsonBuilder=new GsonBuilder();
//        gsonBuilder.setPrettyPrinting();
//        Gson gson=gsonBuilder.create();
//        resp.getWriter().print(gson.toJson(user));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
