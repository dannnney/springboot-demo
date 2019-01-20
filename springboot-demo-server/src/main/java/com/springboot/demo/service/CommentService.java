package com.springboot.demo.service;

import com.springboot.demo.model.CommentModel;
import java.util.List;

/**
 * @author yemingfeng
 */
public interface CommentService {

  List<CommentModel> listByArticleId(Long articleId);

  List<CommentModel> listByParentId(Long parentId);

  CommentModel save(CommentModel commentModel);

}
