package com.springboot.demo.service.impl;

import com.springboot.demo.config.RequestHolder;
import com.springboot.demo.entity.ArticleEntity;
import com.springboot.demo.entity.StarEntity;
import com.springboot.demo.entity.UserEntity;
import com.springboot.demo.mapper.ArticleMapper;
import com.springboot.demo.mapper.UserMapper;
import com.springboot.demo.model.ArticleModel;
import com.springboot.demo.repository.ArticleRepository;
import com.springboot.demo.repository.StarRepository;
import com.springboot.demo.repository.UserRepository;
import com.springboot.demo.service.ArticleService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yemingfeng
 */
@Service
public class ArticleServiceImpl implements ArticleService {

  private final UserMapper userMapper;
  private final RequestHolder requestHolder;
  private final ArticleMapper articleMapper;
  private final UserRepository userRepository;
  private final StarRepository starRepository;
  private final ExecutorService executorService;
  private final ArticleRepository articleRepository;

  @Autowired
  public ArticleServiceImpl(UserMapper userMapper,
      RequestHolder requestHolder,
      ArticleMapper articleMapper,
      UserRepository userRepository,
      StarRepository starRepository,
      ExecutorService executorService,
      ArticleRepository articleRepository) {
    this.userMapper = userMapper;
    this.requestHolder = requestHolder;
    this.articleMapper = articleMapper;
    this.userRepository = userRepository;
    this.starRepository = starRepository;
    this.executorService = executorService;
    this.articleRepository = articleRepository;
  }

  @Override
  public ArticleModel save(ArticleModel articleModel) {
    ArticleEntity articleEntity = articleMapper.toEntity(articleModel);
    articleEntity.setUserId(requestHolder.getUser().getId());
    ArticleEntity db = articleRepository.save(articleEntity);
    return articleMapper.toModel(db);
  }

  @Override
  public List<ArticleModel> listByUser() {
    List<ArticleEntity> articleEntities = articleRepository.findByUserId(requestHolder.getUserId());
    List<ArticleModel> articleModels = new ArrayList<>();
    for (ArticleEntity articleEntity : articleEntities) {
      ArticleModel articleModel = articleMapper.toModel(articleEntity);
      UserEntity userEntity = userRepository.findOne(articleEntity.getUserId());
      articleModel.setUser(userMapper.toModel(userEntity));
      fillArticleModel(articleModel);
    }
    return articleModels;
  }

  private void fillArticleModel(ArticleModel articleModel) {
    Long userId = requestHolder.getUserId();
    CompletableFuture<Long> starCountFuture = CompletableFuture.supplyAsync(() ->
            starRepository.countByTypeAndContentId(StarEntity.ARTICLE, articleModel.getId()),
        executorService);
    CompletableFuture<Boolean> staredFuture = CompletableFuture.supplyAsync(() ->
            starRepository.findByUserIdAndTypeAndContentId(userId,
                StarEntity.ARTICLE, articleModel.getId()) != null,
        executorService);

    try {
      articleModel.setStarCount(starCountFuture.get());
      articleModel.setStared(staredFuture.get());
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }
}
