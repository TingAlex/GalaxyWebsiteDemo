package servlet.TestPart;
import bean.TestPart.diaosi;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Ting on 2017/8/7.
 */
public class JsonObjectSample {
    private static void JSONObject(){
        JSONObject wang=new JSONObject();
        Object nullObj=null;
        try {
            wang.put("name","wang");
            wang.put("age",25.2);
            wang.put("birthday","1990-01-01");
            wang.put("major",new String[]{"lifa","wajueji"});
            wang.put("has_girlfriend",false);
            wang.put("car",nullObj);
            wang.put("house",nullObj);
            System.out.println(wang.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void createJsonByMap(){
        Map<String,Object> wang=new HashMap<String, Object>();
        Object nullObj=null;
        wang.put("name","wang");
        wang.put("age",25.2);
        wang.put("birthday","1990-01-01");
        wang.put("major",new String[]{"lifa","wajueji"});
        wang.put("has_girlfriend",false);
        wang.put("car",nullObj);
        wang.put("house",nullObj);
        System.out.println(new JSONObject(wang).toString());
    }
    public static void createJsonByBean(){
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
        System.out.println(new JSONObject(wang));
    }
    public static void main(String[] args)throws IOException,JSONException{
       // JSONObject();
        //createJsonByMap();
        //createJsonByBean();
//        System.out.println(JsonObjectSample.class.getResource("").toString());
        File file=new File(JsonObjectSample.class.getResource("../wang.json").getFile());
        String content= FileUtils.readFileToString(file);
        JSONObject jsonObject=new JSONObject(content);
        System.out.println(jsonObject.getDouble("age"));
        JSONArray majorArray=jsonObject.getJSONArray("major");
        for(int i=0;i<majorArray.length();i++){
            String m=(String)majorArray.get(i);
            System.out.println("major "+m);
        }
    }
}
