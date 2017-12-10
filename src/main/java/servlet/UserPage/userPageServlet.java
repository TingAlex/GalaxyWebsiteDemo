package servlet.UserPage;

import bean.Album;
import bean.UserInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
@WebServlet(name = "userPageServlet", urlPatterns = {"/test/userpage"})
public class userPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        UserInfo user = (UserInfo) session.getAttribute("user");
        String headpic = user.getHeadUID();
        String after = null;
        String errorString;
        try {
            Connection conn = ConnectionUtils.getConnection();
            Album album = DatabaseTest.getPicByUID(conn, headpic);
            after = album.getType();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        String headpic_path = headpic + after;
        System.out.println("userpage: return path: "+headpic_path);
        req.setAttribute("headpic", headpic_path);

        req.getRequestDispatcher("/Home/UserPage/Self_intro.jsp").forward(req, resp);
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
