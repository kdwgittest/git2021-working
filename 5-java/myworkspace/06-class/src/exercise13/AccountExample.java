package exercise13;

public class AccountExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Account account = new Account();

		account.setBalance(10000);

		if (account.getBalance() == 10000) {
			System.out.println("���̽���� - pass");
		} else {
			System.out.println("���̽����� - fail");
		}

//		System.out.println("�����ܰ�: " + account.getBalance());

		// when �׽�Ʈ �����ͷ� ����Ʈ ���̽� ����
		int expectedvalue = account.getBalance();
		account.setBalance(-100);
		if (account.getBalance() == expectedvalue) {
			System.out.println("���̽���� - pass");
		} else {
			System.out.println("���̽����� - fail");
		}

		System.out.println("�����ܰ�: " + account.getBalance());

		account.setBalance(2000000);
		System.out.println("�����ܰ�: " + account.getBalance());

		account.setBalance(300000);
		System.out.println("�����ܰ�: " + account.getBalance());

	}

}
