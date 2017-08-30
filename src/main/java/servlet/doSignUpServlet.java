package servlet;

import bean.UserInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import conn.ConnectionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static utils.DatabaseTest.createUser;

/**
 * Created by Ting on 2017/8/26.
 */
@WebServlet(name = "doSignUpServlet", urlPatterns = {"/test/signup"})
public class doSignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserInfo user=new UserInfo();
        user.setName(req.getParameter("realname"));
        user.setGender(req.getParameter("gender"));
        user.setSchool(req.getParameter("school"));
        user.setSchoolYears(req.getParameter("schoolyears"));
        user.setTEL(req.getParameter("tel"));
        user.setNickName(req.getParameter("nickname"));
        user.setPassword(req.getParameter("password"));
        user.setExperience(0L);
        user.setEmail(req.getParameter("email"));
        user.setSign("");
        user.setUID();
        String errorString="";
        try {
            Connection conn= ConnectionUtils.getConnection();
            createUser(conn, user);
            GsonBuilder gsonBuilder=new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson=gsonBuilder.create();
            resp.getWriter().print(gson.toJson(user));
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            errorString = e.getMessage();
        }catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
