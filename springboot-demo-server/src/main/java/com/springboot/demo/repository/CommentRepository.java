package com.springboot.demo.repository;

import com.springboot.demo.entity.CommentEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author yemingfeng
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

  @Query(value = "SELECT * FROM Comment WHERE articleId = ?1", nativeQuery = true)
  List<CommentEntity> findByArticleId(Long articleId);

  @Query(value = "SELECT * FROM Comment WHERE parentId = ?1", nativeQuery = true)
  List<CommentEntity> findByParentId(Long parentId);

}
