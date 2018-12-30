package com.springboot.demo.data;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author yemingfeng
 */
@Data
public class PagedList<T> implements Serializable {

  private List<T> data;
  private int page;
  private int size;
  private int totalPage;
}