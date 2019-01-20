package com.springboot.demo.service.impl;

import com.springboot.demo.entity.UserEntity;
import com.springboot.demo.mapper.UserMapper;
import com.springboot.demo.model.UserModel;
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

  private final UserMapper userMapper;
  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserMapper userMapper,
      UserRepository userRepository) {
    this.userMapper = userMapper;
    this.userRepository = userRepository;
  }

  @Override
  public UserModel login(UserModel userModel) {
    UserEntity userEntity = userMapper.toEntity(userModel);
    UserEntity existed = userRepository.findByUsername(userEntity.getUsername());
    if (existed == null) {
      throw new RuntimeException(userModel.getUsername() + " not register");
    }
    if (StringUtils.equals(existed.getPassword(), userEntity.getPassword())) {
      return userMapper.toModel(existed);
    }
    throw new RuntimeException("username and password not pair");
  }

  @Override
  public UserModel register(UserModel userModel) {
    UserEntity userEntity = userMapper.toEntity(userModel);
    UserEntity existed = userRepository.findByUsername(userEntity.getUsername());
    if (existed != null) {
      throw new RuntimeException(userEntity.getUsername() + " is registered");
    }
    UserEntity db = userRepository.save(userEntity);
    return userMapper.toModel(db);
  }
}
