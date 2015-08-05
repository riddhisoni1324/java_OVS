package com.election.voter;
import com.election.voter.*; 
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;
 
import java.util.*;
 
/**
 * Simple Class to send an email using JavaMail API (javax.mail) and Gmail SMTP server
 * @author Dunith Dhanushka, dunithd@gmail.com
 * @version 1.0
 */
 
 //request smtp from less secure
public class GmailSender {
 
     static final String HOST = "smtp.gmail.com";
     static final String USER = "adharvotingsystem@gmail.com";
     static final String PASSWORD = "OVS@DMIN";
     static final String PORT = "587";
     static final String FROM = "adharvotingsystem@gmail.com";
     static  String TO ;
 
     static final String STARTTLS = "true";
     static final String AUTH = "true";
     static final String DEBUG = "true";
     static final String SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";
     static final String SUBJECT = " Aadhar Registration OTP Mail ";
     static  String TEXT ;
 
    public static synchronized void send() {
        //Use Properties object to set environment properties
        Properties props = new Properties();
 
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.user", USER);
 
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", STARTTLS);
        //props.put("mail.smtp.debug", DEBUG);
		
				props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust","smtp.gmail.com");
 
 /*
        props.put("mail.smtp.socketFactory.port", PORT);
        props.put("mail.smtp.socketFactory.class", SOCKET_FACTORY);
        props.put("mail.smtp.socketFactory.fallback", "false");
	*/	
 
        try {
 
            //Obtain the default mail session
            Session session = Session.getDefaultInstance(props, null);
            session.setDebug(true);
 
            //Construct the mail message
            MimeMessage message = new MimeMessage(session);
            message.setText(TEXT);
            message.setSubject(SUBJECT);
            message.setFrom(new InternetAddress(FROM));
            message.addRecipient(RecipientType.TO, new InternetAddress(TO));
            message.saveChanges();
 
            //Use Transport to deliver the message
            Transport transport = session.getTransport("smtp");
            transport.connect(HOST, USER, PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
			

 
        } catch (MessagingException e) {
            e.printStackTrace();
        }
 
    }
 
    public static void main(String[] args) {
       GmailSender.send();
       System.out.println("Mail sent successfully!");
    }
}