package com.springboot.demo.service.impl;

import com.springboot.demo.config.RequestHolder;
import com.springboot.demo.entity.ArticleEntity;
import com.springboot.demo.entity.CommentEntity;
import com.springboot.demo.entity.StarEntity;
import com.springboot.demo.mapper.CommentMapper;
import com.springboot.demo.model.CommentModel;
import com.springboot.demo.repository.ArticleRepository;
import com.springboot.demo.repository.CommentRepository;
import com.springboot.demo.repository.StarRepository;
import com.springboot.demo.service.CommentService;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yemingfeng
 */
@Service
public class CommentServiceImpl implements CommentService {

  private final RequestHolder requestHolder;
  private final CommentMapper commentMapper;
  private final StarRepository starRepository;
  private final ArticleRepository articleRepository;
  private final CommentRepository commentRepository;

  @Autowired
  public CommentServiceImpl(RequestHolder requestHolder,
      CommentMapper commentMapper,
      StarRepository starRepository,
      ArticleRepository articleRepository,
      CommentRepository commentRepository) {
    this.requestHolder = requestHolder;
    this.commentMapper = commentMapper;
    this.starRepository = starRepository;
    this.articleRepository = articleRepository;
    this.commentRepository = commentRepository;
  }

  @Override
  public List<CommentModel> listByArticleId(Long articleId) {
    ArticleEntity articleEntity = articleRepository.findOne(articleId);
    if (articleEntity == null) {
      throw new RuntimeException(articleId + " not found");
    }
    List<CommentEntity> commentEntities =
        commentRepository.findByArticle(articleEntity)
            .stream()
            .filter(commentEntity -> Objects.isNull(commentEntity.getParent()))
            .collect(Collectors.toList());
    return toCommentModels(commentEntities);
  }

  @Override
  public List<CommentModel> listByParentId(Long parentId) {
    CommentEntity parentEntity = commentRepository.findOne(parentId);
    if (parentEntity == null) {
      throw new RuntimeException(parentId + " not found");
    }
    List<CommentEntity> commentEntities = commentRepository.findByParent(parentEntity);
    return toCommentModels(commentEntities);
  }

  private List<CommentModel> toCommentModels(List<CommentEntity> commentEntities) {
    List<CommentModel> commentModels = commentMapper.toModels(commentEntities);
    for (CommentModel commentModel : commentModels) {
      commentModel.setStarCount(
          starRepository.countByTypeAndContentId(StarEntity.COMMENT, commentModel.getId()));
      commentModel.setStared(starRepository
          .findByUserAndTypeAndContentId(requestHolder.getUser(), StarEntity.COMMENT,
              commentModel.getId()) != null);
    }
    return commentModels;
  }

  @Override
  public CommentModel save(CommentModel commentModel) {
    if (commentModel.getParentComment() != null) {
      CommentEntity parent = commentRepository.findOne(commentModel.getParentComment().getId());
      if (parent == null) {
        throw new RuntimeException(commentModel.getParentComment().getId() + " is not found");
      }
    }
    CommentEntity commentEntity = commentMapper.toEntity(commentModel);
    commentEntity.setUser(requestHolder.getUser());
    CommentEntity db = commentRepository.save(commentEntity);
    return commentMapper.toModel(db);
  }
}
