package servlet.Basement;

import bean.UserInfo;
import utils.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ting on 2017/11/6.
 */
@WebServlet(name = "homeServlet",urlPatterns = {"/test/Home"})
public class homeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("root");
        UserInfo user= MyUtils.getLoginedUser(req.getSession());
        if(user==null){
            System.out.println("HomePage");
            req.getRequestDispatcher("/Home/Home.jsp").forward(req,resp);
        }
        else{
            System.out.println("loginedHomePage");
            req.getRequestDispatcher("/test/returnHome").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
