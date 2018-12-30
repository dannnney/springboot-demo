package com.springboot.demo.repository;

import com.springboot.demo.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yemingfeng
 */

public interface ArticleRepository extends JpaRepository<Article, Long> {

  Page<Article> findByUserId(Long userId, Pageable pageable);

}
