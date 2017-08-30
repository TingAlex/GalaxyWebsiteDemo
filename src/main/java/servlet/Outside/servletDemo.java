package servlet.Outside;

import bean.TestPart.diaosi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * Created by Ting on 2017/8/7.
 */
@WebServlet(urlPatterns = {"/servletDemo"})
public class servletDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out=resp.getWriter();
        diaosi wang=new diaosi();
        wang.setCar(null);
        wang.setHas_girlfriend(true);
        LinkedList<String> major=new LinkedList<String>();
        major.add("lala");
        major.add("kaka");
        wang.setMajor(major);
        wang.setHouse(null);
        wang.setName("lala");
        wang.setSchool("lanxiang");
        wang.setIgnore("lalalalala");
        GsonBuilder gsonBuilder=new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson=gsonBuilder.create();
        System.out.println(gson.toJson(wang));
        out.println(gson.toJson(wang));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
