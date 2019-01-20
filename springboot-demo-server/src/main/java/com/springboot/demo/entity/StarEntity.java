package com.springboot.demo.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author yemingfeng
 */
@Data
@Entity
@Table(name = "Star", indexes = {
    @Index(name = "idx_user_id", columnList = "userId"),
    @Index(name = "idx_type_content_id", columnList = "type, contentId")
})
public class StarEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "userId")
  private UserEntity user;
  private String type;
  private Long contentId;

  public static final String ARTICLE = "article";
  public static final String COMMENT = "comment";
}
