package com.xiaohe.service;

import com.xiaohe.pojo.Article;
import com.xiaohe.pojo.PageBean;

public interface ArticleService {
    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    Article findById(Integer id);

    void delete(Integer id);

    void update(Article article);
}
