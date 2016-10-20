/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ething.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Koksik
 */
@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/")
    String home() {
        return "home";
    }
}
