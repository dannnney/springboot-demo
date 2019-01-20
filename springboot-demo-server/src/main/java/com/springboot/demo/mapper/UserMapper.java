package com.springboot.demo.mapper;

import com.springboot.demo.entity.UserEntity;
import com.springboot.demo.model.UserModel;
import org.mapstruct.Mapper;

/**
 * @author yemingfeng
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

  UserModel toModel(UserEntity userEntity);

  UserEntity toEntity(UserModel userModel);

}
