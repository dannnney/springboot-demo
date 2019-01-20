package com.springboot.demo.repository;

import com.springboot.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author yemingfeng
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  @Query(value = "SELECT * FROM User WHERE username = ?1", nativeQuery = true)
  UserEntity findByUsername(String username);

}
