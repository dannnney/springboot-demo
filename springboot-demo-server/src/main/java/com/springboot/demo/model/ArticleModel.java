package com.springboot.demo.model;

import java.io.Serializable;
import lombok.Data;

/**
 * @author yemingfeng
 */
@Data
public class ArticleModel implements Serializable {

  private Long id;
  private UserModel user;
  private String content;

  private Long starCount;
  private Boolean stared;
}