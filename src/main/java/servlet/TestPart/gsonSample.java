package servlet.TestPart;

import bean.TestPart.diaosi;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Field;
import java.util.LinkedList;

/**
 * Created by Ting on 2017/8/8.
 */
public class gsonSample {
    public static void main(String[] args){
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
        gsonBuilder.setFieldNamingStrategy(new FieldNamingStrategy(){

            public String translateName(Field field) {
                if(field.getName().equals("name")){
                    return "NAME";
                }else
                    return field.getName();
            }
        });
        Gson gson=gsonBuilder.create();
        System.out.println(gson.toJson(wang));
    }
}
