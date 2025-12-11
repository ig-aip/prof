package org.example.controller;

import org.example.db.News;
import org.example.service.NewsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/News")
public class NewsController {
    NewsService newsService;


    @PostMapping
    public News create(@RequestBody News news){
        return newsService.create(news);
    }

    @GetMapping
    public List<News> getAll(){
        return newsService.findAll();
    }

}
