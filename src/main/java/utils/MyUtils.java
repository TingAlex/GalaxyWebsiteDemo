package utils;

import bean.UserInfo;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

/**
 * Created by Administrator on 11/18/2016.
 */
public class MyUtils {
    public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";

//    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";


    // Store Connection in request attribute.
    // (Information stored only exist during requests)
    public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_NAME_CONNECTION, conn);
    }

    // Get the Connection object has been stored in one attribute of the request.
    public static Connection getStoredConnection(ServletRequest request) {
        Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
        return conn;
    }

    // Store user info in Session.
    public static void storeLoginedUser(HttpSession session, UserInfo loginedUser) {

        System.out.println("store the user in session");
        // On the JSP can access ${loginedUser}
        session.setAttribute("user", loginedUser);
    }

    // Get the user information stored in the session.
    public static UserInfo getLoginedUser(HttpSession session) {
        return (UserInfo) session.getAttribute("user");
    }

    public static void quitLoginedUser(HttpServletRequest request) {

        System.out.println("delete user in session");
        HttpSession session = request.getSession();
        session.removeAttribute("user");
    }

    // Store info in Cookie
    public static void storeUserCookie(HttpServletResponse response, UserInfo user) {
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie("NickName", user.getNickName());
//        Cookie password = new Cookie("Password", user.getPassword());
        cookieUserName.setMaxAge(24 * 60 * 60);
//        password.setMaxAge(24*60*60);
        cookieUserName.setPath("/");
//        password.setMaxAge(24*60*60);
        response.addCookie(cookieUserName);
//        response.addCookie(password);
    }

    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("NickName".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void getAllInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + " : " + cookie.getValue());
            }
        }else{
            System.out.println("cookie not exists");
        }
    }
    public static void deleteAllInCookie(HttpServletRequest request,HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                Cookie cook = new Cookie(cookie.getName(), null);
                cook.setMaxAge(0);
//        password.setMaxAge(0);
                response.addCookie(cook);
            }
        }
        System.out.println("cookie clear up done");
    }

    // Delete cookie.
    public static void deleteUserCookie(HttpServletResponse response) {
        System.out.println("deleteUserCookie");
        Cookie cookieUserName = new Cookie("NickName", null);
//        Cookie password = new Cookie("Password", null);
        // 0 seconds (Expires immediately)
        cookieUserName.setMaxAge(0);
//        password.setMaxAge(0);
        response.addCookie(cookieUserName);
//        response.addCookie(password);
    }
}
