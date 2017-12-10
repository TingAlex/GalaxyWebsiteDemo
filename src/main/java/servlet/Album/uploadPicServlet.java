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
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static utils.DatabaseTest.*;
import static utils.UIDGenerator.getUID;

/**
 * Created by Ting on 2017/9/24.
 */
@WebServlet(name = "uploadPicServlet", urlPatterns = {"/test/uploadPic"})

public class uploadPicServlet extends HttpServlet {
    private class message {
        private String address;
        public String getAddress() {
            return address;
        }
        public void setAddress(String address) {
            this.address = address;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        UserInfo user = MyUtils.getLoginedUser(req.getSession());
        String uploadPath = DatabaseTest.ResourcePath + user.getUID();
        System.out.println("uploading " + uploadPath);
        try {
            List<FileItem> formItems = upload.parseRequest(req);
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        System.out.println(fileName);
                        int index = fileName.lastIndexOf('.');
                        String after = fileName.substring(index);
                        System.out.println(after);
                        String UID = getUID();
                        String errorString=null;
                        try {
                            Date date=new Date();
                            Connection conn = ConnectionUtils.getConnection();
                            Album album = new Album(UID,user.getUID(),after,date,"",0L,"");
                            addPics(conn,album);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                            errorString = e.getMessage();
                        } catch (SQLException e) {
                            e.printStackTrace();
                            errorString = e.getMessage();
                        }
                        String filePath = uploadPath + File.separator + UID + after;
                        File storeFile = new File(filePath);
                        System.out.println(filePath);
                        item.write(storeFile);
                        message ms = new message();
                        ms.setAddress(UID+after);
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        gsonBuilder.setPrettyPrinting();
                        Gson gson = gsonBuilder.create();
                        System.out.println(gson.toJson(ms));
                        user.setHeadUID(UID);
                        resp.getWriter().print(gson.toJson(ms));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("uploadMessage error: " + e.getMessage());
        }
    }
}
