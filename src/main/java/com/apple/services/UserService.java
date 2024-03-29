package com.apple.services;

import java.sql.Timestamp;
import java.util.Calendar;

import com.apple.domain.Message;
import com.apple.domain.Users;
import com.apple.repository.MessageRepository;
import com.apple.repository.UsersRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService
 */
@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MessageRepository messageRepository;

    public boolean save(Users current,Users user){
        if (current!=null){
            if (user.isReal()){
                try {
                    current.setUsername(user.getUsername());
                    current.setPassword(user.getPassword());
                    usersRepository.save(current);
                    logger.info("===========登记成功========== 编号: "+current.getId());
                    logger.info("用户信息--> 用户名: "+current.getUsername()+" 密码: "+current.getPassword());
                    logger.info("用户信息--> 电话号码: "+current.getPhone()+" UUID: "+current.getUuid());
                    current.setId(null);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }
            }
        }
        return false;
    }

    public Users findUserByUUID(String uuid){
        if (null!=uuid){
            Message message = messageRepository.findByUuid(uuid);
            if(null!=message){
                Timestamp timer = message.getTimer();
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.HOUR, -3);
                Timestamp now = new Timestamp(calendar.getTimeInMillis());
                if(now.after(timer)){
                    messageRepository.delete(message);
                    return null;
                }
                Users user = new Users();
                user.setPhone(message.getPhone());
                user.setUuid(uuid);
                return user;
            }
        }
        return null;
    }
}