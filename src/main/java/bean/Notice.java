package bean;

import utils.UIDGenerator;

import java.util.Date;

/**
 * Created by Ting on 2017/8/16.
 */
public class Notice {
    private String UID;
    private String Sender;
    private String Official;
    private String Document;
    private Date CreateDate;
    private Date ExpiredDate;
    private Boolean Useless;
    private String Receiver;

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String receiver) {
        Receiver = receiver;
    }

    public Notice(String sender, String official, String document, Date createDate, Date expiredDate, Boolean useless, String receiver) {
        Sender = sender;
        Official = official;
        Document = document;
        CreateDate = createDate;
        ExpiredDate = expiredDate;
        Useless = useless;
        Receiver = receiver;
        UID= UIDGenerator.getUID();
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getOfficial() {
        return Official;
    }

    public void setOfficial(String official) {
        Official = official;
    }

    public String getDocument() {
        return Document;
    }

    public void setDocument(String document) {
        Document = document;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        this.CreateDate = createDate;
    }

    public Date getExpiredDate() {
        return ExpiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.ExpiredDate = expiredDate;
    }

    public Boolean getUseless() {
        return Useless;
    }

    public void setUseless(Boolean useless) {
        Useless = useless;
    }
}
