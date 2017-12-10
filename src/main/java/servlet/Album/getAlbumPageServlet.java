package servlet.Album;

import bean.Album;
import bean.UserInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import conn.ConnectionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import utils.DatabaseTest;
import utils.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static utils.DatabaseTest.*;
import static utils.UIDGenerator.getUID;

/**
 * Created by Ting on 2017/9/24.
 */
@WebServlet(name = "getAlbumPageServlet", urlPatterns = {"/test/getAlbumPage"})

public class getAlbumPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
    public static class union{
        String srcs;
        String documents;
        String details;

        public union(String srcs, String title,String details) {
            this.srcs = srcs;
            this.documents = title;
            this.details=details;
        }
    }
    private class message{
        int pageNum;
        LinkedList<union> srcs;
        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        message(){
            srcs=new LinkedList<union>();
        }
        message(int pageNum,LinkedList<union> srcs){
            this.pageNum=pageNum;
            this.srcs=srcs;
        }
        void add(union src){
            srcs.add(src);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errorString;
        message ms=null;
        //每次返回8张图片的src
        int range=8;
        HttpSession session = req.getSession();
        UserInfo user = (UserInfo) session.getAttribute("user");
        try {
            Connection conn = ConnectionUtils.getConnection();
            int pageNum = parseInt(req.getParameter("pageNum"));
            System.out.println("get the request for page "+pageNum);
            ms = new message(getAlbumPageNum(conn,user.getUID(),range),getSrcByAlbumPageNum(conn,user.getUID(),pageNum,range));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        System.out.println(gson.toJson(ms));
        resp.getWriter().print(gson.toJson(ms));
    }
}
