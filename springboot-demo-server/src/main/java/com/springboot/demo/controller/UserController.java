package com.springboot.demo.controller;

import com.springboot.demo.model.UserModel;
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

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  public UserModel login(@Valid @RequestBody UserModel userModel) {
    return userService.login(userModel);
  }

  @PutMapping("/register")
  public UserModel register(@Valid @RequestBody UserModel userModel) {
    return userService.register(userModel);
  }
}
