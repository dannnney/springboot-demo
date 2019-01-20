package com.springboot.demo.repository;

import com.springboot.demo.entity.ArticleEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author yemingfeng
 */
@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

  @Query(value = "SELECT * FROM Article WHERE userId = ?1", nativeQuery = true)
  List<ArticleEntity> findByUserId(Long userId);

}