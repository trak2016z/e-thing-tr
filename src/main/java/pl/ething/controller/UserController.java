package pl.ething.controller;

import java.security.Principal;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.ething.config.ApplicationMail;
import pl.ething.model.EthingUser;
import pl.ething.repository.EthingUserRepository;

/**
 *
 * @author prographer
 */
@org.springframework.stereotype.Controller

public class UserController {

    @Autowired
    EthingUserRepository ethingUserRepository;

    @Autowired
    ApplicationMail senderMail;

    @RequestMapping(value = "/rememberPassword", method = RequestMethod.PUT)
    public @ResponseBody
    String rememberUserPassword(@RequestBody String email) throws MessagingException {
        EthingUser user = this.ethingUserRepository.findEthingUserByEmailAndActivation(email, "");
        if (user != null) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            int newPassword = Math.abs(user.getPassword().hashCode());
            String hashedPassword = passwordEncoder.encode(newPassword + "");
            user.setPassword(hashedPassword);
            this.ethingUserRepository.save(user);
            senderMail.sendEmailRememberMeUser(user, newPassword + "");
            return "message";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public @ResponseBody
    String editUser(HttpServletRequest request, @RequestBody EthingUser ethingUser, Model model, Principal principal) {
        try {
            if (ethingUser.getPassword().equals("") && !ethingUser.getName().equals("")) {
                EthingUser user = ethingUserRepository.findEthingUserByLogin(principal.getName());
                user.setName(ethingUser.getName());
                //user.setPassword(ethingUser.getPassword());
                this.ethingUserRepository.save(user);
                return "message";
            } else if (!ethingUser.getPassword().equals("") && ethingUser.getName().equals("")) {
                EthingUser user = ethingUserRepository.findEthingUserByLogin(principal.getName());
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String hashedPassword = passwordEncoder.encode(ethingUser.getPassword());
                user.setPassword(hashedPassword);
                //user.setName(ethingUser.getName());
                this.ethingUserRepository.save(user);
                return "message";
            } else {
                EthingUser user = ethingUserRepository.findEthingUserByLogin(principal.getName());
                user.setName(ethingUser.getName());
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String hashedPassword = passwordEncoder.encode(ethingUser.getPassword());
                user.setPassword(hashedPassword);
                this.ethingUserRepository.save(user);
                return "message";
            }
        } catch (Exception e) {

            return "error";
        }
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.PUT)
    public @ResponseBody
    String registerUser(HttpServletRequest request, @RequestBody EthingUser ethingUser, Model model) {
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
            this.senderMail.sendEmailActivationUser(newEthingUser, mainPage);
            return "message";
        } catch (Exception e) {

            return "error";
        }
    }

    @RequestMapping(value = "/isLogedUser", method = RequestMethod.GET)
    public @ResponseBody
    String isLogedUser(Principal principal) {
        if (principal != null) {
            return "1";
        } else {
            return "0";
        }
    }

}
