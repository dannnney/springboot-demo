package com.springboot.demo.mapper;

import com.springboot.demo.entity.StarEntity;
import com.springboot.demo.model.StarModel;
import org.mapstruct.Mapper;

/**
 * @author yemingfeng
 */
@Mapper(componentModel = "spring")
public interface StarMapper {

  StarEntity toEntity(StarModel starModel);

  StarModel toModel(StarEntity starEntity);

}
