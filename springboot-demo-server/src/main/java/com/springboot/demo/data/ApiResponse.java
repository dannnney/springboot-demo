package com.springboot.demo.data;

import lombok.Builder;
import lombok.Data;

/**
 * @author yemingfeng
 */
@Data
@Builder
public class ApiResponse {

  private String message;
  private boolean success;
  private Object data;

  public static ApiResponse success() {
    return ApiResponse.builder().success(true).build();
  }

  public static ApiResponse successWithData(Object data) {
    return ApiResponse.builder().success(true).data(data).build();
  }

  public static ApiResponse fail() {
    return ApiResponse.builder().success(false).build();
  }

  public static ApiResponse failWithMessage(String message) {
    return ApiResponse.builder().success(false).message(message).build();
  }
}
