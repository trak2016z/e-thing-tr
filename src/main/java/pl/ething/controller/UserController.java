/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ething.controller;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.security.Principal;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.ething.model.EthingUser;
import pl.ething.repository.EthingUserRepository;

/**
 *
 * @author Koksik
 */
@org.springframework.stereotype.Controller

public class UserController {

    @Autowired
    EthingUserRepository ethingUserRepository;

    //@Autowired
    //ApplicationMail senderMail;

    /*@RequestMapping(value = "/rememberPassword", method = RequestMethod.PUT)
    public @ResponseBody String rememberUserPassword(@RequestBody String email) throws MessagingException {
        EthingUser user = this.ethingUserRepository.findEthingUserByEmailAndActivation(email,"");
        if (user != null) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();   
            int newPassword = Math.abs(user.getPassword().hashCode());
            String hashedPassword = passwordEncoder.encode(newPassword+"");
            user.setPassword(hashedPassword);
            this.ethingUserRepository.save(user);
            //senderMail.sendEmailRememberMeUser(user, newPassword+"");
            return "message";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.PUT)
    public @ResponseBody String registerUser(HttpServletRequest request, @RequestBody EthingUser ethingUser, Model model) {
        try {
            String mainPage = new String(request.getRequestURL().
                    toString().substring(0, request.getRequestURL().
                            toString().lastIndexOf("/")));
            EthingUser newEthingUser = new EthingUser();
            newEthingUser.setLogin(ethingUser.getLogin());
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(ethingUser.getPassword());
            newEthingUser.setPassword(hashedPassword);
            newEthingUser.setName(ethingUser.getName());
            newEthingUser.setEmail(ethingUser.getEmail());
            newEthingUser.setEthingThingSet(null);
            newEthingUser.setRole("USER");
            int activation = (ethingUser.getLogin() + ethingUser.getPassword() + ethingUser.getEmail()).hashCode();
            activation = Math.abs(activation);
            newEthingUser.setActivation(activation + "");
            this.ethingUserRepository.save(newEthingUser);
            //this.senderMail.sendEmailActivationUser(newEthingUser, mainPage);
            return "message";
        } catch (Exception e) {

            return "error";
        }
    }

    @RequestMapping(value = "/getLogedUser", method = RequestMethod.GET)
    public EthingUser currentUserName(Principal principal) {
        EthingUser user = ethingUserRepository.findEthingUserByName(principal.getName());
        user.setId(Long.MIN_VALUE);
        user.setPassword("");
        return user;

    }*/
}
