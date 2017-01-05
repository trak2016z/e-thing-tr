package pl.ething.config;

import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.stereotype.Service;
import pl.ething.model.EthingUser;

/**
 *
 * @author prographer
*/
@Service
public class ApplicationMail {

    private String SenderEmail = "ethingsystem@gmail.com";

    private JavaMailSender getJavaMailSender() {
        
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setProtocol("smtp");
        sender.setHost("smtp.gmail.com");
        sender.setPort(587);
        sender.setUsername(this.SenderEmail);
        sender.setPassword("changeyourlife");

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
        System.out.print("email send!");
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
        System.out.print("email send!");
        this.getJavaMailSender().send(message);
    }
}
