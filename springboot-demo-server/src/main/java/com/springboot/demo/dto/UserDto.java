package com.springboot.demo.dto;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author yemingfeng
 */
@Data
public class UserDto implements Serializable {

  private Long id;
  @NotNull
  private String username;
  @NotNull
  private String password;
  private Date creation;
  private Date modification;
}
