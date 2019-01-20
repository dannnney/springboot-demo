package com.springboot.demo.repository;

import com.springboot.demo.entity.StarEntity;
import com.springboot.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yemingfeng
 */
@Repository
public interface StarRepository extends JpaRepository<StarEntity, Long> {

  Long countByTypeAndContentId(String type, Long contentId);

  StarEntity findByUserAndTypeAndContentId(UserEntity user, String type, Long contentId);

  void deleteByUserAndTypeAndContentId(UserEntity user, String type, Long contentId);

}