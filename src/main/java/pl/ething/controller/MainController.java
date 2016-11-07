/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ething.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Koksik
 */
@org.springframework.stereotype.Controller
public class MainController {

    static final String REGISTER_HTML = "/register";
    static final String LOGIN_HTML = "/login";

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

    @RequestMapping("/{hashId}/profil")
    public String profilPage(@PathVariable("hashId") String hashId, Model model) {
        model.addAttribute("hashId", hashId);
        return "profil";
    }

    @RequestMapping("/login")
    public String loginPage(HttpServletRequest request, Model model) {
        String mainPage = new String(request.getRequestURL().
                toString().substring(0, request.getRequestURL().
                        toString().lastIndexOf("/")));
        model.addAttribute("mainPage", mainPage);
        model.addAttribute("loginPage", mainPage + LOGIN_HTML);
        model.addAttribute("registerPage", mainPage + REGISTER_HTML);
        return "login";
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

}
