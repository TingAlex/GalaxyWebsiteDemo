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
@WebServlet(name = "userListenerPageServlet",urlPatterns = {"/test/userListener"})
public class userListenerPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setCharacterEncoding("UTF-8");
        HttpSession session=req.getSession();
        UserInfo user=(UserInfo)session.getAttribute("user");
        String headpic=user.getHeadUID();
        Album full_headpic=null;;
        if (headpic==null||headpic.equals("")){
            req.setAttribute("headpic","/Home/default_user_headpic.jpg");
        }else {
            String path=user.getUID();
            String errorString;
            try {
                Connection conn= ConnectionUtils.getConnection();
                full_headpic=DatabaseTest.getPicByUID(conn,headpic);
            }catch (ClassNotFoundException e){
                e.printStackTrace();
                errorString = e.getMessage();
            }catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
            String full_headpic_path=DatabaseTest.ResourcePath+path+"\\"+full_headpic;
            req.setAttribute("headpic",full_headpic_path);
        }

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
