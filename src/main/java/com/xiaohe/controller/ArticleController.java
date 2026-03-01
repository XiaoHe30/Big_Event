package com.xiaohe.controller;

import com.xiaohe.pojo.Article;
import com.xiaohe.pojo.PageBean;
import com.xiaohe.pojo.Result;
import com.xiaohe.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody @Validated(value = Article.Update.class) Article article){
        articleService.update(article);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ){
        PageBean<Article> pb  = articleService.list(pageNum, pageSize, categoryId, state);

        return Result.success(pb);
    }

    @GetMapping("/detail")
    public Result<Article> detail(Integer id){
        Article a = articleService.findById(id);
        return Result.success(a);
    }

    @DeleteMapping
    public Result delete(Integer id){
        articleService.delete(id);
        return Result.success();
    }
}
