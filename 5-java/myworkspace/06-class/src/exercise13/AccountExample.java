package exercise13;

public class AccountExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Account account = new Account();

		account.setBalance(10000);

		if (account.getBalance() == 10000) {
			System.out.println("케이스통과 - pass");
		} else {
			System.out.println("케이스실패 - fail");
		}

//		System.out.println("현재잔고: " + account.getBalance());

		// when 테스트 데이터로 테이트 케이스 실행
		int expectedvalue = account.getBalance();
		account.setBalance(-100);
		if (account.getBalance() == expectedvalue) {
			System.out.println("케이스통과 - pass");
		} else {
			System.out.println("케이스실패 - fail");
		}

		System.out.println("현재잔고: " + account.getBalance());

		account.setBalance(2000000);
		System.out.println("현재잔고: " + account.getBalance());

		account.setBalance(300000);
		System.out.println("현재잔고: " + account.getBalance());

	}

}
