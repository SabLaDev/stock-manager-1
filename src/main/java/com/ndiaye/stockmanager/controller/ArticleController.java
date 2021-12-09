package com.ndiaye.stockmanager.controller;

import com.ndiaye.stockmanager.controller.dto.ArticleForm;
import com.ndiaye.stockmanager.entity.Article;
import com.ndiaye.stockmanager.service.IArticleService;
import com.ndiaye.stockmanager.service.ICategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

    private IArticleService articleService;

    private ICategoryService categoryService;

    public ArticleController(IArticleService articleService, ICategoryService categoryService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
    }

    @GetMapping("/articles")
    public String listArticles(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "articles/articles";
    }

    @GetMapping("/articles/new")
    public String createArticleForm(Model model) {
        ArticleForm article = new ArticleForm();
        model.addAttribute("articleForm", article);

        model.addAttribute("categories", categoryService.getAllCategories());

        return "articles/create_article";

    }

    @PostMapping("/articles")
    public String createArticle(@ModelAttribute("articleForm") ArticleForm article) {
        articleService.createArticle(article);
        return "redirect:/articles";
    }
}
