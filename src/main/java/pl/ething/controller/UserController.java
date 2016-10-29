/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ething.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @RequestMapping(value = "/registerUser" , method = RequestMethod.PUT)
    public String registerUser(@RequestBody EthingUser ethingUser) {
        EthingUser newEthingUser = new EthingUser();
        newEthingUser.setId(new Long(0));
        newEthingUser.setLogin(ethingUser.getLogin());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(ethingUser.getPassword());
        newEthingUser.setPassword(hashedPassword);
        newEthingUser.setName(ethingUser.getName());
        newEthingUser.setEmail(ethingUser.getEmail());
        newEthingUser.setEthingThingSet(null);
        newEthingUser.setRole("USER");
        this.ethingUserRepository.save(newEthingUser);
        return "message";
    }
}
