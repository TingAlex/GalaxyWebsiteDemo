package bean;

import utils.UIDGenerator;

import java.util.Date;

/**
 *
 * Created by Ting on 2017/9/16.
 */
public class Album {
    private String UID;
    private String PersonUID;
    private String Type;
    private Date CreateDate;
    private String Geolocation;
    private Long Hot;
    private String Document;

    public Album(String UID, String personUID, String type, Date createDate, String geolocation, Long hot, String document) {
        this.UID = UID;
        PersonUID = personUID;
        Type = type;
        CreateDate = createDate;
        Geolocation = geolocation;
        Hot = hot;
        Document = document;
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

    public String getPersonUID() {
        return PersonUID;
    }

    public void setPersonUID(String personUID) {
        PersonUID = personUID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public String getGeolocation() {
        return Geolocation;
    }

    public void setGeolocation(String geolocation) {
        Geolocation = geolocation;
    }

    public Long getHot() {
        return Hot;
    }

    public void setHot(Long hot) {
        Hot = hot;
    }

    public String getDocument() {
        return Document;
    }

    public void setDocument(String document) {
        Document = document;
    }
}
