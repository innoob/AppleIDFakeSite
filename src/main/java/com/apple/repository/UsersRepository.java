package com.apple.repository;

import com.apple.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UsersRepository
 */
@Repository
public interface UsersRepository extends JpaRepository<Users,String> {

    

}