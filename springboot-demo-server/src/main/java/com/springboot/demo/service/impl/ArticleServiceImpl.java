package com.springboot.demo.service.impl;

import com.springboot.demo.config.RequestHolder;
import com.springboot.demo.entity.ArticleEntity;
import com.springboot.demo.entity.StarEntity;
import com.springboot.demo.entity.UserEntity;
import com.springboot.demo.mapper.ArticleMapper;
import com.springboot.demo.model.ArticleModel;
import com.springboot.demo.repository.ArticleRepository;
import com.springboot.demo.repository.StarRepository;
import com.springboot.demo.service.ArticleService;
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

  private final RequestHolder requestHolder;
  private final ArticleMapper articleMapper;
  private final StarRepository starRepository;
  private final ExecutorService executorService;
  private final ArticleRepository articleRepository;

  @Autowired
  public ArticleServiceImpl(RequestHolder requestHolder,
      ArticleMapper articleMapper, StarRepository starRepository,
      ExecutorService executorService, ArticleRepository articleRepository) {
    this.requestHolder = requestHolder;
    this.articleMapper = articleMapper;
    this.starRepository = starRepository;
    this.executorService = executorService;
    this.articleRepository = articleRepository;
  }

  @Override
  public ArticleModel save(ArticleModel articleModel) {
    ArticleEntity articleEntity = articleMapper.toEntity(articleModel);
    articleEntity.setUser(requestHolder.getUser());
    ArticleEntity db = articleRepository.save(articleEntity);
    return articleMapper.toModel(db);
  }

  @Override
  public List<ArticleModel> listByUser() {
    UserEntity userEntity = requestHolder.getUser();
    List<ArticleEntity> articleEntities = articleRepository.findByUser(userEntity);
    List<ArticleModel> articleModels = articleMapper.toModels(articleEntities);
    articleModels.parallelStream()
        .forEach(this::fillArticleModel);
    return articleModels;
  }

  private void fillArticleModel(ArticleModel articleModel) {
    UserEntity userEntity = requestHolder.getUser();
    CompletableFuture<Long> starCountFuture = CompletableFuture.supplyAsync(() ->
            starRepository.countByTypeAndContentId(StarEntity.ARTICLE, articleModel.getId()),
        executorService);
    CompletableFuture<Boolean> staredFuture = CompletableFuture.supplyAsync(() ->
            starRepository.findByUserAndTypeAndContentId(userEntity,
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
