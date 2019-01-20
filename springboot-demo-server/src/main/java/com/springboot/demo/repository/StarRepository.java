package com.springboot.demo.repository;

import com.springboot.demo.entity.StarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yemingfeng
 */
@Repository
public interface StarRepository extends JpaRepository<StarEntity, Long> {

  Long countByTypeAndContentId(String type, Long contentId);

  StarEntity findByUserIdAndTypeAndContentId(Long userId, String type, Long contentId);

  void deleteByUserIdAndTypeAndContentId(Long userId, String type, Long contentId);

}