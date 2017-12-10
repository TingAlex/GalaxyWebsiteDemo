package servlet.Basement;

import bean.UserInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import conn.ConnectionUtils;
import mail.SendMail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static utils.DatabaseTest.getUserByEmail;

@WebServlet(name = "findpasswordServlet",urlPatterns = {"/test/find"})
public class findpasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String email=request.getParameter("email");
        System.out.println(email);
        Connection conn= null;
        try {
            conn= ConnectionUtils.getConnection();
            UserInfo userInfo=getUserByEmail(conn,email);
            if(userInfo!=null){
                SendMail sm = new SendMail(userInfo,1);
                new Thread(sm).start();
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.setPrettyPrinting();
                Gson gson = gsonBuilder.create();
                response.getWriter().print(gson.toJson(userInfo));
            }else{
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.setPrettyPrinting();
                Gson gson = gsonBuilder.create();
                response.getWriter().print(gson.toJson(new UserInfo()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
