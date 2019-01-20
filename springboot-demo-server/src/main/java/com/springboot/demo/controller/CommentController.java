package com.springboot.demo.controller;

import com.springboot.demo.model.CommentModel;
import com.springboot.demo.service.CommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yemingfeng
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

  private final CommentService commentService;

  @Autowired
  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @PostMapping
  public CommentModel save(CommentModel commentModel) {
    return commentService.save(commentModel);
  }

  @GetMapping("/article")
  public List<CommentModel> listByArticleId(@RequestParam("id") Long articleId) {
    return commentService.listByArticleId(articleId);
  }

  @GetMapping("/parent")
  public List<CommentModel> listByParentId(@RequestParam("id") Long parentId) {
    return commentService.listByParentId(parentId);
  }
}
