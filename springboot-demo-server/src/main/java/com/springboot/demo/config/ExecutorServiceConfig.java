package com.springboot.demo.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yemingfeng
 */
@Configuration
public class ExecutorServiceConfig {

  @Bean
  public ExecutorService build() {
    return Executors.newFixedThreadPool(200);
  }
}
