package com.plk.sbdemo.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.plk.sbdemo.mongodb.entity.Article;

public interface ArticleRepository extends MongoRepository<Article, Long> {

}
