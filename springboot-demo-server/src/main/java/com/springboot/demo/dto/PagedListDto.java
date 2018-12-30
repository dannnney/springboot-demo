package com.springboot.demo.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author yemingfeng
 */
@Data
public class PagedListDto<T> implements Serializable {

  private List<T> data;
  private int page;
  private int size;
  private int totalPage;
}