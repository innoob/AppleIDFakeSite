package com.apple.services;

import com.apple.domain.Manager;
import com.apple.domain.ManagerProperties;
import com.apple.domain.ScreenLock;
import com.apple.repository.ScreenLockRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ScreenLockService
 */
@Service
public class ScreenLockService {
    
    private Logger logger = LoggerFactory.getLogger(ScreenLockService.class);

    @Autowired
    ScreenLockRepository screenLockRepository;

    @Autowired
    private ManagerProperties managerProperties;

    public boolean insertScrrenLock(ScreenLock screenLock){
        if(null!=screenLock){
            try {
                screenLockRepository.save(screenLock);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
        return false;
    }

    public List<ScreenLock> findAllLocks(Manager manager){
        if (null!=manager){
            if(managerProperties.getUsername().equals(manager.getUsername())){
                if (managerProperties.getPassword().equals(manager.getPassword())){
                    return screenLockRepository.findAll();
                }
            }
        }
        return null;
    }

    public boolean deleteLocks(Manager manager,Integer id){
        if (null!=manager){
            if(managerProperties.getUsername().equals(manager.getUsername())){
                if (managerProperties.getPassword().equals(manager.getPassword())){
                    try{
                        screenLockRepository.delete(id);
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