package com.aiggo.common.util;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

import org.apache.commons.lang.StringUtils;

import java.security.Security;
import java.util.Date;
import java.util.Properties;

import lombok.extern.log4j.Log4j;

/**
 * Created by zhang.c on 15-12-7.
 */
@Log4j
public class SendEmailUtil {

    //服务器
    private static String host;
    //协议
    private static String protocol;
    private static String auth;

    //发送方
    private static String from;
    private static String password;

    // 收件人
    private static String recipient;


    @SuppressWarnings("restriction")
	public static void sendEmail(String filePath,String subject,String content) throws Exception{
        Properties properties = new Properties();

        //启动ssl
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.transport.protocol", protocol);
        properties.put("mail.smtp.auth", auth);
        properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.starttls.enable","true");
//        properties.put("mail.smtp.ssl.socketFactory", sf);

        //创建session  链接服务器
        Session session = Session.getInstance(properties);

        //debug模式，发送邮件过程可以在控制台看到发送过程
//        session.setDebug(true);

        //得到Transport对象
        Transport transport = session.getTransport();

        //使用邮箱的帐号密码链接上服务器
        transport.connect(host, from, password);

        //分割files路径
        String[] files = splitPath(filePath);

        //创建邮件
        Message message = createMail(session,files,subject,content);

        if(message == null){
            transport.close();
            return;
        }

        //设置发邮时间
        message.setSentDate(new Date());

        //设置主题
        message.setSubject(subject);

        //发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        log.info(String.format("邮件发送完毕：targer[%s] context[%s]", recipient, content));
    }

    private static String[] splitPath(String filePath) {
        if (StringUtils.isBlank(filePath)){
            return null;
        }
        String[] files = filePath.split(",");
        return files;
    }

    private static Message createMail(Session session, String[] files, String subject, String content) throws Exception{
        MimeMessage mimeMessage = new MimeMessage(session);

        //设置邮件信息
        //发件人
        mimeMessage.setFrom(new InternetAddress(from));

        //收件人(群发)
        Address[] tos = null;
        String[] receivers = recipient.trim().split(",");
        if(receivers.length <= 0) {
            return null;
        }
        if (receivers[0] != "" && receivers[0] != null) {
            Address to = new InternetAddress(receivers[0]);
            mimeMessage.setRecipient(Message.RecipientType.TO, to);
        }
        if (receivers.length > 1){
            tos = new Address[receivers.length-1];

            //为每个邮件接收者创建一个地址
            for (int i=1;i<receivers.length;i++){
                if (receivers[i] != "" && receivers[i] != null) {
                    tos[i - 1] = new InternetAddress(receivers[i]);
                }
            }
            mimeMessage.setRecipients(Message.RecipientType.CC,tos);
        }

        //邮件标题
        mimeMessage.setSubject(subject);

        //创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
        MimeBodyPart text = new MimeBodyPart();
        text.setContent(content, "text/html;charset=UTF-8");

        MimeMultipart mp = new MimeMultipart();
        
        if(files != null){
        	
        	 //创建邮件附件
            MimeBodyPart attach;
            FileDataSource fileDataSource;
            for (int j = 0; j <files.length; j++) {
                attach = new MimeBodyPart();
                fileDataSource = new FileDataSource(files[j]);
                attach.setDataHandler(new DataHandler(fileDataSource));
                try {
                    attach.setFileName(MimeUtility.encodeText(fileDataSource.getName()));
                }catch (Exception e){
                    e.printStackTrace();
                }
                mp.addBodyPart(attach);
            }
        	
        }
        //创建容器描述数据关系
        mp.addBodyPart(text);
        mp.setSubType("mixed");

        mimeMessage.setContent(mp);
        mimeMessage.saveChanges();

        //将创建的Email写入到E盘存储
//        mimeMessage.writeTo(new FileOutputStream("E:\\attachMail.eml"));

        //返回生成的邮件
        return mimeMessage;
    }


    public static void setHost(String host) {
        SendEmailUtil.host = host;
    }

    public static void setProtocol(String protocol) {
        SendEmailUtil.protocol = protocol;
    }

    public static void setAuth(String auth) {
        SendEmailUtil.auth = auth;
    }

    public static void setFrom(String from) {
        SendEmailUtil.from = from;
    }

    public static void setPassword(String password) {
        SendEmailUtil.password = password;
    }

    public static void setRecipient(String recipient) {
        SendEmailUtil.recipient = recipient;
    }


    public static void main(String[] args) throws Exception {
/*        host = "smtp.qq.com";
        protocol = "smtp";
        auth = "true";
        from = "zhangchao@qding.me";
        password = "abc123c";
        recipient = "zhangchao@qding.me,641341067@qq.com";
        sendEmail("/home/pdf/month.pdf","subject","content");*/
    }
}
