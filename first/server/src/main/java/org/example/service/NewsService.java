package org.example.service;

import org.example.db.News;
import org.example.repositories.NewsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    NewsRepo newsRepo;


    public NewsService(NewsRepo newsRepo) {
        this.newsRepo = newsRepo;
    }

    public News create(News news){
        return newsRepo.save(news);
    }

    public List<News> findAll(){
        return newsRepo.findAll();
    }
}
