package utils;

import bean.Album;
import bean.Product;
import bean.UserInfo;
import conn.ConnectionUtils;
import servlet.Album.getAlbumPageServlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by Ting on 2017/8/17.
 */
public class DatabaseTest {
    public static String ResourcePath = "C:\\Users\\Ting\\Documents\\Galaxy\\";

    public static void InitDatabase(Connection conn) throws SQLException {
        PreparedStatement pstm;
        String createUserInfoTable = "CREATE TABLE userinfo(id CHAR (32) NOT NULL PRIMARY KEY, Name CHAR(40) NOT NULL ,Gender char(10),School char(50),SchoolYears char(20), TEL char(20),NickName char(40),Password char(20),Experience int(5),Email char(40),Sign char(50),HeadUID char(32),IsAdmin BOOLEAN NOT NULL,IsSchoolAdmin BOOLEAN NOT NULL,UNIQUE (Email),UNIQUE (NickName))ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        String createAlbumTable = "CREATE TABLE Album(UID CHAR(32) NOT NULL , PersonUID CHAR(32) NOT NULL ,Title CHAR (50),Type CHAR(32) NOT NULL , CreateDate DATETIME NOT NULL , Geolocation CHAR(50) , Hot LONG , Document CHAR(100))ENGINE=InnoDB DEFAULT CHARSET=utf8;";

        String createProductTable = "CREATE TABLE product(id CHAR(32) NOT NULL PRIMARY KEY,Hot Long,Name CHAR(40) NOT NULL ,Detail CHAR(100),CreateDate DATE,ExpiredDate DATE,Personal CHAR(40),Official CHAR(50),TEL char(20),PricePerDay INT(4),Settled BOOLEAN,Useless BOOLEAN,Borrower CHAR(40))ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        String createNoticeAndInvationTable = "CREATE TABLE Notice(id CHAR(32) NOT NULL PRIMARY KEY,Sender CHAR(40) NOT NULL ,Official CHAR(50),Document CHAR(100),CreateDate DATE,ExpiredDate DATE,Receiver CHAR(40),Useless BOOLEAN,RangeArea CHAR(50),Geolocation CHAR(100),Classfy INT(4),Hot LONG)ENGINE=InnoDB DEFAULT CHARSET=utf8;";

        String createPostsTable = "CREATE TABLE POSTS(HEAD CHAR(32) NOT NULL PRIMARY KEY,WRITER CHAR(32) NOT NULL,CATEGORY CHAR(32) NOT NULL,TAG CHAR(32),POSTDATE DATE,CONTENT TEXT ) character set = utf8;";

        String createCommentsTable = "CREATE TABLE COMMENTS(WRITER CHAR(32),TITLE CHAR(32),TEXTS TEXT,NUM BIGINT,PRIMARY KEY(WRITER,TITLE,NUM)) character set = utf8;";

        String sql = "DROP DATABASE IF EXISTS galaxy;";
        pstm = conn.prepareStatement(sql);
        pstm.executeUpdate();

        sql = "CREATE DATABASE galaxy character set utf8 COLLATE utf8_general_ci;";
        pstm = conn.prepareStatement(sql);
        pstm.executeUpdate();

        sql = "USE galaxy;";
        pstm = conn.prepareStatement(sql);
        pstm.executeUpdate();

        sql = createUserInfoTable;
        pstm = conn.prepareStatement(sql);
        pstm.executeUpdate();

        sql = createAlbumTable;
        pstm = conn.prepareStatement(sql);
        pstm.executeUpdate();

        sql = createProductTable;
        pstm = conn.prepareStatement(sql);
        pstm.executeUpdate();

        sql = createNoticeAndInvationTable;
        pstm = conn.prepareStatement(sql);
        pstm.executeUpdate();

        sql = createPostsTable;
        pstm = conn.prepareStatement(sql);
        pstm.executeUpdate();

        sql = createCommentsTable;
        pstm = conn.prepareStatement(sql);
        pstm.executeUpdate();
    }

