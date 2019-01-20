package com.springboot.demo.model;

import java.io.Serializable;
import lombok.Data;

/**
 * @author yemingfeng
 */
@Data
public class StarModel implements Serializable {

  private Long id;
  private UserModel user;
  private String type;
  private Long contentId;
}
