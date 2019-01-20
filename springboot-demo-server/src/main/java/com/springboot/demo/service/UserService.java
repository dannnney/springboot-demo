package com.springboot.demo.service;

import com.springboot.demo.model.UserModel;

/**
 * @author yemingfeng
 */

public interface UserService {

  UserModel login(UserModel userModel);

  UserModel register(UserModel userModel);

}
