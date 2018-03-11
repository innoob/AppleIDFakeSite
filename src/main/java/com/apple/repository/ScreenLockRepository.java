package com.apple.repository;

import org.springframework.stereotype.Repository;

import com.apple.domain.ScreenLock;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ScreenLockRepository
 */
@Repository
public interface ScreenLockRepository extends JpaRepository<ScreenLock,Integer> {

    
    
}