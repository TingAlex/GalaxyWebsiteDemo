package utils;

import bean.Product;
import bean.UserInfo;
import conn.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ting on 2017/8/17.
 */
public class DatabaseTest {
    public static void InitDatabase(Connection conn) throws SQLException{
        PreparedStatement pstm;
        String createUserInfoTable="CREATE TABLE userinfo(id CHAR (32) NOT NULL PRIMARY KEY, Name CHAR(40) NOT NULL ,Gender char(10),School char(50),SchoolYears char(20), TEL char(20),NickName char(40),Password char(20),Experience int(5),Email char(40),Sign char(50));";
        String createProductTable="CREATE TABLE product(id CHAR(32) NOT NULL PRIMARY KEY,Hot Long,Name CHAR(40) NOT NULL ,Detail CHAR(100),CreateDate DATE,ExpiredDate DATE,Personal CHAR(40),Official CHAR(50),TEL char(20),PricePerDay INT(4),Settled BOOLEAN,Useless BOOLEAN,Borrower CHAR(40));";
        String createNoticeAndInvationTable="CREATE TABLE Notice(id CHAR(32) NOT NULL PRIMARY KEY,Sender CHAR(40) NOT NULL ,Official CHAR(50),Document CHAR(100),CreateDate DATE,ExpiredDate DATE,Receiver CHAR(40),Useless BOOLEAN,RangeArea CHAR(50),Geolocation CHAR(100),Classfy INT(4),Hot LONG);";

        String sql="DROP DATABASE IF EXISTS galaxy;";
        pstm=conn.prepareStatement(sql);
        pstm.executeUpdate();

        sql="CREATE DATABASE galaxy;";
        pstm=conn.prepareStatement(sql);
        pstm.executeUpdate();

        sql="USE galaxy;";
        pstm=conn.prepareStatement(sql);
        pstm.executeUpdate();

        sql=createUserInfoTable;
        pstm=conn.prepareStatement(sql);
        pstm.executeUpdate();

        sql=createProductTable;
        pstm=conn.prepareStatement(sql);
        pstm.executeUpdate();

        sql=createNoticeAndInvationTable;
        pstm=conn.prepareStatement(sql);
        pstm.executeUpdate();
    }
    public static void createUser(Connection conn, UserInfo user) throws SQLException {

        String sql = "Insert into UserInfo(id,Name,Gender,School,SchoolYears,TEL,NickName,Password,Experience,Email,Sign) values (?,?,?,?,?,?,?,?,?,?,?) ";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,user.getUID());
        pstm.setString(2,user.getName());
        pstm.setString(3, user.getGender());
        pstm.setString(4,user.getSchool());
        pstm.setString(5,user.getSchoolYears());
        pstm.setString(6,user.getTEL());
        pstm.setString(7,user.getNickName());
        pstm.setString(8,user.getPassword());
        pstm.setLong(9,user.getExperience());
        pstm.setString(10,user.getEmail());
        pstm.setString(11,user.getSign());
        pstm.executeUpdate();
    }
    public static void createProduct(Connection conn, Product product) throws SQLException{
        String sql="Insert into Product(id,Hot,Name,Detail,CreateDate,ExpiredDate,Personal,Official,TEL,PricePerDay,Settled,Useless,Borrower) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstm=conn.prepareStatement(sql);
        pstm.setString(1,product.getUID());
        pstm.setLong(2,product.getHot());
        pstm.setString(3,product.getName());
        pstm.setString(4,product.getDetail());
        pstm.setDate(5,new java.sql.Date(product.getCreateDate().getTime()));
        pstm.setDate(6,new java.sql.Date(product.getExpiredDate().getTime()));
        pstm.setString(7,product.getPersonal());
        pstm.setString(8,product.getOfficial());
        pstm.setString(9,product.getTEL());
        pstm.setInt(10,product.getPricePerDay());
        pstm.setBoolean(11,product.getSettled());
        pstm.setBoolean(12,product.getUseless());
        pstm.setString(13,product.getBorrower());
        pstm.executeUpdate();
    }
    public static UserInfo getUserByNickName(Connection conn,String NickName)throws SQLException{
        String sql="SELECT a.id,a.Name,a.Gender,a.School,a.SchoolYears,a.TEL,a.NickName,a.Password,a.Experience,a.Email,a.Sign FROM userinfo a WHERE a.NickName=?";
        PreparedStatement pstm=conn.prepareStatement(sql);
        pstm.setString(1,NickName);
        ResultSet rs=pstm.executeQuery();

        while (rs.next()){
            UserInfo user=new UserInfo();
            user.setUID(rs.getString("id"));
            user.setName(rs.getString("Name"));
            user.setGender(rs.getString("Gender"));
            user.setSchool(rs.getString("School"));
            user.setSchoolYears(rs.getString("SchoolYears"));
            user.setTEL(rs.getString("TEL"));
            user.setNickName(rs.getString("NickName"));
            user.setPassword(rs.getString("Password"));
            user.setExperience(rs.getLong("Experience"));
            user.setEmail(rs.getString("Email"));
            user.setSign(rs.getString("Sign"));
            return user;
        }
        return null;
    }
    public static UserInfo getUserByNickNameOrEmail(Connection conn,String NickNameOrEmail,String Password)throws SQLException{
        String sql="SELECT a.id,a.Name,a.Gender,a.School,a.SchoolYears,a.TEL,a.NickName,a.Password,a.Experience,a.Email,a.Sign FROM userinfo a WHERE (a.NickName=? OR a.Email=?)and a.Password=?";
        PreparedStatement pstm=conn.prepareStatement(sql);
        pstm.setString(1,NickNameOrEmail);
        pstm.setString(2,NickNameOrEmail);
        pstm.setString(3,Password);
        ResultSet rs=pstm.executeQuery();

        while (rs.next()){
            UserInfo user=new UserInfo();
            user.setUID(rs.getString("id"));
            user.setName(rs.getString("Name"));
            user.setGender(rs.getString("Gender"));
            user.setSchool(rs.getString("School"));
            user.setSchoolYears(rs.getString("SchoolYears"));
            user.setTEL(rs.getString("TEL"));
            user.setNickName(rs.getString("NickName"));
            user.setPassword(rs.getString("Password"));
            user.setExperience(rs.getLong("Experience"));
            user.setEmail(rs.getString("Email"));
            user.setSign(rs.getString("Sign"));
            return user;
        }

        return null;
    }
    public static LinkedList<Product> getProductsByName(Connection conn, String Name)throws SQLException{
        String sql="SELECT a.id,a.Hot,a.Name,a.Detail,a.CreateDate,a.ExpiredDate,a.Personal,a.Official,a.TEL,a.PricePerDay,a.Settled,a.Useless,a.Borrower FROM product a WHERE a.Name =?";
        PreparedStatement pstm=conn.prepareStatement(sql);
        pstm.setString(1,Name);
        ResultSet rs=pstm.executeQuery();
        LinkedList<Product> products=new LinkedList<Product>();
        while (rs.next()){
            Product product=new Product();
            product.setUID(rs.getString("id"));
            product.setHot(rs.getLong("Hot"));
            product.setBorrower(rs.getString("Borrower"));
            product.setName(rs.getString("Name"));
            product.setCreateDate(rs.getDate("CreateDate"));
            product.setDetail(rs.getString("Detail"));
            product.setExpiredDate(rs.getDate("ExpiredDate"));
            product.setTEL(rs.getString("TEL"));
            product.setPersonal(rs.getString("Personal"));
            product.setOfficial(rs.getString("Official"));
            product.setPricePerDay(rs.getInt("PricePerDay"));
            product.setSettled(rs.getBoolean("Settled"));
            product.setUseless(rs.getBoolean("Useless"));
            products.add(product);
        }
        if(products.size()==0){
            return null;
        }else return products;
    }
    public static void deleteProductsByUID(Connection conn,String UID)throws SQLException{
        String sql="DELETE FROM product WHERE id=?";
        PreparedStatement pstm=conn.prepareStatement(sql);
        pstm.setString(1,UID);
        pstm.executeUpdate();

    }
    public static LinkedList<Product> getAllProductsByDefault(Connection conn)throws SQLException{
        String sql="SELECT a.id,a.Hot,a.Name,a.Detail,a.CreateDate,a.ExpiredDate,a.Personal,a.Official,a.TEL,a.PricePerDay,a.Settled,a.Useless,a.Borrower FROM product a";
        PreparedStatement pstm=conn.prepareStatement(sql);
        ResultSet rs=pstm.executeQuery();
        LinkedList<Product> products=new LinkedList<Product>();
        while (rs.next()){
            Product product=new Product();
            product.setUID(rs.getString("id"));
            product.setHot(rs.getLong("Hot"));
            product.setBorrower(rs.getString("Borrower"));
            product.setName(rs.getString("Name"));
            product.setCreateDate(rs.getDate("CreateDate"));
            product.setDetail(rs.getString("Detail"));
            product.setExpiredDate(rs.getDate("ExpiredDate"));
            product.setTEL(rs.getString("TEL"));
            product.setPersonal(rs.getString("Personal"));
            product.setOfficial(rs.getString("Official"));
            product.setPricePerDay(rs.getInt("PricePerDay"));
            product.setSettled(rs.getBoolean("Settled"));
            product.setUseless(rs.getBoolean("Useless"));
            products.add(product);
        }
        if(products.size()==0){
            return null;
        }else return products;
    }
    public static void main(String[] args){
        final String docPath="C:\\Users\\Ting\\Documents\\ResourcesForGalaxy";
        String errorString=null;
        UserInfo user =new UserInfo("lala","Woman","DLUT","Junier","13840853573","BaiBai","12345",100L,"10chongzheng@outlook.com","ahahahahaha");

        Product product=new Product(user,0L,"WangYuanJing","Get it For Free!", new Date(),new Date(),"",0,false,false,"");
        //Init
        try {
            Connection conn= ConnectionUtils.getConnection();
            InitDatabase(conn);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            errorString = e.getMessage();
        }catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
//        //Create a user
        try {
            Connection conn= ConnectionUtils.getConnection();
            createUser(conn, user);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            errorString = e.getMessage();
        }catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
//        //Create a product
//        try {
//            Connection conn= ConnectionUtils.getConnection();
//            createProduct(conn, product);
//        }catch (ClassNotFoundException e){
//            e.printStackTrace();
//            errorString = e.getMessage();
//        }catch (SQLException e) {
//            e.printStackTrace();
//            errorString = e.getMessage();
//        }
        //Get a user by NickName
//        try {
//            Connection conn=ConnectionUtils.getConnection();
//            String testNickName="BaiBai";
//            UserInfo userA=getUserByNickName(conn,testNickName);
//            if(userA!=null)
//                System.out.print(userA.getEmail());
//            else
//                errorString="No Find this User which NickName is "+testNickName;
//        }catch (ClassNotFoundException e){
//            e.printStackTrace();
//            errorString=e.getMessage();
//        }catch (SQLException e){
//            e.printStackTrace();
//            errorString=e.getMessage();
//        }
        //Get a user by NickName or Email and Password
//        try {
//            Connection conn=ConnectionUtils.getConnection();
//            String testNickNameOrEmail="BaiBai";
//            String testPassword="12345";
//            UserInfo userA=getUserByNickNameOrEmail(conn,testNickNameOrEmail,testPassword);
//            if(userA!=null)
//                System.out.print(userA.getEmail());
//            else
//                errorString="No Find this User which NickName is "+testNickNameOrEmail;
//        }catch (ClassNotFoundException e){
//            e.printStackTrace();
//            errorString=e.getMessage();
//        }catch (SQLException e){
//            e.printStackTrace();
//            errorString=e.getMessage();
//        }
        //Get products by Name
//        String UID="";
//        try {
//            Connection conn=ConnectionUtils.getConnection();
//            String testName="WangYuanJing";
//            LinkedList<Product> products=getProductsByName(conn,testName);
//            if(products!=null){
//                System.out.println("We got "+products.size()+" products for you.");
//                UID=products.getFirst().getUID();
//            }
//        }catch (ClassNotFoundException e){
//            e.printStackTrace();
//            errorString=e.getMessage();
//        }catch (SQLException e){
//            e.printStackTrace();
//            errorString=e.getMessage();
//        }
//      //Delete product by UID
//        try {
//            Connection conn=ConnectionUtils.getConnection();
//            deleteProductsByUID(conn,UID);
//                System.out.println("We delete "+UID+" product for you.");
//        }catch (ClassNotFoundException e){
//            e.printStackTrace();
//            errorString=e.getMessage();
//        }catch (SQLException e){
//            e.printStackTrace();
//            errorString=e.getMessage();
//        }
        //Get All  product by Default
        LinkedList<Product> productsAll;
//        try {
//            Connection conn=ConnectionUtils.getConnection();
//            productsAll=getAllProductsByDefault(conn);
//            if(productsAll!=null)
//                System.out.println("We get "+productsAll.size()+" products for you.");
//            else
//                System.out.println("We don't have any products.");
//        }catch (ClassNotFoundException e){
//            e.printStackTrace();
//            errorString=e.getMessage();
//        }catch (SQLException e){
//            e.printStackTrace();
//            errorString=e.getMessage();
//        }
    }
}
