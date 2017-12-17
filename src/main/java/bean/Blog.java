package bean;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

public class Blog {
    private String blogTitle;
    private String category;
    private String tag;
    private Date postDate;
    private String content;
    private String writer;
    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getWriter(){
        return writer;
    }

    public void setWriter(String writer){
        this.writer=writer;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPartOfContent(){
        return  content.substring(0, Math.min(200, content.length()));
    }

    public String encodeURL() throws UnsupportedEncodingException {
        return  URLEncoder.encode(blogTitle, "UTF-8");
    }

    public String[] listTags(){
        return tag.split(",");
    }
}
