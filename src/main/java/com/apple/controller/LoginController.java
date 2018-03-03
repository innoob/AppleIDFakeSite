package com.apple.controller;
import javax.servlet.http.HttpServletRequest;
import com.apple.domain.Manager;
import com.apple.domain.Users;
import com.apple.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * LoginController
 */
@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userLogin/{uuid}",method = RequestMethod.GET)
    public String userLogin(@PathVariable("uuid") String uuid,HttpServletRequest request){
        if (null!=uuid){
            System.out.println("UUID: "+uuid);
            request.getSession().setAttribute("currentUUID", uuid);
            return "index.html";
        }
        return "error.html";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("user") Users user,HttpServletRequest request){
        if (request.getSession().getAttribute("firstLogin")==null) {
            if (userService.save(user)){
                request.getSession().setAttribute("firstLogin", user);
                return "faild.html";
            }
        }
        return "error.html";
    }

}