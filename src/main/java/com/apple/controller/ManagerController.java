package com.apple.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.apple.domain.Manager;
import com.apple.domain.ManagerProperties;
import com.apple.domain.Message;
import com.apple.domain.Users;
import com.apple.services.ManagerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
    private ManagerService managerService;

    @RequestMapping(value="/charge",method=RequestMethod.POST)
    @ResponseBody
    public String managerLogin(Manager manager,HttpServletRequest request){
        request.getSession().setAttribute("currentManager", null);
        if (managerService.checkStatu(manager)){
            request.getSession().setAttribute("currentManager", manager);
            logger.info("管理员登录成功 时间:"+new Date().toLocaleString());
            return "{\"msg\":\"success\"}";
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

    @RequestMapping(value = "/sendmessage",method=RequestMethod.GET)
    public String sendMessege(HttpServletRequest request){
        Manager manager = (Manager) request.getSession().getAttribute("currentManager");
        if (null!=manager){
            return "sendMessage";
        }
        return "managerLogin";
    }

    @RequestMapping(value="/send",method=RequestMethod.POST)
    @ResponseBody
    public Message message(String phone,HttpServletRequest request){
        Manager manager = (Manager) request.getSession().getAttribute("currentManager");
        if (null!=manager){
            return managerService.createMessage(phone);
        }
        return null;
    }

    @RequestMapping(value="/user/all",method=RequestMethod.GET)
    @ResponseBody
    public List<Users> findAllUser(HttpServletRequest request){
        Manager manager = (Manager) request.getSession().getAttribute("currentManager");
        if(null!=manager){
            return managerService.findAllUsers(manager);
        }
        return null;
    }

    @RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
    public String deleteUsers(@PathVariable("id") String id,HttpServletRequest request){
        Manager manager = (Manager) request.getSession().getAttribute("currentManager");
        if(null!=manager){
            if(null!=id&&!"".equals(id)){
                if(managerService.deleteUsers(manager, Integer.valueOf(id))){
                    return "manager";
                }
            }
        }
        return "managerLogin";
    }

}