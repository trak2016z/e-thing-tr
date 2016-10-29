/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ething.controller;

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

    @RequestMapping("/")
    public String homePage() {
        return "home";
    }

    @RequestMapping("/{hashId}/profil")
    public String profilPage(@PathVariable("hashId") String hashId, Model model) {   
        model.addAttribute("hashId", hashId);
        return "profil";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/register")
    public String registerPage() {
        return "register";
    }
}
