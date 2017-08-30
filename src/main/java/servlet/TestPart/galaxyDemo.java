package servlet.TestPart;

import bean.TestPart.galaxy;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Ting on 2017/8/7.
 */
@WebServlet(urlPatterns = {"/galaxyDemo"})
public class galaxyDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out=resp.getWriter();
        File file = new File(JsonObjectSample.class.getResource("../galaxy.json").getFile());
        String content = FileUtils.readFileToString(file, "UTF-8");
        Gson gson=new Gson();
        galaxy wang=gson.fromJson(content,galaxy.class);
        out.println(gson.toJson(wang));

//        diaosi wang=new diaosi();
//        wang.setCar(null);
//        wang.setHas_girlfriend(true);
//        LinkedList<String> major=new LinkedList<String>();
//        major.add("lala");
//        major.add("kaka");
//        wang.setMajor(major);
//        wang.setHouse(null);
//        wang.setName("lala");
//        wang.setSchool("lanxiang");
//        wang.setIgnore("lalalalala");
//        GsonBuilder gsonBuilder=new GsonBuilder();
//        gsonBuilder.setPrettyPrinting();
//        Gson gson=gsonBuilder.create();
//        System.out.println(gson.toJson(wang));
//        out.println(gson.toJson(wang));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
