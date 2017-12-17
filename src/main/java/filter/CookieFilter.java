package filter;

import bean.UserInfo;
import conn.ConnectionUtils;
import utils.MyUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static utils.DatabaseTest.getUserByNickName;
import static utils.MyUtils.getAllInCookie;

/**
 * Created by Administrator on 11/19/2016.
 */
@WebFilter(filterName = "cookieFilter", urlPatterns = {"/*"})
public class CookieFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("Cookie Filter");
        HttpSession session = req.getSession();
//        getAllInCookie(req);
        UserInfo userInSession = MyUtils.getLoginedUser(session);
        if (userInSession == null) {
            System.out.println("no user in session found");
            String userName = MyUtils.getUserNameInCookie(req);
            if (userName == null) {
                System.out.println("user not in cookie");
                chain.doFilter(request, response);
                return;
            } else {
                System.out.println("user is in cookie and be add to session: " + userName);
                try {
//                    ConnectionUtils.getConnection()
                    Connection conn = ConnectionUtils.getConnection();
                    UserInfo user = getUserByNickName(conn, userName);
                    MyUtils.storeLoginedUser(session, user);
                    chain.doFilter(request, response);
                    return;
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("found user in session" + userInSession.getName());
            chain.doFilter(request, response);
            return;
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
