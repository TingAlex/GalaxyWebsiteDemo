package servlet.Album;

import bean.UserInfo;
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
import java.util.List;
import java.util.UUID;

/**
 * Created by Ting on 2017/9/24.
 */
@WebServlet(name = "uploadHeadpicServlet", urlPatterns = {"/test/uploadHeadpic"})
public class uploadHeadpicServlet extends HttpServlet {
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
        try {
            List<FileItem> formItems = upload.parseRequest(req);
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
//                        //将节点信息保存到总表
//                        FileInfo fileInfo = new FileInfo();
//                        fileInfo.setFileName(fileName);
                        //计算UID和key
                        String UID = UUID.randomUUID().toString();
//                        fileInfo.setUID(UID);
//                        fileInfo.setKey(UID);
//                        fileInfo.setSize(new File(item.getName()).length());
//                        HttpSession session = req.getSession();
//                        String userName = (String) session.getAttribute("name");
//                        fileInfo.setUser(userName);

                        //将节点信息保存到waitList
//                        WaitForUpload waitInfo = new WaitForUpload();
//                        waitInfo.setFileName(fileName);
//                        waitInfo.setUID(UID);
//                        waitForUploadList.add(waitInfo);
                        String filePath = uploadPath + File.separator + UID;
                        File storeFile = new File(filePath);
                        System.out.println(filePath);
                        item.write(storeFile);
//                        //把文件大小信息写进fileInfo
//                        fileInfo.setSize(new File(filePath).length());
//                        fileInfoList.add(fileInfo);
                        //在这里开启线程执行上传文件到SN操作
//                        if (UploadToSN(UID, new File(filePath))) {
//                            req.setAttribute("uploadMessage", "upload success!");
//                            req.setAttribute("UID", UID);
                        resp.getWriter().print("{\"message\":\"success!\",\"address\":\""+filePath+"\"}");
//                            file=new File(filePath);
//                            storeFile.delete();
//                        } else
//                            req.setAttribute("uploadMessage", "upload false");

                    }
                }
            }
        } catch (Exception e) {
            System.out.println("uploadMessage error: " + e.getMessage());
        }
//        req.getRequestDispatcher("/UserPage/Self_intro.jsp").forward(req, resp);
    }
}
