package com.apple.controller;
import com.apple.domain.ManagerProperties;
import com.apple.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * LoginController
 */
@Controller
@EnableAutoConfiguration
public class LoginController {

    @RequestMapping(value = "/userLogin",method = RequestMethod.GET)
    public String userLogin(String uuid){
        return "index.html";


    }

    
}