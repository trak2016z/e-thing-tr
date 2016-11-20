/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ething.controller;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.ething.model.EthingThing;
import pl.ething.model.EthingUser;
import pl.ething.repository.EthingThingRepository;
import pl.ething.repository.EthingUserRepository;

/**
 *
 * @author Koksik
 */
@org.springframework.stereotype.Controller
public class MainController {

    static final String PROFIL_HTML = "/profil/";
    static final String REGISTER_HTML = "/register";
    static final String LOGIN_HTML = "/login";
    static final String REMEMBERME_HTML = "/rememberme";
    @Autowired
    EthingUserRepository ethingUserRepository;
    @Autowired
    EthingThingRepository ethingThingRepository;

    @RequestMapping("/")
    public String homePage(HttpServletRequest request, Model model, Principal principal) {
        String mainPage = new String(request.getRequestURL().
                toString().substring(0, request.getRequestURL().
                        toString().lastIndexOf("/")));
        model.addAttribute("mainPage", mainPage);
        if (principal == null) {
            model.addAttribute("loginPage", mainPage + LOGIN_HTML);
            model.addAttribute("loginPageText", "Log-in");
        } else {
            EthingUser user = ethingUserRepository.findEthingUserByName(principal.getName());
            model.addAttribute("loginPageText", user.getName());
            model.addAttribute("loginPage", mainPage + PROFIL_HTML + user.getName());
        }
        model.addAttribute("registerPage", mainPage + REGISTER_HTML);
        return "home";
    }

    @RequestMapping(value = "/profil/{name}", method = RequestMethod.GET)
    public String profilPage(HttpServletRequest request, @PathVariable("name") String name, Model model, Principal principal) {
        EthingUser profilUser = ethingUserRepository.findEthingUserByNameAndActivation(name, "1");
        if (profilUser == null) {
            return "error";
        } else {
            String mainPage = new String(request.getRequestURL().
                    toString().substring(0, request.getRequestURL().
                            toString().lastIndexOf("/")));
            mainPage = mainPage.substring(0, mainPage.lastIndexOf("/"));

            model.addAttribute("mainPage", mainPage);
            if (principal == null) {
                model.addAttribute("loginPage", mainPage + LOGIN_HTML);
                model.addAttribute("loginPageText", "Log-in");
            } else {
                EthingUser user = ethingUserRepository.findEthingUserByName(principal.getName());
                model.addAttribute("loginPageText", user.getName());
                model.addAttribute("loginPage", mainPage + PROFIL_HTML + user.getName());
            }
            model.addAttribute("registerPage", mainPage + REGISTER_HTML);
            model.addAttribute("user", profilUser);
            return "profil";
        }
    }

    @RequestMapping("/login")
    public String loginPage(HttpServletRequest request, Model model) {
        String mainPage = new String(request.getRequestURL().
                toString().substring(0, request.getRequestURL().
                        toString().lastIndexOf("/")));
        model.addAttribute("mainPage", mainPage);
        model.addAttribute("registerPage", mainPage + REGISTER_HTML);
        model.addAttribute("rememberMePage", mainPage + REMEMBERME_HTML);
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
    public String registerPage(HttpServletRequest request, Model model, Principal principal) {
        String mainPage = new String(request.getRequestURL().
                toString().substring(0, request.getRequestURL().
                        toString().lastIndexOf("/")));
        model.addAttribute("mainPage", mainPage);
        if (principal == null) {
            model.addAttribute("loginPage", mainPage + LOGIN_HTML);
            model.addAttribute("loginPageText", "Log-in");
        } else {
            EthingUser user = ethingUserRepository.findEthingUserByName(principal.getName());
            model.addAttribute("loginPageText", user.getName());
            model.addAttribute("loginPage", mainPage + PROFIL_HTML + user.getName());
        }
        model.addAttribute("registerPage", mainPage + REGISTER_HTML);
        model.addAttribute("mainPage", mainPage);
        return "register";
    }

    @RequestMapping(value = "/thing/{hashId}", method = RequestMethod.GET)
    public String thingPage(HttpServletRequest request, @PathVariable("hashId") String hashId, Model model, Principal principal) {
        if (!"".equals(hashId)) {
            String mainPage = new String(request.getRequestURL().
                    toString().substring(0, request.getRequestURL().
                            toString().lastIndexOf("/")));
            mainPage = mainPage.substring(0, mainPage.lastIndexOf("/"));

            model.addAttribute("mainPage", mainPage);
            if (principal == null) {
                model.addAttribute("loginPage", mainPage + LOGIN_HTML);
                model.addAttribute("loginPageText", "Log-in");
            } else {
                EthingUser user = ethingUserRepository.findEthingUserByName(principal.getName());
                model.addAttribute("loginPageText", user.getName());
                model.addAttribute("loginPage", mainPage + PROFIL_HTML + user.getName());
            }
            model.addAttribute("registerPage", mainPage + REGISTER_HTML);
            EthingThing thing = ethingThingRepository.findEthingThingByIdhash(hashId);
            model.addAttribute("thing", thing);
        } else {
            return "home";
        }
        return "thing";
    }

    @RequestMapping(value = "/activation/{hashId}", method = RequestMethod.GET)
    public String activationUser(HttpServletRequest request, @PathVariable("hashId") String hashId, Model model) {
        if (!"".equals(hashId)) {
            String mainPage = new String(request.getRequestURL().
                    toString().substring(0, request.getRequestURL().
                            toString().lastIndexOf("/")));
            mainPage = mainPage.substring(0, mainPage.lastIndexOf("/"));
            model.addAttribute("mainPage", mainPage);
            model.addAttribute("loginPage", mainPage + LOGIN_HTML);
            model.addAttribute("registerPage", mainPage + REGISTER_HTML);
            EthingUser user = ethingUserRepository.findEthingUserByActivation(hashId);
            if (user != null) {
                user.setActivation("1");
                ethingUserRepository.save(user);
                return "activation";
            } else {
                return "error";
            }
        } else {
            return "error";
        }
    }
}