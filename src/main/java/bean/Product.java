package bean;

import utils.UIDGenerator;

import java.util.Date;

/**
 * Created by Ting on 2017/8/16.
 */
public class Product {
    private Long Hot;
    private String Name;
    private String UID;
    private String Detail;
    private Date CreateDate;
    private Date ExpiredDate;
    private String Personal;
    private String Official;
    private String TEL;
    private Integer PricePerDay;
    private Boolean Settled;
    private Boolean Useless;
    private String Borrower;

    public Product() {
    }

    public String getBorrower() {
        return Borrower;
    }

    public void setBorrower(String borrower) {
        Borrower = borrower;
    }

    public Product(Long hot, String name, String detail, Date createDate, Date expiredDate, String personal, String official, String TEL, Integer pricePerDay, Boolean settled, Boolean useless, String borrower) {
        Hot = hot;
        Name = name;
        this.UID = UIDGenerator.getUID();
        Detail = detail;
        CreateDate = createDate;
        ExpiredDate = expiredDate;
        Personal = personal;
        Official = official;
        this.TEL = TEL;
        PricePerDay = pricePerDay;
        Settled = settled;
        Useless = useless;
        Borrower=borrower;
    }

    public Product(UserInfo user,Long hot, String name, String detail, Date createDate, Date expiredDate,String official,Integer pricePerDay, Boolean settled, Boolean useless, String borrower){
        Hot = hot;
        Name = name;
        this.UID = UIDGenerator.getUID();
        Detail = detail;
        CreateDate = createDate;
        ExpiredDate = expiredDate;
        Personal = user.getEmail();
        Official = official;
        this.TEL = user.getTEL();
        PricePerDay = pricePerDay;
        Settled = settled;
        Useless = useless;
        Borrower=borrower;
    }
    public Long getHot() {
        return Hot;
    }

    public void setHot(Long hot) {
        Hot = hot;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public Date getExpiredDate() {
        return ExpiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        ExpiredDate = expiredDate;
    }

    public String getPersonal() {
        return Personal;
    }

    public void setPersonal(String personal) {
        Personal = personal;
    }

    public String getOfficial() {
        return Official;
    }

    public void setOfficial(String official) {
        Official = official;
    }

    public String getTEL() {
        return TEL;
    }

    public void setTEL(String TEL) {
        this.TEL = TEL;
    }

    public Integer getPricePerDay() {
        return PricePerDay;
    }

    public void setPricePerDay(Integer pricePerDay) {
        PricePerDay = pricePerDay;
    }

    public Boolean getSettled() {
        return Settled;
    }

    public void setSettled(Boolean settled) {
        Settled = settled;
    }

    public Boolean getUseless() {
        return Useless;
    }

    public void setUseless(Boolean useless) {
        Useless = useless;
    }
}
