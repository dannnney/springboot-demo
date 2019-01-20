package com.springboot.demo.config;

import com.springboot.demo.repository.UserRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author yemingfeng
 */
@Component
public class RequestInterceptor implements HandlerInterceptor {

  private final RequestHolder requestHolder;
  private final UserRepository userRepository;

  @Autowired
  public RequestInterceptor(RequestHolder requestHolder, UserRepository userRepository) {
    this.requestHolder = requestHolder;
    this.userRepository = userRepository;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    if (request.getParameter("userId") != null) {
      requestHolder.setUser(userRepository.findOne(Long.valueOf(request.getParameter("userId"))));
    }
    if (requestHolder.getUser() == null) {
      throw new RuntimeException("user not found");
    }
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) {

  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) {

  }
}