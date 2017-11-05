package servlet.Basement;

import utils.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ting on 2017/9/21.
 */
@WebServlet(name = "quitServlet",urlPatterns = {"/test/quit"})
public class quitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //这里要清理掉cookie和session中的全部信息
        MyUtils.deleteUserCookie(resp);
        MyUtils.quitLoginedUser(req);
        req.getRequestDispatcher("/Home/Home.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
