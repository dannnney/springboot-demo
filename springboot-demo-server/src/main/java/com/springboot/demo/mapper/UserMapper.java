package com.springboot.demo.mapper;

import com.springboot.demo.dto.UserDto;
import com.springboot.demo.entity.User;
import org.mapstruct.Mapper;

/**
 * @author yemingfeng
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

  UserDto toDto(User user);

  User toEntity(UserDto userDto);

}
