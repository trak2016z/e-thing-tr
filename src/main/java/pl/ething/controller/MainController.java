/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ething.controller;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import pl.ething.model.EthingUser;
import pl.ething.repository.EthingUserRepository;

/**
 *
 * @author Koksik
 */
@org.springframework.stereotype.Controller
public class MainController {

    static final String REGISTER_HTML = "/register";
    static final String LOGIN_HTML = "/login";
    static final String REMEMBERME_HTML = "/rememberme";
    @Autowired
    EthingUserRepository ethingUserRepository;

    @RequestMapping("/")
    public String homePage(HttpServletRequest request, Model model) {
        String mainPage = new String(request.getRequestURL().
                toString().substring(0, request.getRequestURL().
                        toString().lastIndexOf("/")));
        model.addAttribute("mainPage", mainPage);
        model.addAttribute("loginPage", mainPage + LOGIN_HTML);
        model.addAttribute("registerPage", mainPage + REGISTER_HTML);
        return "home";
    }

    @RequestMapping("/{name}/profil")
    public String profilPage(@PathVariable("name") String name, Model model) {
        EthingUser user = ethingUserRepository.findEthingUserByName(name);
        model.addAttribute("user", user);
        return "profil";
    }

    @RequestMapping("/login")
    public String loginPage(HttpServletRequest request) {
        return "login";
    }

    @RequestMapping("/rememberme")
    public String rememberMePage(HttpServletRequest request, Model model) {
        String mainPage = new String(request.getRequestURL().
                toString().substring(0, request.getRequestURL().
                        toString().lastIndexOf("/")));
        model.addAttribute("mainPage", mainPage);
        model.addAttribute("loginPage", mainPage + LOGIN_HTML);
        model.addAttribute("registerPage", mainPage + REGISTER_HTML);
        model.addAttribute("rememberMePage", mainPage + REMEMBERME_HTML);
        return "rememberme";
    }

    @RequestMapping("/register")
    public String registerPage(HttpServletRequest request, Model model) {
        String mainPage = new String(request.getRequestURL().
                toString().substring(0, request.getRequestURL().
                        toString().lastIndexOf("/")));
        model.addAttribute("mainPage", mainPage);
        model.addAttribute("loginPage", mainPage + LOGIN_HTML);
        model.addAttribute("registerPage", mainPage + REGISTER_HTML);
        return "register";
    }

    @RequestMapping(value = "/activation/{hashId}", method = RequestMethod.GET)
    public String activationUser(@PathVariable("hashId") String hashId, Model model) {
        if (hashId != "") {
            EthingUser user = ethingUserRepository.findEthingUserByActivation(hashId);
            user.setActivation("1");
            ethingUserRepository.save(user);
            return "activation";
        } else {
            return "home";
        }
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody
    String test() {

        return "test";

    }
    /*@RequestMapping("/error")
    public String error(HttpServletRequest request, Model model) {
        String mainPage = new String(request.getRequestURL().
                toString().substring(0, request.getRequestURL().
                        toString().lastIndexOf("/")));
        model.addAttribute("mainPage", mainPage);
        model.addAttribute("loginPage", mainPage + LOGIN_HTML);
        model.addAttribute("registerPage", mainPage + REGISTER_HTML);
        return "error";
    }*/
}
