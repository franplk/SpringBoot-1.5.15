package com.plk.sbdemo.mongodb.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

public class Article implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id // 标注为MongoDB的ID
	private Long id;

	private String title;
	private Date createDate;
	private String author;
	private String source;
	private String summary;
	private String content;
	
	public Article() {
	}
	
	public Article(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Article setId(Long id) {
		this.id = id;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Article setTitle(String title) {
		this.title = title;
		return this;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Article setCreateDate(Date createDate) {
		this.createDate = createDate;
		return this;
	}

	public String getAuthor() {
		return author;
	}

	public Article setAuthor(String author) {
		this.author = author;
		return this;
	}

	public String getSource() {
		return source;
	}

	public Article setSource(String source) {
		this.source = source;
		return this;
	}

	public String getSummary() {
		return summary;
	}

	public Article setSummary(String summary) {
		this.summary = summary;
		return this;
	}

	public String getContent() {
		return content;
	}

	public Article setContent(String content) {
		this.content = content;
		return this;
	}
}
