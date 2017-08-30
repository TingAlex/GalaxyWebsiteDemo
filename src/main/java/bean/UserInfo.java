package bean;

import utils.UIDGenerator;

import java.util.LinkedList;

/**
 * Created by Ting on 2017/8/16.
 */
public class UserInfo {
    private String UID;
    private String Name;
    private String Gender;
    private String School;
    private String SchoolYears;
    private String TEL;
    private String NickName;
    private String Password;
    private Long Experience;
    private String Email;
    private String Sign;
//    private LinkedList<String> NoticeList;
//    private LinkedList<String> ListenerList;
//    private LinkedList<String> WatchList;
//    private LinkedList<String> ProductList;


    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
    public void setUID() {
        this.UID = UIDGenerator.getUID();
    }

    public UserInfo(String name, String gender, String school, String schoolYears, String TEL, String nickName, String password, Long experience, String email, String sign) {
        this.UID= UIDGenerator.getUID();
        Name = name;
        Gender = gender;
        School = school;
        SchoolYears = schoolYears;
        this.TEL = TEL;
        NickName = nickName;
        Password = password;
        Experience = experience;
        Email = email;
        Sign = sign;
    }

//    public UserInfo(String name, String gender, String school, String schoolYears, String TEL, String nickName, String password, Long experience, String email, String sign, LinkedList<String> noticeList, LinkedList<String> listenerList, LinkedList<String> watchList, LinkedList<String> productList) {
//        Name = name;
//        Gender = gender;
//        School = school;
//        SchoolYears = schoolYears;
//        this.TEL = TEL;
//        NickName = nickName;
//        Password = password;
//        Experience = experience;
//        Email = email;
//        Sign = sign;
//        NoticeList = noticeList;
//        ListenerList = listenerList;
//        WatchList = watchList;
//        ProductList = productList;
//    }

    public UserInfo() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getSchool() {
        return School;
    }

    public void setSchool(String school) {
        School = school;
    }

    public String getSchoolYears() {
        return SchoolYears;
    }

    public void setSchoolYears(String schoolYears) {
        SchoolYears = schoolYears;
    }

    public String getTEL() {
        return TEL;
    }

    public void setTEL(String TEL) {
        this.TEL = TEL;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Long getExperience() {
        return Experience;
    }

    public void setExperience(Long experience) {
        Experience = experience;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSign() {
        return Sign;
    }

    public void setSign(String sign) {
        Sign = sign;
    }
}