    public static Boolean TranFile(String src, String dest) throws Exception {
        FileInputStream in = new FileInputStream(src);
        File file = new File(dest);
        FileOutputStream out = new FileOutputStream(file);
        int c;
        byte buffer[] = new byte[1024];
        while ((c = in.read(buffer)) != -1) {
            for (int i = 0; i < c; i++)
                out.write(buffer[i]);
        }
        in.close();
        out.close();
        return true;
    }

    public static void addInitHeadpic(Connection conn, UserInfo user) throws SQLException {
        String UIDPath = user.getUID();
        String userPath = ResourcePath + UIDPath;

        String rootFile1 = "C:\\Users\\Ting\\Documents\\Galaxy\\default_user_headpic.jpg";
        String rootFile2 = userPath + File.separator + user.getHeadUID() + ".jpg";
        System.out.println(userPath + File.separator + user.getHeadUID() + ".jpg");
        try {
            TranFile(rootFile1, rootFile2);
        } catch (Exception e) {
            System.out.println("can't trans");
        }
        try {
            Date date = new Date();
            Album album = new Album(user.getHeadUID(), user.getUID(), ".jpg", date, "", 0L, "");
            addPics(conn, album);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public static void delFile(String filepath) {
        File file = new File(filepath);
        if (file.exists() && file.isFile())
            if (file.delete()) {
                System.out.println("delete: " + filepath + " successful!");
            } else {
                System.out.println("delete: " + filepath + " failed!");
            }
    }

    public static void deletePicFileByUID(Connection conn, String UID) throws SQLException {
        //根据UID拼接到完整路径，删除文件
        String sql = "SELECT a.PersonUID,a.Type,a.CreateDate,a.Geolocation,a.Hot,a.Document FROM album a WHERE a.UID=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, UID);
        ResultSet rs = pstm.executeQuery();
        String type = null;
        String user = null;
        while (rs.next()) {
            type = rs.getString("Type");
            user = rs.getString("PersonUID");
        }
        String picPath = ResourcePath + user + File.separator + UID + type;
        delFile(picPath);
    }

    public static LinkedList<getAlbumPageServlet.union> getSrcByAlbumPageNum(Connection conn, String userUID, int pageNum, int range) throws SQLException {
        String sql = "SELECT UID,Type,Title,Document FROM album WHERE PersonUID =? limit ?,?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userUID);
        pstm.setInt(2, pageNum * range);
        pstm.setInt(3, range);
        ResultSet rs = pstm.executeQuery();
        LinkedList<getAlbumPageServlet.union> srcs = new LinkedList<getAlbumPageServlet.union>();
        int i = pageNum * range;
        while (rs.next()) {
            System.out.println("test for index: " + i + " " + rs.getString("UID"));
            getAlbumPageServlet.union ion = new getAlbumPageServlet.union(rs.getString("UID") + rs.getString("Type"), rs.getString("Title"), rs.getString("Document"));
            srcs.add(ion);
        }
        return srcs;
    }

    public static int getAlbumPageNum(Connection conn, String userUID, int range) throws SQLException {
        String sql = "SELECT COUNT(*) as T FROM album WHERE PersonUID =?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userUID);
        ResultSet rs = pstm.executeQuery();
        int pageNum = 0;
        while (rs.next()) {
            pageNum = rs.getInt("T") / range;
        }
        return pageNum;
    }

    public static void deletePicRecordByUID(Connection conn, String UID) throws SQLException {
        //删除数据库记录
        String sql = "DELETE FROM album WHERE UID=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, UID);
        pstm.executeUpdate();
        System.out.println("delete: " + UID + " record success!");
    }

    public static void createUser(Connection conn, UserInfo user) throws SQLException {

        String UIDPath = user.getUID();
        String userPath = ResourcePath + UIDPath;

        File rootFile = new File(userPath);
        rootFile.mkdir();
        String picPath = userPath + "\\pic";
        String productPath = userPath + "\\product";
        rootFile = new File(picPath);
        rootFile.mkdir();
        rootFile = new File(productPath);
        rootFile.mkdir();
        addInitHeadpic(conn, user);

        String sql = "Insert into UserInfo(id,Name,Gender,School,SchoolYears,TEL,NickName,Password,Experience,Email,Sign,HeadUID,IsAdmin,IsSchoolAdmin) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, user.getUID());
        pstm.setString(2, user.getName());
        pstm.setString(3, user.getGender());
        pstm.setString(4, user.getSchool());
        pstm.setString(5, user.getSchoolYears());
        pstm.setString(6, user.getTel());
        pstm.setString(7, user.getNickName());
        pstm.setString(8, user.getPassword());
        pstm.setLong(9, user.getExperience());
        pstm.setString(10, user.getEmail());
        pstm.setString(11, user.getSign());
        pstm.setString(12, user.getHeadUID());
        pstm.setBoolean(13, user.getAdmin());
        pstm.setBoolean(14, user.getSchoolAdmin());
        pstm.executeUpdate();
    }

    public static void updateUserHeadPicRecord(Connection conn, UserInfo user, String headUID) throws SQLException {
        String sql = "UPDATE userinfo SET HeadUID=? WHERE id=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, headUID);
        pstm.setString(2, user.getUID());
        pstm.executeUpdate();
        System.out.println("update: " + headUID + " record success!");
    }

    public static void createProduct(Connection conn, Product product) throws SQLException {
        String sql = "Insert into Product(id,Hot,Name,Detail,CreateDate,ExpiredDate,Personal,Official,TEL,PricePerDay,Settled,Useless,Borrower) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, product.getUID());
        pstm.setLong(2, product.getHot());
        pstm.setString(3, product.getName());
        pstm.setString(4, product.getDetail());
        pstm.setDate(5, new java.sql.Date(product.getCreateDate().getTime()));
        pstm.setDate(6, new java.sql.Date(product.getExpiredDate().getTime()));
        pstm.setString(7, product.getPersonal());
        pstm.setString(8, product.getOfficial());
        pstm.setString(9, product.getTEL());
        pstm.setInt(10, product.getPricePerDay());
        pstm.setBoolean(11, product.getSettled());
        pstm.setBoolean(12, product.getUseless());
        pstm.setString(13, product.getBorrower());
        pstm.executeUpdate();
    }

    public static void setDefaultHeadPic(Connection conn) {

    }

    //***将一张图片设置为用户头像
    public static void addHeadPic(Connection conn, Album headPic) throws SQLException {
        String sql = "UPDATE userinfo SET HeadUID='" + headPic.getUID() + "' WHERE id='" + headPic.getPersonUID() + "'";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.executeUpdate();
    }

    public static void addPics(Connection conn, Album pic) throws SQLException {
        String sql = "INSERT INTO Album(UID,PersonUID,Type,CreateDate,Geolocation,Hot,Document) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, pic.getUID());
        pstm.setString(2, pic.getPersonUID());
        pstm.setString(3, pic.getType());
        pstm.setDate(4, new java.sql.Date(pic.getCreateDate().getTime()));
        pstm.setString(5, pic.getGeolocation());
        pstm.setLong(6, pic.getHot());
        pstm.setString(7, pic.getDocument());
        pstm.executeUpdate();
    }

    public static Album getPicByUID(Connection conn, String UID) throws SQLException {
        //***通过用户中的headUID取得Album表中 对应头像图片的UID和type的拼接字符串
        String sql = "SELECT a.PersonUID,a.Type,a.CreateDate,a.Geolocation,a.Hot,a.Document FROM album a WHERE a.UID=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, UID);
        ResultSet rs = pstm.executeQuery();
        Album album = null;
        while (rs.next()) {
            album = new Album(UID, rs.getString("PersonUID"), rs.getString("Type"),
                    rs.getDate("CreateDate"), rs.getString("Geolocation"), rs.getLong("Hot"), rs.getString("Document"));
        }
        return album;
    }

    public static UserInfo changeUserInfo(Connection conn, UserInfo user) throws SQLException {
        //需要根据参数user的UID查找到UserInfo表中对应的项，并把那项user中的内容更改。需要更改的属性有
//        user.setName(req.getParameter("realname"));
//        user.setGender(req.getParameter("gender"));
//        user.setSchool(req.getParameter("school"));
//        user.setSchoolYears(req.getParameter("schoolyears"));
//        user.setTel(req.getParameter("tel"));
//        user.setNickName(req.getParameter("nickname"));
//        user.setEmail(req.getParameter("email"));
//        user.setSign(req.getParameter("sign"));
        return user;
    }

    public static UserInfo getUserByNickName(Connection conn, String NickName) throws SQLException {
        String sql = "SELECT a.id,a.Name,a.Gender,a.School,a.SchoolYears,a.TEL,a.NickName,a.Password,a.Experience,a.Email,a.Sign,a.HeadUID,a.IsAdmin,a.IsSchoolAdmin FROM userinfo a WHERE a.NickName=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, NickName);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            UserInfo user = new UserInfo();
            user.setUID(rs.getString("id"));
            user.setName(rs.getString("Name"));
            user.setGender(rs.getString("Gender"));
            user.setSchool(rs.getString("School"));
            user.setSchoolYears(rs.getString("SchoolYears"));
            user.setTel(rs.getString("TEL"));
            user.setNickName(rs.getString("NickName"));
            user.setPassword(rs.getString("Password"));
            user.setExperience(rs.getLong("Experience"));
            user.setEmail(rs.getString("Email"));
            user.setSign(rs.getString("Sign"));
            user.setHeadUID(rs.getString("HeadUID"));
            user.setAdmin(rs.getBoolean("IsAdmin"));
            user.setSchoolAdmin(rs.getBoolean("IsSchoolAdmin"));
            return user;
        }
        return null;
    }

    public static UserInfo getUserByNickNameOrEmail(Connection conn, String NickNameOrEmail, String Password) throws SQLException {
        String sql = "SELECT a.id,a.Name,a.Gender,a.School,a.SchoolYears,a.TEL,a.NickName,a.Password,a.Experience,a.Email,a.Sign,a.HeadUID,a.IsAdmin,a.IsSchoolAdmin FROM userinfo a WHERE (a.NickName=? OR a.Email=?)and a.Password=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, NickNameOrEmail);
        pstm.setString(2, NickNameOrEmail);
        pstm.setString(3, Password);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            UserInfo user = new UserInfo();
            user.setUID(rs.getString("id"));
            user.setName(rs.getString("Name"));
            user.setGender(rs.getString("Gender"));
            user.setSchool(rs.getString("School"));
            user.setSchoolYears(rs.getString("SchoolYears"));
            user.setTel(rs.getString("TEL"));
            user.setNickName(rs.getString("NickName"));
            user.setPassword(rs.getString("Password"));
            user.setExperience(rs.getLong("Experience"));
            user.setEmail(rs.getString("Email"));
            user.setSign(rs.getString("Sign"));
            user.setHeadUID(rs.getString("HeadUID"));
            user.setAdmin(rs.getBoolean("IsAdmin"));
            user.setSchoolAdmin(rs.getBoolean("IsSchoolAdmin"));
            return user;
        }

        return null;
    }

