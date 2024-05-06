package com.abc.userservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abc.userservice.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    
}
