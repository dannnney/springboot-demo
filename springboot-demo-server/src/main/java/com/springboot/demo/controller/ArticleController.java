package com.springboot.demo.controller;

import com.springboot.demo.model.ArticleModel;
import com.springboot.demo.service.ArticleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yemingfeng
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

  private final ArticleService articleService;

  @Autowired
  public ArticleController(ArticleService articleService) {
    this.articleService = articleService;
  }

  @PostMapping("/save")
  public ArticleModel save(@RequestBody ArticleModel articleModel) {
    return articleService.save(articleModel);
  }

  @GetMapping("/list")
  public List<ArticleModel> listByUserId(@RequestParam("userId") Long userId) {
    return articleService.listByUserId(userId);
  }
}