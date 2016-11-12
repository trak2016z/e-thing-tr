/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ething.config;

import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.ething.model.EthingUser;

/**
 *
 * @author Koksik
 */
@Service
public class ApplicationMail {

    private String SenderEmail = "virus268268@gmail.com";

    private JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setProtocol("smtp");
        sender.setHost("smtp.gmail.com");
        sender.setPort(587);
        sender.setUsername(this.SenderEmail);
        sender.setPassword("killman268");

        Properties mailProps = new Properties();
        mailProps.put("mail.smtps.auth", "true");
        mailProps.put("mail.smtp.starttls.enable", "true");
        mailProps.put("mail.smtp.debug", "true");

        sender.setJavaMailProperties(mailProps);

        return sender;
    }

    public void sendEmailRememberMeUser(EthingUser ethingUser, String password) throws MessagingException {
        MimeMessage message = this.getJavaMailSender().createMimeMessage();
        message.setSubject("Remember password in E-thing");
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(message, false, "utf-8");
        String msg = "This is your new temporary:\n"+password+"\nPlease login and change your password.";
        message.setContent(msg, "text/html");
        helper.setFrom(this.SenderEmail);
        helper.setTo(ethingUser.getEmail());
        this.getJavaMailSender().send(message);
    }

    public void sendEmailActivationUser(EthingUser ethingUser, String mainPage) throws MessagingException {
        MimeMessage message = this.getJavaMailSender().createMimeMessage();
        message.setSubject("Complete your registration E-thing");
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(message, false, "utf-8");
        String link = mainPage + "/activation/" + ethingUser.getActivation();
        String msg = "Click this link complete your registration.<p><a href=\"" + link + "\">Click to activate your account</a></p>";
        message.setContent(msg, "text/html");
        helper.setFrom(this.SenderEmail);
        helper.setTo(ethingUser.getEmail());
        //helper.setText(msg, true);
        this.getJavaMailSender().send(message);
    }
}
