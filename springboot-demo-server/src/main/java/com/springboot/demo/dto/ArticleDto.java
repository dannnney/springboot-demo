package com.springboot.demo.dto;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author yemingfeng
 */
@Data
public class ArticleDto implements Serializable {

  private Long id;
  @NotNull
  private Long userId;
  @NotEmpty
  private String content;
  private Date creation;
  private Date modification;
}