package servlet.Basement;

import bean.UserInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import conn.ConnectionUtils;
import utils.DatabaseTest;
import utils.MyUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static utils.DatabaseTest.changeUserInfo;

/**
 * Created by Ting on 2017/11/16.
 */
@WebServlet(name = "returnImgServlet", urlPatterns = {"/test/returnImg"})
public class returnImg extends HttpServlet {
    public static BufferedImage getInputStream(String addr) {
        try {
            String imgPath = addr;
            BufferedImage image = ImageIO.read(new FileInputStream(imgPath));
            return image;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println();
            System.out.println("获取图片异常:java.awt.image.BufferedImage");
            System.out.println("请检查图片路径是否正确，或者该地址是否为一个图片");
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //参数只需要图片的UID+after后缀就足够了
        BufferedImage img = new BufferedImage(300, 150, BufferedImage.TYPE_INT_RGB);
        String addr=req.getParameter("addr");
        UserInfo user = MyUtils.getLoginedUser(req.getSession());
        String uploadPath = DatabaseTest.ResourcePath + user.getUID();
        String filePath = uploadPath + File.separator + addr;
        System.out.println(filePath);
        img = getInputStream(filePath);
        if (img == null) {
            throw new RuntimeException("打印图片异常：com.controller.Business_Ctrl.getImg(String, HttpServletResponse)");
        }else {
            try {
                ImageIO.write(img, "JPEG", resp.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("打印异常:com.controller.Business_Ctrl.getImg(String, HttpServletResponse)");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}