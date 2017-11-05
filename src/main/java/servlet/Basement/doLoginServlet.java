package servlet.Basement;

//import bean.TestPart.diaosi;
import bean.UserInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static utils.DatabaseTest.getUserByNickNameOrEmail;

/**
 * Created by Ting on 2017/8/22.
 */
@WebServlet(name = "doLoginServlet", urlPatterns = {"/test/login"})
public class doLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String Name = req.getParameter("user_id");
        String Pass = req.getParameter("pass");
        String rememberMeStr = req.getParameter("rememberMe");
        boolean remember= "Y".equals(rememberMeStr);

//        String form=req.getParameter("form_data");
        System.out.println(Name + " " + Pass);
        String errorString;
        try {
            Connection conn = MyUtils.getStoredConnection(req);
            UserInfo userA = getUserByNickNameOrEmail(conn, Name, Pass);
            if (userA != null) {
                System.out.print(userA.getName());
                //设置session
                MyUtils.storeLoginedUser(req.getSession(),userA);
                // If user checked "Remember me".
                if(remember)  {
                    MyUtils.storeUserCookie(resp,userA);
                }
                // Else delete cookie.
                else  {
                    MyUtils.deleteUserCookie(resp);
                }
                //把用户信息输出到home.jsp，留待之后实现动态查看自己的缩略信息功能
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.setPrettyPrinting();
                Gson gson = gsonBuilder.create();
                resp.getWriter().print(gson.toJson(userA));
            } else{
                UserInfo newUser=new UserInfo();
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.setPrettyPrinting();
                Gson gson = gsonBuilder.create();
                resp.getWriter().print(gson.toJson(newUser));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
