package com.springboot.demo.model;

import java.io.Serializable;
import lombok.Data;

/**
 * @author yemingfeng
 */
@Data
public class UserModel implements Serializable {

  private Long id;
  private String username;
  private String password;
}
