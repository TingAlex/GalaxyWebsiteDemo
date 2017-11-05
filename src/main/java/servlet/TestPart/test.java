package servlet.TestPart;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Ting on 2017/9/11.
 */
public class test {
     static int lengthOfLongestSubstring(String s) {
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max=0;
        for (int i=0, j=0; i<s.length(); ++i){
            if (map.containsKey(s.charAt(i))){
                j = Math.max(j,map.get(s.charAt(i))+1);
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-j+1);
        }
        return max;
    }

    public static void main(String[] args){
        String path="C:\\Users\\Ting\\Documents\\Galaxy\\";
        String UID="12345";
        File file=new File(path+UID);
        if(file.mkdir()){
            System.out.println("单文件夹创建成功！创建后的文件目录为：" + file.getPath() + ",上级文件为:" + file.getParent());
        }
    }
}
