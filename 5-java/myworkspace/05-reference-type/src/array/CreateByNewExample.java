package array;

public class CreateByNewExample {

	public static void main(String[] args) {

		int[] arrInt = new int[3];
		System.out.println(arrInt[0]); // 0�ʱ�ȭ �Ǿ�����
		System.out.println(arrInt[1]);
		System.out.println(arrInt[2]);

		for (int i = 0; i < 3; i++) {
			System.out.println(arrInt[i]);
		}

		String[] arrStr = new String[3];
		System.out.println(arrStr[0]); // null �ʱ�ȭ �Ǿ�����
		System.out.println(arrStr[1]); // null �ʱ�ȭ �Ǿ�����
		System.out.println(arrStr[2]); // null �ʱ�ȭ �Ǿ�����

		// for(���Ÿ�� ��Һ��� : �迭������)
		for (String str : arrStr) {
			str = "test";
			System.out.println(str);
		}

		// �迭 ũ��
		System.out.println(arrStr.length);

	}

}