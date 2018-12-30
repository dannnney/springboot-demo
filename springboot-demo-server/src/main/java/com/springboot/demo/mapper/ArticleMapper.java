package com.springboot.demo.mapper;

import com.springboot.demo.data.PagedList;
import com.springboot.demo.dto.ArticleDto;
import com.springboot.demo.entity.Article;
import org.mapstruct.Mapper;

/**
 * @author yemingfeng
 */
@Mapper(componentModel = "spring")
public interface ArticleMapper {

  ArticleDto toDto(Article article);

  Article toEntity(ArticleDto articleDto);

  PagedList<ArticleDto> toDtos(PagedList<Article> pagedList);

}
