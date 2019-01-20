package com.springboot.demo.model;

import java.io.Serializable;
import lombok.Data;

/**
 * @author yemingfeng
 */
@Data
public class CommentModel implements Serializable {

  private Long id;
  private CommentModel parentComment;
  private UserModel user;
  private String content;

  private Long starCount;
  private Boolean stared;
}
