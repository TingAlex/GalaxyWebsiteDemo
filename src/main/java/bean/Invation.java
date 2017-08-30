package bean;

import java.util.Date;

/**
 * Created by Ting on 2017/8/16.
 */
public class Invation extends Notice {
    private String RangeArea;
    private String Geolocation;
    private Integer Classfy;
    private Long Hot;

    public Invation(String sender, String official, String document, Date createTime, Date expiredTime, Boolean useless, String receiver, String rangeArea, String geolocation, Integer classfy, Long hot) {
        super(sender, official, document, createTime, expiredTime, useless, receiver);
        RangeArea = rangeArea;
        Geolocation = geolocation;
        Classfy = classfy;
        Hot = hot;
    }

    public String getRangeArea() {
        return RangeArea;
    }

    public void setRangeArea(String rangeArea) {
        RangeArea = rangeArea;
    }

    public String getGeolocation() {
        return Geolocation;
    }

    public void setGeolocation(String geolocation) {
        Geolocation = geolocation;
    }

    public Integer getClassfy() {
        return Classfy;
    }

    public void setClassfy(Integer classfy) {
        Classfy = classfy;
    }

    public Long getHot() {
        return Hot;
    }

    public void setHot(Long hot) {
        Hot = hot;
    }


}
