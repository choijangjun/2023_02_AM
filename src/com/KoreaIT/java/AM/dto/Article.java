package com.KoreaIT.java.AM.dto;

public class Article {
	public int id;
	public String now;
	public String title;
	public String body;
	public int look;

	public Article(int id, String now, String title, String body, int look) {
		this.id = id;
		this.now = now;
		this.title = title;
		this.body = body;
		this.look = look;
	}

}
