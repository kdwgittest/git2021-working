package array;

public class CreateByNewExample {

	public static void main(String[] args) {

		int[] arrInt = new int[3];
		System.out.println(arrInt[0]); // 0초기화 되어있음
		System.out.println(arrInt[1]);
		System.out.println(arrInt[2]);

		for (int i = 0; i < 3; i++) {
			System.out.println(arrInt[i]);
		}

		String[] arrStr = new String[3];
		System.out.println(arrStr[0]); // null 초기화 되어있음
		System.out.println(arrStr[1]); // null 초기화 되어있음
		System.out.println(arrStr[2]); // null 초기화 되어있음

		// for(요소타입 요소변수 : 배열변수명)
		for (String str : arrStr) {
			str = "test";
			System.out.println(str);
		}

		// 배열 크기
		System.out.println(arrStr.length);

	}

}
