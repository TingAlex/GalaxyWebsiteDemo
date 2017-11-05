package bean;

import utils.UIDGenerator;

/**
 * Created by Ting on 2017/8/16.
 */
public class UserInfo {
    private String UID;
    private String Name;
    private String Gender;
    private String School;
    private String SchoolYears;
    private String Tel;
    private String NickName;
    private String Password;
    private Long Experience;
    private String Email;
    private String Sign;
    private String HeadUID;
    private Boolean IsAdmin;
    private Boolean IsSchoolAdmin;

    public String getHeadUID() {
        return HeadUID;
    }

    public void setHeadUID(String headUID) {
        HeadUID = headUID;
    }

    public Boolean getAdmin() {
        return IsAdmin;
    }

    public void setAdmin(Boolean admin) {
        IsAdmin = admin;
    }

    public Boolean getSchoolAdmin() {
        return IsSchoolAdmin;
    }

    public void setSchoolAdmin(Boolean schoolAdmin) {
        IsSchoolAdmin = schoolAdmin;
    }


    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
    public void setUID() {
        this.UID = UIDGenerator.getUID();
    }

    public UserInfo(String UID, String name, String gender, String school, String schoolYears, String Tel, String nickName, String password, Long experience, String email, String sign, String headUID, Boolean isAdmin, Boolean isSchoolAdmin) {
        this.UID = UID;
        Name = name;
        Gender = gender;
        School = school;
        SchoolYears = schoolYears;
        this.Tel = Tel;
        NickName = nickName;
        Password = password;
        Experience = experience;
        Email = email;
        Sign = sign;
        HeadUID = headUID;
        IsAdmin = isAdmin;
        IsSchoolAdmin = isSchoolAdmin;
    }

    public UserInfo() {}

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

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        this.Tel = tel;
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
