package com.springboot.demo.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
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
@Table(name = "User", indexes = {
    @Index(name = "idx_username", columnList = "username")
})
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username")
  private String username;
  @Column(name = "password")
  private String password;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date creation;

  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date modification;
}
