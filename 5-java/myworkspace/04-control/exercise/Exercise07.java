import java.util.Scanner;

public class Exercise07 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean run = true;

		int balance = 0;

		Scanner scanner = new Scanner(System.in);
		System.out.println("�Է°��� �Է����ּ���");

		while (run) {
			System.out.println("----------------");
			System.out.println("1.���� | 2.��� | 3.�ܰ� | 4.����");
			System.out.println("----------------");
			System.out.println("����>");

			switch (scanner.nextByte()) {

			case 1:
				System.out.println("���ݾ�> ");
				// �Է¹��� ���� �ܰ� �߰�
				balance += scanner.nextInt();
				break;

			case 2:
				System.out.println("��ݾ�> ");
				balance -= scanner.nextInt();
				break;

			case 3:
				System.out.println("�ܰ�>" + balance);
				break;

			case 4:
				run = false;
				break;
			}

		}

		System.out.println("���α׷� ����");
	}

}
