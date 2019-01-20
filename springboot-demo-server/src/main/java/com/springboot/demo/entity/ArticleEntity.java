package com.springboot.demo.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author yemingfeng
 */
@Data
@Entity
@Table(name = "ArticleEntity", indexes = {
    @Index(name = "idx_user_id", columnList = "userId")
})
public class ArticleEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long userId;
  private String content;
}