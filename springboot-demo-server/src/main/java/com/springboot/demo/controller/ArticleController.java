package com.springboot.demo.controller;

import com.springboot.demo.data.ApiResponse;
import com.springboot.demo.dto.ArticleDto;
import com.springboot.demo.mapper.ArticleMapper;
import com.springboot.demo.service.ArticleService;
import javax.validation.Valid;
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

  private final ArticleMapper articleMapper;
  private final ArticleService articleService;

  @Autowired
  public ArticleController(ArticleMapper articleMapper, ArticleService articleService) {
    this.articleMapper = articleMapper;
    this.articleService = articleService;
  }

  @PostMapping("/save")
  public ApiResponse save(@RequestBody @Valid ArticleDto articleDto) {
    return ApiResponse.successWithData(
        articleMapper.toDto(articleService.save(articleMapper.toEntity(articleDto))));
  }

  @GetMapping("/list")
  public ApiResponse listByUserId(@RequestParam("userId") Long userId,
      @RequestParam("page") int page,
      @RequestParam("size") int size) {
    return ApiResponse.successWithData(
        articleMapper.toDtos(articleService.listByUserId(userId, page, size)));
  }
}