package com.springboot.demo.service;

import com.springboot.demo.data.PagedList;
import com.springboot.demo.entity.Article;

/**
 * @author yemingfeng
 */
public interface ArticleService {

  Article save(Article article);

  PagedList<Article> listByUserId(Long userId, int page, int size);

}