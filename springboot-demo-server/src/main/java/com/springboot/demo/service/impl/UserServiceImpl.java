package com.springboot.demo.service.impl;

import com.springboot.demo.entity.User;
import com.springboot.demo.repository.UserRepository;
import com.springboot.demo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yemingfeng
 */
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User login(User user) {
    User existed = userRepository.findByUsername(user.getUsername());
    if (existed == null) {
      return null;
    }
    if (StringUtils.equals(existed.getPassword(), user.getPassword())) {
      return existed;
    }
    return null;
  }

  @Override
  public User register(User user) {
    User existed = userRepository.findByUsername(user.getUsername());
    if (existed != null) {
      return null;
    }
    return userRepository.save(user);
  }
}
