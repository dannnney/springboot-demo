package com.springboot.demo.mapper;

import com.springboot.demo.entity.ArticleEntity;
import com.springboot.demo.model.ArticleModel;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * @author yemingfeng
 */
@Mapper(componentModel = "spring")
public interface ArticleMapper {

  ArticleEntity toEntity(ArticleModel articleModel);

  ArticleModel toModel(ArticleEntity articleEntity);

  List<ArticleModel> toModels(List<ArticleEntity> articleEntities);

}
