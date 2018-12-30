package com.springboot.demo.service.impl;

import com.springboot.demo.data.PagedList;
import com.springboot.demo.entity.Article;
import com.springboot.demo.repository.ArticleRepository;
import com.springboot.demo.repository.UserRepository;
import com.springboot.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author yemingfeng
 */
@Service
public class ArticleServiceImpl implements ArticleService {

  private final UserRepository userRepository;
  private final ArticleRepository articleRepository;

  @Autowired
  public ArticleServiceImpl(UserRepository userRepository,
      ArticleRepository articleRepository) {
    this.userRepository = userRepository;
    this.articleRepository = articleRepository;
  }

  @Override
  public Article save(Article article) {
    if (userRepository.findOne(article.getUserId()) == null) {
      return null;
    }
    return articleRepository.save(article);
  }

  @Override
  @Cacheable(cacheNames = "article", key = "#userId + '_' + #page + '_' + #size")
  public PagedList<Article> listByUserId(Long userId, int page, int size) {
    Page<Article> articlePage = articleRepository.findByUserId(userId, new PageRequest(page, size));
    PagedList<Article> pagedList = new PagedList<>();
    pagedList.setData(articlePage.getContent());
    pagedList.setPage(page);
    pagedList.setSize(articlePage.getSize());
    pagedList.setTotalPage(articlePage.getTotalPages());
    return pagedList;
  }
}
