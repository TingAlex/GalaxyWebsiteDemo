package servlet.TestPart;

import bean.TestPart.diaosi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Ting on 2017/8/8.
 */
public class gsonReader {
    public static void main(String[] args) throws IOException {
        File file = new File(JsonObjectSample.class.getResource("../wang.json").getFile());
        String content = FileUtils.readFileToString(file, "UTF-8");
        Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        diaosi wang=gson.fromJson(content,diaosi.class);
        System.out.println(wang.getMajor());
    }
}
