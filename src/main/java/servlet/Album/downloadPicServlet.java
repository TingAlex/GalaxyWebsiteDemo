package servlet.Album;

import bean.UserInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import conn.ConnectionUtils;
import utils.DatabaseTest;
import utils.MyUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;
import static utils.DatabaseTest.getAlbumPageNum;
import static utils.DatabaseTest.getSrcByAlbumPageNum;

/**
 * Created by Ting on 2017/12/2.
 */
@WebServlet(name = "downloadPicServlet", urlPatterns = {"/test/downloadPic"})
public class downloadPicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //参数只需要图片的UID+after后缀就足够了
//        BufferedImage img = new BufferedImage(300, 150, BufferedImage.TYPE_INT_RGB);
        String addr = req.getParameter("pic");
        UserInfo user = MyUtils.getLoginedUser(req.getSession());
        String uploadPath = DatabaseTest.ResourcePath + user.getUID();
        String filePath = uploadPath + File.separator + addr;
        System.out.println("want to download : "+filePath);
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            resp.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(addr, "UTF-8"));
            // 读取要下载的文件，保存到文件输入流
            FileInputStream in = new FileInputStream(filePath);
            // 创建输出流
            ServletOutputStream out = resp.getOutputStream();
            // 创建缓冲区
            byte buffer[] = new byte[1024];
            int len = 0;
            // 循环将输入流中的内容读取到缓冲区当中
            while ((len = in.read(buffer)) > 0) {
                // 输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
            }
            // 关闭文件输入流
            in.close();
            out.flush();
            // 关闭输出流
            out.close();
            System.out.println(addr + " download success!");
//            req.setAttribute("downloadMessage", "download success!");
//            req.getRequestDispatcher("/console").forward(req, resp);
        } else {
            System.out.println(addr + " can't download");
//            req.setAttribute("downloadMessage", "download success!");
//            req.getRequestDispatcher("/console").forward(req, resp);
        }
    }
}
