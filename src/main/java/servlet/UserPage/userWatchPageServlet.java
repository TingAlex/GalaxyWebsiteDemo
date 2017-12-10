package servlet.UserPage;

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
@WebServlet(name = "userWatchPageServlet",urlPatterns = {"/test/userWatch"})
public class userWatchPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setCharacterEncoding("UTF-8");
        HttpSession session=req.getSession();
        UserInfo user=(UserInfo)session.getAttribute("user");


        req.getRequestDispatcher("/Home/UserPage/Self_intro.jsp").forward(req,resp);
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
