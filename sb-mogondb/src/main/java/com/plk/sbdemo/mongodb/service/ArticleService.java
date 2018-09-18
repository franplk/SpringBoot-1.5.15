package com.plk.sbdemo.mongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plk.sbdemo.mongodb.entity.Article;
import com.plk.sbdemo.mongodb.repository.ArticleRepository;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleDao;
	
	public Article addArticle(Article article) {
		return articleDao.insert(article);
	}
	
	public void delArticleById(Long id) {
		articleDao.delete(id);
	}
	
	public Article updateArticle(Article article) {
		return articleDao.save(article);
	}
	
	public Article getById(Long id) {
		return articleDao.findOne(id);
	}
}
