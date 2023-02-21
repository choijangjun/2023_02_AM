package com.KoreaIT.java.AM;


import java.util.ArrayList;
import com.KoreaIT.java.AM.dto.Member;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.util.Util;

public class App {

	public static List<Article> articles;
	public static List<Member> members;

	static {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}
	static int lastArticleId = 0;
	static int id = lastArticleId + 1;

	public void run() {

		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		makeTestData();
		int lastMemberId = 0;

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
				int id = lastMemberId + 1;
				String regDate = Util.getDate();
				String loginId = null;

				while (true) {

					System.out.printf("로그인 아이디 : ");
					loginId = sc.nextLine();

					if (isJoinableLoginId(loginId) == false) {
						System.out.println("이미 사용중인 아이디입니다");
						continue;
					}

					break;
				}

				String loginPw = null;
				String loginPwConfirm = null;
				while (true) {
					System.out.printf("로그인 비밀번호 : ");
					loginPw = sc.nextLine();
					System.out.printf("로그인 비밀번호 재확인: ");
					loginPwConfirm = sc.nextLine();

					if (loginPw.equals(loginPwConfirm) == false) {
						System.out.println("비밀번호를 다시 입력해주세요");
						continue;
					}
					break;
				}
				System.out.printf("이름 : ");
				String name = sc.nextLine();

				Member member = new Member(id, regDate, loginId, loginPw, name);
				members.add(member);

				System.out.println(id + "번 회원이 가입되었습니다");
				lastMemberId++;
			} else if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시물은 존재하지 않습니다.");
				} else {
					System.out.println("번호 / 제목 / 날짜 / 조회수");
					for (int i = articles.size() - 1; i >= 0; i--) {
						Article article = articles.get(i);
						System.out.printf("  %d  /  %s  /  %s  / %d\n", article.id, article.title, article.now,
								article.look);

					}

				}

			} else if (command.equals("article write")) {

				int id = lastArticleId + 1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				String now = Util.getDate();
				int look = 0;
//				System.out.printf("%s / %s\n", title, body);

				Article article = new Article(id, now, title, body, look);
				articles.add(article);

				System.out.printf("%d번 글이 생성되었습니다.\n", id);
				lastArticleId++;

			} else if (command.startsWith("article detail ")) {

				String[] cmdBits = command.split(" "); // article / detail / 1
				// cmdBits[0] => article
				// cmdBits[1] => detail
				// cmdBits[2] => id

				int id = Integer.parseInt(cmdBits[2]);

				// article detail 1 => "1" => 1

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				foundArticle.look++;
				System.out.printf("번호 : %d\n", id);
				System.out.printf("날짜 : %s\n", foundArticle.now);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
				System.out.printf("조회수 : %d\n", foundArticle.look);

			} else if (command.startsWith("article delete ")) {
				String[] cmdBits = command.split(" "); // article / detail / 1
				// cmdBits[0] => article
				// cmdBits[1] => detail
				// cmdBits[2] => id

				int id = Integer.parseInt(cmdBits[2]);

				// article detail 1 => "1" => 1

				int foundIndex = getArticleIndextById(id);

				if (foundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				articles.remove(foundIndex);
				System.out.printf("%d번 글을 삭제했습니다.\n", id);

			} else if (command.startsWith("article modify ")) {
				String[] cmdBits = command.split(" "); // article / detail / 1
				// cmdBits[0] => article
				// cmdBits[1] => detail
				// cmdBits[2] => id

				int id = Integer.parseInt(cmdBits[2]);

				// article detail 1 => "1" => 1

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				String now = Util.getDate();

				foundArticle.title = title;
				foundArticle.body = body;

				System.out.printf("%d번 글을 수정했습니다.\n", id);

			} else {

				System.out.println("존재하지 않는 명령어 입니다.");

			}

		}

		System.out.println("==프로그램 끝==");

		sc.close();

	}

	private boolean isJoinableLoginId(String loginId) {

		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return true;
		}

		return false;
	}

	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		return -1;
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

	static void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");

		lastArticleId = 0;
		id = lastArticleId + 1;
		String now = Util.getDate();
		String title;
		String body;
		int look = 0;
		for (int i = 1; i <= 3; i++) {
			id = lastArticleId + 1;
			look = 10 * id;
			title = "Exam_title" + i;
			body = "Exam_body" + i;
			Article article = new Article(id, now, title, body, look);
			articles.add(article);
			System.out.printf("%d번 글이 생성되었습니다.\n", id);
			lastArticleId++;

		}

	}

}
