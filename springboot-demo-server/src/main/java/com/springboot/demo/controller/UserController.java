package com.springboot.demo.controller;

import com.springboot.demo.data.ApiResponse;
import com.springboot.demo.dto.UserDto;
import com.springboot.demo.mapper.UserMapper;
import com.springboot.demo.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yemingfeng
 */
@RestController
@RequestMapping("/user")
public class UserController {

  private final UserMapper userMapper;
  private final UserService userService;

  @Autowired
  public UserController(UserMapper userMapper, UserService userService) {
    this.userMapper = userMapper;
    this.userService = userService;
  }

  @PostMapping("/login")
  public ApiResponse login(@Valid @RequestBody UserDto userDto) {
    return ApiResponse.successWithData(
        userMapper.toDto(userService.login(userMapper.toEntity(userDto))));
  }

  @PutMapping("/register")
  public ApiResponse register(@Valid @RequestBody UserDto userDto) {
    return ApiResponse.successWithData(
        userMapper.toDto(userService.register(userMapper.toEntity(userDto))));
  }
}
