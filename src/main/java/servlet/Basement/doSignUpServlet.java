package servlet.Basement;

import bean.UserInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.org.apache.bcel.internal.ExceptionConstants;
import conn.ConnectionUtils;
import mail.SendMail;
import utils.MyUtils;

import javax.mail.SendFailedException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static utils.DatabaseTest.createUser;
import static utils.MyUtils.storeLoginedUser;
import static utils.UIDGenerator.getUID;

/**
 * Created by Ting on 2017/8/26.
 */
@WebServlet(name = "doSignUpServlet", urlPatterns = {"/test/signup"})
public class doSignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        UserInfo user=new UserInfo();
        user.setName(req.getParameter("realname"));
        user.setGender(req.getParameter("gender"));
        user.setSchool(req.getParameter("school"));
        user.setSchoolYears(req.getParameter("schoolyears"));
        user.setTel(req.getParameter("tel"));
        user.setNickName(req.getParameter("nickname"));
        user.setPassword(req.getParameter("password"));
        user.setExperience(0L);
        user.setEmail(req.getParameter("email"));
        user.setSign("");
        user.setUID();
        user.setSchoolAdmin(false);
        user.setHeadUID(getUID());
        user.setAdmin(false);
        String errorString="";
        String rememberMeStr = req.getParameter("rememberMe");

        try {
            SendMail sm = new SendMail(user,0);//need later run for test
            new Thread(sm).start();//these two lines
            Connection conn= ConnectionUtils.getConnection();
            createUser(conn, user);
            //设置session
            storeLoginedUser(req.getSession(),user);
            System.out.println("after sign up: "+user.getHeadUID());

            // If user checked "Remember me".
            if(rememberMeStr.equals("Y"))  {
                MyUtils.storeUserCookie(resp,user);
            }
            // Else delete cookie.
            else  {
                MyUtils.deleteUserCookie(resp);
            }
            //把用户信息输出到home.jsp，留待之后实现动态查看自己的缩略信息功能
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
