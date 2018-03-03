package com.apple.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.apple.domain.Manager;
import com.apple.domain.ManagerProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ManagerController
 */
@Controller
public class ManagerController {

    private Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    private ManagerProperties managerProperties;

    @RequestMapping(value="/charge",method=RequestMethod.POST)
    @ResponseBody
    public String managerLogin(Manager manager,HttpServletRequest request){
        request.getSession().setAttribute("currentManager", null);
        if (manager.isReal()){
            if (this.managerProperties.getUsername().equals(manager.getUsername())){
                if (this.managerProperties.getPassword().equals(manager.getPassword())){
                    request.getSession().setAttribute("currentManager", manager);
                    logger.info("管理员登录成功 时间:"+new Date().toLocaleString());
                    return "{\"msg\":\"success\"}";
                }
            }
        }
        return "{\"msg\":\"faild\"}";
    }
    
    @RequestMapping(value="/charger",method=RequestMethod.GET)
    public String locationRedirect(HttpServletRequest request){
        Manager manager = (Manager) request.getSession().getAttribute("currentManager");
        if (manager!=null){
            logger.info("currentManager:"+manager.getUsername());
            return "manager";
        }
        return "managerLogin";
    }
}