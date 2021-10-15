package exercise;

import java.util.Scanner;

public class Exercise09 {

	public static void main(String[] args) {

		boolean run = true;

		int studentNum = 0;
		int[] scores = null;

		Scanner scanner = new Scanner(System.in);

		while (run) {
			System.out.println("----------------------");
			System.out.println("1.학생수 | 2.점수입력 | 3.점수리스트 | 4.분석 | 5.종료");
			System.out.println("----------------------");
			System.out.println("선택> ");

			int selectNo = scanner.nextInt();

			switch (selectNo) {
			case 1:
				// 입력한 학생수 만큼 배열크기를 초기화
				System.out.println("학생수> ");
				scores = new int[scanner.nextInt()];
				break;
			case 2:
				// 배열크기만큼 반복해서 점수를 입력받음
				for (int i = 0; i < scores.length; i++) {
					System.out.println("scores[" + i + "]");
					scores[i] = scanner.nextInt();

				}

				break;
			case 3:
				// 배열크기만큼 반복해서 점수목록을 출력
				for (int i = 0; i < scores.length; i++) {
					System.out.println("scores[" + i + "]" + scores[i]);

				}
				break;
			case 4:
				// 최고점수와 평균점수 출력
				int sum = 0;
				for (int score : scores) {
					sum += score;
				}

				System.out.println("최고점수: " + sum);
				System.out.println("최고점수: " + sum * 1.0 / scores.length);

				break;
			case 5:
				run = false;
				break;
			}

		}

		System.out.println("프로그램 종료");

	}

}
