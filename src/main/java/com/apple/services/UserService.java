package com.apple.services;

import com.apple.domain.Users;
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

    public boolean save(Users user){
        if (user.isReal()){
            try {
                usersRepository.save(user);
                logger.info("===========登记成功==========");
                logger.info("用户信息--> 用户名: "+user.getUsername()+" 密码: "+user.getPassword());
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
        return false;
    }
}