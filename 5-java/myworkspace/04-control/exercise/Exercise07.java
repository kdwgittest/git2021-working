import java.util.Scanner;

public class Exercise07 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean run = true;

		int balance = 0;

		Scanner scanner = new Scanner(System.in);
		System.out.println("입력값을 입력해주세요");

		while (run) {
			System.out.println("----------------");
			System.out.println("1.예금 | 2.출금 | 3.잔고 | 4.종료");
			System.out.println("----------------");
			System.out.println("선택>");

			switch (scanner.nextByte()) {

			case 1:
				System.out.println("예금액> ");
				// 입력받은 값을 잔고에 추가
				balance += scanner.nextInt();
				break;

			case 2:
				System.out.println("출금액> ");
				balance -= scanner.nextInt();
				break;

			case 3:
				System.out.println("잔고>" + balance);
				break;

			case 4:
				run = false;
				break;
			}

		}

		System.out.println("프로그램 종료");
	}

}
