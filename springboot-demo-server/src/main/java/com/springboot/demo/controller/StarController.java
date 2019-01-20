package com.springboot.demo.controller;

import com.springboot.demo.model.StarModel;
import com.springboot.demo.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yemingfeng
 */
@RestController
@RequestMapping("/star")
public class StarController {

  private final StarService starService;

  @Autowired
  public StarController(StarService starService) {
    this.starService = starService;
  }

  @PostMapping("")
  public StarModel save(StarModel starModel) {
    return starService.save(starModel);
  }

  @DeleteMapping("")
  public void delete(StarModel starModel) {
    starService.delete(starModel);
  }
}
