package com.springboot.demo.mapper;

import com.springboot.demo.entity.CommentEntity;
import com.springboot.demo.model.CommentModel;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * @author yemingfeng
 */
@Mapper(componentModel = "spring")
public interface CommentMapper {

  CommentModel toModel(CommentEntity commentEntity);

  List<CommentModel> toModels(List<CommentEntity> commentEntities);

  CommentEntity toEntity(CommentModel commentModel);

}
