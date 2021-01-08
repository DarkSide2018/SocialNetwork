package com.highload.socialNetwork.service;

import com.highload.socialNetwork.model.NewsMessage;
import com.highload.socialNetwork.repos.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsRepository repository;


    public List<NewsMessage> getLastNews(){
        return repository.getLastNews();
    }
}
