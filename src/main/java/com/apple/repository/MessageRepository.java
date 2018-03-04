package com.apple.repository;

import com.apple.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * MessageRepository
 */
@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {

    Message findByPhone(String phone);
    Message findByUuid(String uuid);

    
}