    public static UserInfo getUserByEmail(Connection conn, String email) throws SQLException {
        String sql = "SELECT a.id,a.Name,a.Gender,a.School,a.SchoolYears,a.TEL,a.NickName,a.Password,a.Experience,a.Email,a.Sign,a.HeadUID,a.IsAdmin,a.IsSchoolAdmin FROM userinfo a WHERE a.Email=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, email);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            UserInfo user = new UserInfo();
            user.setUID(rs.getString("id"));
            user.setName(rs.getString("Name"));
            user.setGender(rs.getString("Gender"));
            user.setSchool(rs.getString("School"));
            user.setSchoolYears(rs.getString("SchoolYears"));
            user.setTel(rs.getString("TEL"));
            user.setNickName(rs.getString("NickName"));
            user.setPassword(rs.getString("Password"));
            user.setExperience(rs.getLong("Experience"));
            user.setEmail(rs.getString("Email"));
            user.setSign(rs.getString("Sign"));
            user.setHeadUID(rs.getString("HeadUID"));
            user.setAdmin(rs.getBoolean("IsAdmin"));
            user.setSchoolAdmin(rs.getBoolean("IsSchoolAdmin"));
            return user;
        }
        return null;
    }

    public static LinkedList<Product> getProductsByName(Connection conn, String Name) throws SQLException {
        String sql = "SELECT a.id,a.Hot,a.Name,a.Detail,a.CreateDate,a.ExpiredDate,a.Personal,a.Official,a.TEL,a.PricePerDay,a.Settled,a.Useless,a.Borrower FROM product a WHERE a.Name =?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, Name);
        ResultSet rs = pstm.executeQuery();
        LinkedList<Product> products = new LinkedList<Product>();
        while (rs.next()) {
            Product product = new Product();
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
        if (products.size() == 0) {
            return null;
        } else return products;
    }

    public static void deleteProductsByUID(Connection conn, String UID) throws SQLException {
        String sql = "DELETE FROM product WHERE id=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, UID);
        pstm.executeUpdate();

    }

    public static LinkedList<Product> getAllProductsByDefault(Connection conn) throws SQLException {
        String sql = "SELECT a.id,a.Hot,a.Name,a.Detail,a.CreateDate,a.ExpiredDate,a.Personal,a.Official,a.TEL,a.PricePerDay,a.Settled,a.Useless,a.Borrower FROM product a";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        LinkedList<Product> products = new LinkedList<Product>();
        while (rs.next()) {
            Product product = new Product();
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
        if (products.size() == 0) {
            return null;
        } else return products;
    }

    public static void main(String[] args) {
        final String docPath = "C:\\Users\\Ting\\Documents\\ResourcesForGalaxy";
        String errorString = null;
        UserInfo user = new UserInfo();
        user.setUID();

        user.setSign("ahahahahaha");
        user.setEmail("10chongzheng@outlook.com");
        user.setExperience(100L);
        user.setAdmin(false);
        user.setGender("Woman");
        user.setHeadUID("");
        user.setNickName("ting");
        user.setPassword("12345");
        user.setSchool("DLUT");
        user.setSchoolAdmin(false);
        user.setSchoolYears("Junier");
        user.setTel("18340853573");
        user.setName("石崇正");

//        Product product=new Product(user,0L,"WangYuanJing","Get it For Free!", new Date(),new Date(),"",0,false,false,"");
        //Init
        try {
            Connection conn = ConnectionUtils.getConnection();
            InitDatabase(conn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
//        //Create a user
        try {
            Connection conn = ConnectionUtils.getConnection();
            createUser(conn, user);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } catch (SQLException e) {
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
//            Connection conn = ConnectionUtils.getConnection();
//            String testNickNameOrEmail = "ting";
//            String testPassword = "12345";
//            UserInfo userA = getUserByNickNameOrEmail(conn, testNickNameOrEmail, testPassword);
//            if (userA != null)
//                System.out.print(userA.getName());
//            else
//                errorString = "No Find this User which NickName is " + testNickNameOrEmail;
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            errorString = e.getMessage();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            errorString = e.getMessage();
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
//        LinkedList<Product> productsAll;
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
