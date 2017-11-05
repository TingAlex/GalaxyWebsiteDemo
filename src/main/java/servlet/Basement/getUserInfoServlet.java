package servlet.Basement;

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

/**
 * Created by Ting on 2017/9/21.
 */
@WebServlet(name = "getUserInfoServlet",urlPatterns = {"/test/getUserInfo"})
public class getUserInfoServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserInfo user=MyUtils.getLoginedUser(req.getSession());
        if(user!=null){
            //把用户信息输出到home.jsp，留待之后实现动态查看自己的缩略信息功能
            GsonBuilder gsonBuilder=new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson=gsonBuilder.create();
            resp.getWriter().print(gson.toJson(user));
        }
        else{
            user=new UserInfo();
            GsonBuilder gsonBuilder=new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson=gsonBuilder.create();
            resp.getWriter().print(gson.toJson(user));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
