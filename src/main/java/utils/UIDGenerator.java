package utils;

/**
 * Created by Ting on 2017/8/19.
 */
import java.util.UUID;
public class UIDGenerator {
    public static String getUID(){
        UUID uuid=UUID.randomUUID();
        String str=uuid.toString();
        str=str.replace("-","");
        return str;
    }
    public static void main(String[] args){
        System.out.print(getUID());
    }
}
