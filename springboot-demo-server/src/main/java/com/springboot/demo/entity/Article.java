package com.springboot.demo.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author yemingfeng
 */
@Data
@Entity
@Table(name = "Article", indexes = {
    @Index(name = "idx_user_id", columnList = "userId")
})
@EntityListeners(AuditingEntityListener.class)
public class Article implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long userId;

  private String content;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date creation;

  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date modification;
}