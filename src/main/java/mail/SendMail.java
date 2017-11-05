package mail;

import bean.UserInfo;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail implements Runnable{
    private String from = "18742013413@139.com";
    private String userName = "18742013413";
    private String passWord = "0203051410";
    private String host = "smtp.139.com";
    private String URL = "http://localhost:8080//";
    private int type;
    private UserInfo userInfo;
    public SendMail(UserInfo userInfo, int type){
        this.userInfo=userInfo;
        this.type=type;
    }
    public Message createEmail(Session session, UserInfo userInfo, int type) throws Exception{
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(userInfo.getEmail()));
       if(type==0){
           message.setSubject("用户注册邮件");
           String info = "<p>尊敬的 "+userInfo.getNickName()+",您好 O(∩_∩)O~~</p>恭喜您注册成功！";
           message.setContent(info, "text/html;charset=UTF-8");
           message.saveChanges();
       }
       else if(type==1){
           message.setSubject("密码找回邮件");
           String info = "<p>尊敬的 "+userInfo.getNickName()+",您好 O(∩_∩)O~~</p>您的密码为："+userInfo.getPassword();
           message.setContent(info, "text/html;charset=UTF-8");
           message.saveChanges();
       }
        return message;
    }
    public void run() {
        try{
            Properties prop = new Properties();
            prop.setProperty("mail.host",host);
            prop.setProperty("mail.transport.protocol","smtp");
            prop.setProperty("mail.smtp.auth","true");
            Session session = Session.getInstance(prop);
            session.setDebug(true);
            Transport transport = session.getTransport();
            transport.connect(host,userName,passWord);
            Message message = createEmail(session,userInfo,type);
            transport.sendMessage(message,message.getAllRecipients());
            transport.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
