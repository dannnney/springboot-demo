package com.springboot.demo.service.impl;

import com.springboot.demo.config.RequestHolder;
import com.springboot.demo.entity.ArticleEntity;
import com.springboot.demo.entity.CommentEntity;
import com.springboot.demo.entity.StarEntity;
import com.springboot.demo.mapper.StarMapper;
import com.springboot.demo.model.StarModel;
import com.springboot.demo.repository.ArticleRepository;
import com.springboot.demo.repository.CommentRepository;
import com.springboot.demo.repository.StarRepository;
import com.springboot.demo.service.StarService;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yemingfeng
 */
@Service
public class StarServiceImpl implements StarService {

  private final StarMapper starMapper;
  private final RequestHolder requestHolder;
  private final StarRepository starRepository;
  private final CommentRepository commentRepository;
  private final ArticleRepository articleRepository;

  @Autowired
  public StarServiceImpl(StarMapper starMapper,
      RequestHolder requestHolder, StarRepository starRepository,
      CommentRepository commentRepository, ArticleRepository articleRepository) {
    this.starMapper = starMapper;
    this.requestHolder = requestHolder;
    this.starRepository = starRepository;
    this.commentRepository = commentRepository;
    this.articleRepository = articleRepository;
  }

  @Override
  public StarModel save(StarModel starModel) {
    if (!Arrays.asList(StarEntity.ARTICLE, StarEntity.COMMENT).contains(starModel.getType())) {
      throw new RuntimeException(starModel.getType() + " is invalid");
    }
    switch (starModel.getType()) {
      case StarEntity.ARTICLE: {
        ArticleEntity articleEntity = articleRepository.findOne(starModel.getContentId());
        if (articleEntity == null) {
          throw new RuntimeException(starModel.getContentId() + " not found");
        }
      }
      case StarEntity.COMMENT: {
        CommentEntity commentEntity = commentRepository.findOne(starModel.getContentId());
        if (commentEntity == null) {
          throw new RuntimeException(starModel.getContentId() + " not found");
        }
      }
    }

    StarEntity starEntity = starMapper.toEntity(starModel);
    starEntity.setUserId(requestHolder.getUserId());
    StarEntity db = starRepository.save(starEntity);
    return starMapper.toModel(db);
  }

  @Override
  public void delete(StarModel starModel) {
    if (starModel.getId() != null && starModel.getId() != 0) {
      starRepository.delete(starModel.getId());
      return;
    }
    StarEntity starEntity = starMapper.toEntity(starModel);
    starRepository.deleteByUserIdAndTypeAndContentId(requestHolder.getUserId(),
        starEntity.getType(), starEntity.getContentId());
  }
}
