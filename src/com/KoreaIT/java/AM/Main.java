package com.KoreaIT.java.AM;

import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);
		
		int lastArticleId = 0;
		List<Article> articles = new ArrayList<>();
		
		while (true) {
			
			
			System.out.printf("명령어 ) ");
			String command = sc.nextLine().trim();
			
//			System.out.printf("입력된 명령어 : %s\n",  command);

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			
			
				
				
			if (command.equals("article delete 1")) {
				if (articles.size() == 0) {
					System.out.println("게시물은 존재하지 않습니다.");
					continue;
					
				}else if(articles.size() == 1){
					
					Article article = articles.remove(0);
					
					System.out.printf("%d번 게시물이 삭제 되었습니다.",article.id);
					continue;
					
				
					
					
				}
				
				
			}
			
			if (command.equals("system exit")) {
				break;
			}

			if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시물은 존재하지 않습니다.");
				}else {
					System.out.println("번호 / 제목 / 내용");
					for(int i = articles.size()-1; i >= 0; i--) {
						Article article = articles.get(i);
						System.out.printf("  %d  /  %s  /  %s  \n", article.id, article.title, article.body);
						
					}
					
				}
			} else if (command.equals("article write")) {
				int id = lastArticleId +1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
//				System.out.printf("%s / %s\n", title, body);

				Article article = new Article(id, title, body);
				articles.add(article);
				
				System.out.printf("%d번 글이 생성되었습니다.\n", id);
				lastArticleId++;
				
				
				
			}else if (command.startsWith("article detail ")) {
				
				String[] cmdBits = command.split(" "); // article / detail / 1
				// cmdBits[0] => article
				// cmdBits[1] => detail
				// cmdBits[2] => id
				
				int id = Integer.parseInt(cmdBits[2]);
				
				// article detail 1 => "1" => 1
				
				boolean found = false;
				Article article = null;
				for (int i = 0; i < articles.size(); i++) {
					// 0, 1, 2 -> index
					// 1, 2, 3 -> id
					article = articles.get(i);
					if (article.id == id) {
						found = true;
						break;
					}
				}
				
				if (found == false) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				System.out.printf("번호 : %d\n", id);
				System.out.printf("날짜 : %s\n", "2023-12-12 12:12:12");
				System.out.printf("제목 : %s\n", article.title);
				System.out.printf("내용 : %s\n", article.body);
				
			}
			else {

				System.out.println("존재하지 않는 명령어 입니다.");

			}
			
			

		}

		System.out.println("==프로그램 끝==");

		sc.close();
		
		
	
	}

}

class Article {
	int id;
	String title;
	String body;
	Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}
	

	
	
	
}
