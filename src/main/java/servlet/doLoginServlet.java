package servlet;

import bean.TestPart.diaosi;
import bean.UserInfo;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import conn.ConnectionUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

import static utils.DatabaseTest.getUserByNickNameOrEmail;

/**
 * Created by Ting on 2017/8/22.
 */
@WebServlet(name = "doLoginServlet",urlPatterns = {"/test/login"})
public class doLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Name=req.getParameter("user_id");
        String Pass=req.getParameter("pass");

//        String form=req.getParameter("form_data");
        System.out.println(Name+" "+Pass);
        String errorString;
        try {
            Connection conn= ConnectionUtils.getConnection();
            UserInfo userA=getUserByNickNameOrEmail(conn,Name,Pass);
            if(userA!=null)
            {
                //设置session
                HttpSession session=req.getSession();
                session.setAttribute("user",userA);
                //把用户信息输出到home.jsp，留待之后实现动态查看自己的缩略信息功能
                GsonBuilder gsonBuilder=new GsonBuilder();
                gsonBuilder.setPrettyPrinting();
                Gson gson=gsonBuilder.create();
                resp.getWriter().print(gson.toJson(userA));
            }
            else
                errorString="No Find this User which NickName is "+Name;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            errorString=e.getMessage();
        }catch (SQLException e){
            e.printStackTrace();
            errorString=e.getMessage();
        }
//        //test
//        diaosi wang=new diaosi();
//
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
//        gsonBuilder.setFieldNamingStrategy(new FieldNamingStrategy(){
//
//            public String translateName(Field field) {
//                if(field.getName().equals("name")){
//                    return "NAME";
//                }else
//                    return field.getName();
//            }
//        });
//        Gson gson=gsonBuilder.create();
//        resp.getWriter().print(gson.toJson(wang));
        //test
//        resp.getWriter().print("123445");
//        JSONArray jsonArray = JSONArray.fromObject(jsonStr);
//        String name = "";
//        String age = "";
//        for (int i = 0; i < jsonArray.size(); i++) {
//            JSONObject jsonJ = jsonArray.getJSONObject(i);
//            name = jsonJ.getString("name");
//            age = jsonJ.getString("age");
//        }
//        System.out.println(form);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
