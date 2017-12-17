package servlet.Basement;

import bean.UserInfo;
import utils.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.MyUtils.getAllInCookie;

/**
 * Created by Ting on 2017/11/6.
 */
@WebServlet(name = "homeServlet",urlPatterns = {"/test/Home"})
public class homeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("the first page you may see");

        UserInfo user= MyUtils.getLoginedUser(req.getSession());
        if(user==null){
            System.out.println("HomePage");
            req.getRequestDispatcher("/Home/Home.jsp").forward(req,resp);
        }
        else{
            System.out.println("/loginedHomePage");
            req.getRequestDispatcher("/Home/loginedHome.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
