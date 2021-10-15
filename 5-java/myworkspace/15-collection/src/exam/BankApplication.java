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
			System.out.println("1.���»��� | 2.���¸�� | 3.���� | 4.��� | 5.����");
			System.out.println("----------------------------------------------------------");
			System.out.print("����> ");

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
		System.out.println("���α׷� ����");

	}

	// ���»����ϱ�(�����߰��ϱ�)
	private static void createAccount() {
		System.out.println("----------------");
		System.out.println("-���»���-");

		System.out.println("���¹�ȣ> ");
		String ano = scanner.next();

		System.out.println("������> ");
		String owner = scanner.next();

		System.out.println("�ʱ��Աݾ�> ");
		int balance = scanner.nextInt();

		Account In = new Account(ano, owner, balance);

		accounts.put(ano, In);

	}

	// ���¸�Ϻ���
	private static void accountList() {

		System.out.println("----------------");
		System.out.println("-��� ����-");
		for (String ano : accounts.keySet()) {
			String owner = accounts.get(ano).getOwner();
			int balance = accounts.get(ano).getBalance();

			System.out.println("���¹�ȣ> " + ano);
			System.out.println("������> " + owner);
			System.out.println("�ܰ�> " + balance);

		}

	}

	// �����ϱ�(�ʵ尪����)
	private static void deposit() {

		System.out.println("----------------");
		System.out.println("���¹�ȣ> ");
		String ano = scanner.next();

		if (accounts.containsKey(ano)) {
			System.out.println("�Ա��� �ݾ��� �Է��ϼ���.");
			System.out.println("�Աݾ�> ");
			int inoutMoney = scanner.nextInt();

			Account account = accounts.get(ano);
			account.setBalance(account.getBalance() + inoutMoney);

			System.out.print("�ܰ�> ");
			System.out.println(account.getBalance());

		}

		else {
			System.out.println("�ش� ���°� �����ϴ�. ");
		}

		System.out.println("-���� ����-");

	}

	// ����ϱ�(�ʵ尪����)
	private static void withdraw() {

		System.out.println("----------------");
		System.out.println("���¹�ȣ> ");
		String ano = scanner.next();

		if (accounts.containsKey(ano)) {
			System.out.println("����� �ݾ��� �Է��ϼ���.");
			System.out.println("��ݾ�> ");
			int inoutMoney = scanner.nextInt();
			Account account = accounts.get(ano);
			if (account.getBalance() < inoutMoney) {
				System.out.println("�ܾ��� �����մϴ�. ");
				return;
			}

			account.setBalance(account.getBalance() - inoutMoney);

			System.out.print("�ܰ�> ");
			System.out.println(account.getBalance());
			System.out.println("-��� ����-");

		}

		else {
			System.out.println("�ش� ���°� �����ϴ�. ");
		}

	}
}
