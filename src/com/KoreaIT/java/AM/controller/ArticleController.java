package com.KoreaIT.java.AM.controller;

import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;
import java.util.List;

public class ArticleController {
	private List<Article> articles;
	private Scanner sc;


	public ArticleController(List<Article> articles, Scanner sc) {
		this.articles = articles;
		this.sc = sc;
		// TODO Auto-generated constructor stub
	}

	int lastArticleId = 3;

	

	public void showList() {
		if (articles.size() == 0) {
			System.out.println("게시물은 존재하지 않습니다.");
		} else {
			System.out.println("번호 / 제목 / 날짜 / 조회수");
			for (int i = articles.size() - 1; i >= 0; i--) {
				Article article = articles.get(i);
				System.out.printf("  %d  /  %s  /  %s  / %d\n", article.id, article.title, article.now, article.look);

			}

		}

	}

	public void doWrite(String command) {
		int id = lastArticleId + 1;
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		String now = Util.getDate();
		int look = 0;
//		System.out.printf("%s / %s\n", title, body);

		Article article = new Article(id, now, title, body, look);
		articles.add(article);

		System.out.printf("%d번 글이 생성되었습니다.\n", id);
		lastArticleId++;

	}

	public void showDetail(String command) {
		String[] cmdBits = command.split(" "); // article / detail / 1
		// cmdBits[0] => article
		// cmdBits[1] => detail
		// cmdBits[2] => id

		int id = Integer.parseInt(cmdBits[2]);

		// article detail 1 => "1" => 1

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		foundArticle.look++;
		System.out.printf("번호 : %d\n", id);
		System.out.printf("날짜 : %s\n", foundArticle.now);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("조회수 : %d\n", foundArticle.look);

	}

	public void doModify(String command) {
		String[] cmdBits = command.split(" "); // article / detail / 1
		// cmdBits[0] => article
		// cmdBits[1] => detail
		// cmdBits[2] => id

		int id = Integer.parseInt(cmdBits[2]);

		// article detail 1 => "1" => 1

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		String now = Util.getDate();

		foundArticle.title = title;
		foundArticle.body = body;

		System.out.printf("%d번 글을 수정했습니다.\n", id);

	}

	public void doDelete(String command) {
		String[] cmdBits = command.split(" "); // article / detail / 1
		// cmdBits[0] => article
		// cmdBits[1] => detail
		// cmdBits[2] => id

		int id = Integer.parseInt(cmdBits[2]);

		// article detail 1 => "1" => 1

		int foundIndex = getArticleIndextById(id);

		if (foundIndex == -1) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		articles.remove(foundIndex);
		System.out.printf("%d번 글을 삭제했습니다.\n", id);

	}

	private int getArticleIndextById(int id) {
		int i = 0;

		for (Article article : articles) {
			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private Article getArticleById(int id) {
		// 1
//		for (int i = 0; i < articles.size(); i++) {
//
//			Article article = articles.get(i);
//			if (article.id == id) {
//
//				return article;
//
//			}
//		}
//
//		return null;
//	}
		// 2
//		for (Article article : articles) {
//			if (article.id == id) {
//				return article;
//			}
//		}
		// 3
		int index = getArticleIndextById(id);

		if (index != -1) {
			return articles.get(index);
		}
		return null;
	}
	



}
