package com.springboot.demo.service;

import com.springboot.demo.model.StarModel;

/**
 * @author yemingfeng
 */
public interface StarService {

  StarModel save(StarModel starModel);

  void delete(StarModel starModel);

}
