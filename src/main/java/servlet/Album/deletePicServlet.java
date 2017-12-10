package servlet.Album;

import bean.Album;
import bean.UserInfo;
import conn.ConnectionUtils;
import utils.DatabaseTest;
import utils.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import static utils.DatabaseTest.*;

/**
 * Created by Ting on 2017/9/24.
 */
@WebServlet(name = "deletePicServlet",urlPatterns = {"/test/deletePic"})
public class deletePicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String addr = req.getParameter("pic");
        int index = addr.lastIndexOf('.');
        String UID = addr.substring(0,index);
        String errorString="no error";
        try {
            Connection conn = ConnectionUtils.getConnection();
            deletePicFileByUID(conn,UID);
            deletePicRecordByUID(conn,UID);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
    }
}
