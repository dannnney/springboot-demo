package com.springboot.demo.config;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

import com.springboot.demo.entity.UserEntity;
import java.io.Serializable;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @author yemingfeng
 */
@Data
@Component
@Scope(value = SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestHolder implements Serializable {

  private UserEntity user;

}