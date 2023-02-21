package com.KoreaIT.java.AM;

import java.util.ArrayList;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;
import java.util.List;
import java.util.Scanner;
import com.KoreaIT.java.AM.controller.ArticleController;
import com.KoreaIT.java.AM.controller.MemberController;

import com.KoreaIT.java.AM.util.Util;

public class App {

	public static List<Article> articles;
	public static List<Member> members;

	static {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}
	static int lastId = 0;
	static int id = lastId + 1;
	

	public void run() {

		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		makeTestData();
		int lastMemberId = 0;

		MemberController memberController = new MemberController(members, sc);
		ArticleController articleController = new ArticleController(articles, sc);

		while (true) {

			System.out.printf("명령어 ) ");
			String command = sc.nextLine().trim();

//			System.out.printf("입력된 명령어 : %s\n",  command);

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}

			if (command.equals("system exit")) {
				break;
			}
			if (command.equals("member join")) {

				memberController.doJoin();

			} else if (command.equals("article list")) {
				articleController.showList();

			} else if (command.equals("article write")) {
				articleController.doWrite(command);

			} else if (command.startsWith("article detail ")) {
				articleController.showDetail(command);

			} else if (command.startsWith("article modify ")) {
				articleController.doModify(command);

			} else if (command.startsWith("article delete ")) {
				articleController.doDelete(command);

			} else {

				System.out.println("존재하지 않는 명령어 입니다.");

			}

		}

		System.out.println("==프로그램 끝==");

		sc.close();

	}

	static void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");

		lastId = 0;
		id = lastId + 1;
		String now = Util.getDate();
		String title;
		String body;
		int look = 0;
		for (int i = 1; i <= 3; i++) {
			id = lastId + 1;
			look = 10 * id;
			title = "Exam_title" + i;
			body = "Exam_body" + i;
			Article article = new Article(id, now, title, body, look);
			articles.add(article);
			System.out.printf("%d번 글이 생성되었습니다.\n", id);
			lastId++;

		}

	}

}
