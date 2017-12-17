package utils;

import bean.Blog;
import conn.ConnectionUtils;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BlogRepositoryImpl {

    private static ArrayList<Blog> blogList;
    private static Set<String> tagSet;
    private static Set<String> categorySet;
    private static Map<String, Integer> dateMap;

    public BlogRepositoryImpl() {

    }


    public static int addBlog(Blog blogi) {
        String sql = "INSERT INTO POSTS VALUES (?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        int out = 0;
        try {
            conn = ConnectionUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, blogi.getBlogTitle());
            ps.setString(2, blogi.getWriter());
            ps.setString(3, blogi.getCategory());
            ps.setString(4, blogi.getTag());
            ps.setDate(5, new java.sql.Date(blogi.getPostDate().getTime()));
            ps.setString(6, blogi.getContent());

            out = ps.executeUpdate();
            System.out.println("create new blog.");
        } catch (Exception ex) {
            Logger.getLogger(BlogRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeEverything(conn, ps, null);
        }
        refreshCache();
        return out;
    }

    public static int deleteBlog(String blogName) {
        String sql1 = "DELETE FROM POSTS WHERE HEAD = ?";
        String sql2 = "DELETE FROM COMMENTS WHERE TITLE = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        int out = 0;
        try {
            conn = ConnectionUtils.getConnection();
            ps = conn.prepareStatement(sql1);
            ps.setString(1, blogName);
            out = ps.executeUpdate();
            ps = conn.prepareStatement(sql2);
            ps.setString(1, blogName);
            out = ps.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(BlogRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeEverything(conn, ps, null);
        }
        refreshCache();
        return out;
    }

    public static int updateBlog(Blog blog, String oldhead) {

        String sql = "UPDATE POSTS SET HEAD=?,WRITER=?,CATEGORY=?,TAG=?,POSTDATE=?,CONTENT=? WHERE HEAD=?";

        PreparedStatement ps = null;
        Connection conn = null;
        int out = 0;
        try {
            conn = ConnectionUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, blog.getBlogTitle());
            ps.setString(2, blog.getWriter());
            ps.setString(3, blog.getCategory());
            ps.setString(4, blog.getTag());
            ps.setDate(5, new java.sql.Date(blog.getPostDate().getTime()));
            ps.setString(6, blog.getContent());
            ps.setString(7, oldhead);

            out = ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(BlogRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeEverything(conn, ps, null);
        }
        refreshCache();
        return out;
    }

    public static List<Blog> ListAllBlog() {
        blogList = new ArrayList<Blog>();
        Blog blog;
        String sql = "SELECT * FROM POSTS ORDER BY POSTDATE DESC";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int out = 0;
        try {
            conn = ConnectionUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                blog = new Blog();
                blog.setBlogTitle(rs.getString(1));
                blog.setWriter(rs.getString(2));
                blog.setCategory(rs.getString(3));
                blog.setTag(rs.getString(4));
                blog.setPostDate(rs.getDate(5));
                blog.setContent(rs.getString(6));
                blogList.add(blog);

            }
        } catch (Exception ex) {
            Logger.getLogger(BlogRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeEverything(conn, ps, rs);
        }
        return blogList;
    }

    public static void deleteComment(String writer, String title, String comment) {
        String sql = "delete from comments where writer =? and title=? and texts like ? LIMIT 1";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectionUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, writer);
            ps.setString(2, title);
            ps.setString(3, '%' + comment + '%');
            System.out.println("|" + comment + "|");
            ps.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(BlogRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeEverything(conn, ps, null);
        }
    }

    public static void addComment(String writer, String title, String comment) {
        String sql = "INSERT INTO COMMENTS VALUES (?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        long a = new Date().getTime();
        try {
            conn = ConnectionUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, writer);
            ps.setString(2, title);
            ps.setString(3, comment);
            ps.setLong(4, a);
            ps.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(BlogRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeEverything(conn, ps, null);
        }
    }

    public static List<String> getCommentsByTitle(String title) {
        List<String> list = new ArrayList<String>();
        String sql = "SELECT WRITER,TEXTS FROM COMMENTS WHERE TITLE = ? ORDER BY NUM DESC";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnectionUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1) + "|" + rs.getString(2));
            }
        } catch (Exception e) {
            Logger.getLogger(BlogRepositoryImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeEverything(conn, ps, rs);
        }
        return list;
    }


    public static Blog getBlogByTitle(String title) {
        Blog blog = new Blog();
        String sql = "SELECT * FROM POSTS WHERE HEAD = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnectionUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            rs = ps.executeQuery();
            while (rs.next()) {
                blog = new Blog();
                blog.setBlogTitle(rs.getString(1));
                blog.setWriter(rs.getString(2));
                blog.setCategory(rs.getString(3));
                blog.setTag(rs.getString(4));
                blog.setPostDate(rs.getDate(5));
                blog.setContent(rs.getString(6));
            }
        } catch (Exception ex) {
            Logger.getLogger(BlogRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeEverything(conn, ps, rs);
        }
        return blog;
    }

    public static List listBlogByKey(String keyword) {
        List<Blog> list = new ArrayList<Blog>();
        Blog blog;
        String sql = "SELECT * FROM POSTS WHERE HEAD LIKE ? ORDER BY POSTDATE DESC";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int out = 0;
        try {
            conn = ConnectionUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                blog = new Blog();
                blog.setBlogTitle(rs.getString(1));
                blog.setWriter(rs.getString(2));
                blog.setCategory(rs.getString(3));
                blog.setTag(rs.getString(4));
                blog.setPostDate(rs.getDate(5));
                blog.setContent(rs.getString(6));
                list.add(blog);
            }
        } catch (Exception ex) {
            Logger.getLogger(BlogRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeEverything(conn, ps, rs);
        }
        return list;
    }

    public static List listBlogByContent(String keyword) {
        List<Blog> list = new ArrayList<Blog>();
        Blog blog;
        String sql = "SELECT * FROM POSTS WHERE CONTENT LIKE ? ORDER BY POSTDATE DESC";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        int out = 0;
        try {
            conn = ConnectionUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                blog = new Blog();
                blog.setBlogTitle(rs.getString(1));
                blog.setWriter(rs.getString(2));
                blog.setCategory(rs.getString(3));
                blog.setTag(rs.getString(4));
                blog.setPostDate(rs.getDate(5));
                blog.setContent(rs.getString(6));
                list.add(blog);

            }
        } catch (Exception ex) {
            Logger.getLogger(BlogRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeEverything(conn, ps, rs);
        }
        return list;
    }

    public static Set listAllTags() {
        tagSet = new HashSet<String>();
        String sql = "SELECT TAG FROM POSTS ";
        Connection conn = null;
        Statement ss = null;
        ResultSet rs = null;
        try {
            conn = ConnectionUtils.getConnection();
            ss = conn.createStatement();
            rs = ss.executeQuery(sql);
            while (rs.next()) {
                String tags = rs.getString(1);
                String[] Arraytags = tags.split(",");
                tagSet.addAll(Arrays.asList(Arraytags));

            }

        } catch (Exception ex) {
            Logger.getLogger(BlogRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeEverything(conn, ss, rs);
        }
        return tagSet;
    }

    public static List listBlogByWriter(String writer) {
        List<Blog> list = new ArrayList<Blog>();
        Blog blog;
        String sql = "SELECT * FROM POSTS WHERE WRITER = ? ORDER BY POSTDATE DESC";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int out = 0;
        try {
            conn = ConnectionUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, writer);

            rs = ps.executeQuery();
            while (rs.next()) {
                blog = new Blog();
                blog.setBlogTitle(rs.getString(1));
                blog.setWriter(rs.getString(2));
                blog.setCategory(rs.getString(3));
                blog.setTag(rs.getString(4));
                blog.setPostDate(rs.getDate(5));
                blog.setContent(rs.getString(6));
                list.add(blog);
            }
        } catch (Exception ex) {
            Logger.getLogger(BlogRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeEverything(conn, ps, rs);
        }
        return list;
    }

    public static List listBlogByTag(String keyword) {
        List<Blog> list = new ArrayList<Blog>();
        Blog blog;
        String sql = "SELECT * FROM POSTS WHERE TAG LIKE ? ORDER BY POSTDATE DESC";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int out = 0;
        try {
            conn = ConnectionUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                blog = new Blog();
                blog.setBlogTitle(rs.getString(1));
                blog.setWriter(rs.getString(2));
                blog.setCategory(rs.getString(3));
                blog.setTag(rs.getString(4));
                blog.setPostDate(rs.getDate(5));
                blog.setContent(rs.getString(6));
                list.add(blog);
            }
        } catch (Exception ex) {
            Logger.getLogger(BlogRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeEverything(conn, ps, rs);
        }
        return list;

    }

    public static List listBlogByDate(String keyword) {
        List<Blog> list = new ArrayList<Blog>();
        Blog blog;
        String sql = "SELECT * FROM POSTS WHERE POSTDATE LIKE ? ORDER BY POSTDATE DESC";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int out = 0;
        try {
            conn = ConnectionUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                blog = new Blog();
                blog.setBlogTitle(rs.getString(1));
                blog.setWriter(rs.getString(2));
                blog.setCategory(rs.getString(3));
                blog.setTag(rs.getString(4));
                blog.setPostDate(rs.getDate(5));
                blog.setContent(rs.getString(6));
                list.add(blog);
            }
        } catch (Exception ex) {
            Logger.getLogger(BlogRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeEverything(conn, ps, rs);
        }
        return list;

    }


    public static Set listAllCategory() {
        categorySet = new HashSet<String>();
        String sql = "SELECT CATEGORY FROM POSTS";
        Connection conn = null;
        Statement ss = null;
        ResultSet rs = null;
        try {
            conn = ConnectionUtils.getConnection();
            ss = conn.createStatement();
            rs = ss.executeQuery(sql);
            while (rs.next()) {
                String category = rs.getString(1);
                categorySet.add(category);
            }

        } catch (Exception ex) {
            Logger.getLogger(BlogRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeEverything(conn, ss, rs);
        }
        return categorySet;
    }

    public static List listBlogByCategory(String keyword) {
        List<Blog> list = new ArrayList<Blog>();
        Blog blog;
        String sql = "SELECT * FROM POSTS WHERE CATEGORY LIKE ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int out = 0;
        try {
            conn = ConnectionUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                blog = new Blog();
                blog.setBlogTitle(rs.getString(1));
                blog.setWriter(rs.getString(2));
                blog.setCategory(rs.getString(3));
                blog.setTag(rs.getString(4));
                blog.setPostDate(rs.getDate(5));
                blog.setContent(rs.getString(6));
                list.add(blog);

            }
        } catch (Exception ex) {
            Logger.getLogger(BlogRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeEverything(conn, ps, rs);
        }
        return list;

    }

    private static void fillDateMap() {
        String sql = "SELECT  POSTDATE,COUNT(POSTDATE) FROM POSTS GROUP BY POSTDATE";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        dateMap = new HashMap<String, Integer>();
        try {
            conn = ConnectionUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                dateMap.put(rs.getString(1), rs.getInt(2));

            }
        } catch (Exception ex) {
            Logger.getLogger(BlogRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeEverything(conn, ps, rs);
        }
    }

    public static void test() {
        for (Map.Entry<String, Integer> dd : dateMap.entrySet()) {
            dd.getKey();
            dd.getValue();
        }
    }

    public static void refreshCache() {
        ListAllBlog();
        listAllTags();
        listAllCategory();
        fillDateMap();

    }

    public static ArrayList<Blog> getBlogList() {
        return blogList;
    }

    public static Set<String> getTagSet() {
        return tagSet;
    }

    public static Set<String> getCategorySet() {
        return categorySet;
    }

    public static Map<String, Integer> getDateMap() {
        return dateMap;
    }

    private static void closeEverything(Connection conn, Statement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
            }
        }

    }
}
