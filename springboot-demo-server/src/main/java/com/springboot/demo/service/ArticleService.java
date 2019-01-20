package com.springboot.demo.service;

import com.springboot.demo.model.ArticleModel;
import java.util.List;

/**
 * @author yemingfeng
 */
public interface ArticleService {

  ArticleModel save(ArticleModel articleModel);

  List<ArticleModel> listByUser();

}