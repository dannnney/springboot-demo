package com.springboot.demo.repository;

import com.springboot.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yemingfeng
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByUsername(String username);

}
