package org.example.controller;

import org.example.db.News;
import org.example.service.NewsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/News")
public class NewsController {
    NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping
    public News create(@RequestBody News news){
        return newsService.create(news);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_FRANCHAISER')")
    public List<News> getAll(){
        return newsService.findAll();
    }

}
