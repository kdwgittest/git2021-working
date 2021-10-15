package exam;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankApplication {

	private static Map<String, Account> accounts = new HashMap<String, Account>();

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		boolean run = true;
		while (run) {
			System.out.println("----------------------------------------------------------");
			System.out.println("1.계좌생성 | 2.계좌목록 | 3.예금 | 4.출금 | 5.종료");
			System.out.println("----------------------------------------------------------");
			System.out.print("선택> ");

			int selectNo = scanner.nextInt();

			if (selectNo == 1) {
				createAccount();
			} else if (selectNo == 2) {
				accountList();
			} else if (selectNo == 3) {
				deposit();
			} else if (selectNo == 4) {
				withdraw();
			} else if (selectNo == 5) {
				run = false;
			}
		}
		System.out.println("프로그램 종료");

	}

	// 계좌생성하기(계좌추가하기)
	private static void createAccount() {
		System.out.println("----------------");
		System.out.println("-계좌생성-");

		System.out.println("계좌번호> ");
		String ano = scanner.next();

		System.out.println("계좌주> ");
		String owner = scanner.next();

		System.out.println("초기입금액> ");
		int balance = scanner.nextInt();

		Account In = new Account(ano, owner, balance);

		accounts.put(ano, In);

	}

	// 계좌목록보기
	private static void accountList() {

		System.out.println("----------------");
		System.out.println("-목록 보기-");
		for (String ano : accounts.keySet()) {
			String owner = accounts.get(ano).getOwner();
			int balance = accounts.get(ano).getBalance();

			System.out.println("계좌번호> " + ano);
			System.out.println("계좌주> " + owner);
			System.out.println("잔고> " + balance);

		}

	}

	// 예금하기(필드값수정)
	private static void deposit() {

		System.out.println("----------------");
		System.out.println("계좌번호> ");
		String ano = scanner.next();

		if (accounts.containsKey(ano)) {
			System.out.println("입금할 금액을 입력하세요.");
			System.out.println("입금액> ");
			int inoutMoney = scanner.nextInt();

			Account account = accounts.get(ano);
			account.setBalance(account.getBalance() + inoutMoney);

			System.out.print("잔고> ");
			System.out.println(account.getBalance());

		}

		else {
			System.out.println("해당 계좌가 없습니다. ");
		}

		System.out.println("-예금 성공-");

	}

	// 출금하기(필드값수정)
	private static void withdraw() {

		System.out.println("----------------");
		System.out.println("계좌번호> ");
		String ano = scanner.next();

		if (accounts.containsKey(ano)) {
			System.out.println("출금할 금액을 입력하세요.");
			System.out.println("출금액> ");
			int inoutMoney = scanner.nextInt();
			Account account = accounts.get(ano);
			if (account.getBalance() < inoutMoney) {
				System.out.println("잔액이 부족합니다. ");
				return;
			}

			account.setBalance(account.getBalance() - inoutMoney);

			System.out.print("잔고> ");
			System.out.println(account.getBalance());
			System.out.println("-출금 성공-");

		}

		else {
			System.out.println("해당 계좌가 없습니다. ");
		}

	}
}
