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

    @RequestMapping(value = "/login/{uuid}",method = RequestMethod.GET)
    public String userLogin(@PathVariable("uuid") String uuid,HttpServletRequest request){
        Users user =  userService.findUserByUUID(uuid);
        if (null!=user){
            logger.info("UUID: "+user.getUuid()+" 电话: "+user.getPhone());
            request.getSession().setAttribute("currentUser", user);
            return "login";
        }
        return "error";
    }

    @RequestMapping(value = "/signin",method = RequestMethod.POST)
    public String signin(Users user,HttpServletRequest request){
        Users current = (Users) request.getSession().getAttribute("currentUser");
        if (current!=null){
            if (userService.save(current,user)){
                request.getSession().setAttribute("firstLogin", user);
                return "login";
            }
        }
        return "error";
    }

}