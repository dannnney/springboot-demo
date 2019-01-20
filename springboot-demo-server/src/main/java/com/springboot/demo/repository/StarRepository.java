package com.springboot.demo.repository;

import com.springboot.demo.entity.StarEntity;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author yemingfeng
 */
@Repository
public interface StarRepository extends JpaRepository<StarEntity, Long> {

  @Query(value = "SELECT COUNT(1) FROM Star WHERE `type` = ?1 AND contentId = ?2",
      nativeQuery = true)
  Long countByTypeAndContentId(String type, Long contentId);

  @Query(value = "SELECT * FROM Star WHERE userId = ?1 AND `type` = ?2 AND contentId = ?3",
      nativeQuery = true)
  StarEntity findByUserIdAndTypeAndContentId(Long userId, String type, Long contentId);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM Star WHERE userId = ?1 AND `type` = ?2 AND contentId = ?3",
      nativeQuery = true)
  void deleteByUserIdAndTypeAndContentId(Long userId, String type, Long contentId);

}