package com.springboot.demo.service;

import com.springboot.demo.entity.User;

/**
 * @author yemingfeng
 */

public interface UserService {

  User login(User user);

  User register(User user);

}
