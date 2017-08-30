package bean.TestPart;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Ting on 2017/8/8.
 */
public class diaosi {
    private String name;
    private String school;
    private List<String> major;
    private Object car;
    private Object house;
    private boolean has_girlfriend;
    private Date birthday;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIgnore() {
        return ignore;
    }


    public void setIgnore(String ignore) {
        this.ignore = ignore;
    }

    private transient String ignore;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public List<String> getMajor() {
        return major;
    }

    public void setMajor(List<String> major) {
        this.major = major;
    }

    public Object getCar() {
        return car;
    }

    public void setCar(Object car) {
        this.car = car;
    }

    public Object getHouse() {
        return house;
    }

    public void setHouse(Object house) {
        this.house = house;
    }

    public boolean isHas_girlfriend() {
        return has_girlfriend;
    }

    public void setHas_girlfriend(boolean has_girlfriend) {
        this.has_girlfriend = has_girlfriend;
    }
}
