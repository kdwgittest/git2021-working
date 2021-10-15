package array;

public class CreateByValuesExample {

	public static void main(String[] args) {
		// 정수형 배열 선언 및 초기화
		int[] scores = { 83, 90, 87 };

		System.out.println(scores[0]);
		System.out.println(scores[1]);
		System.out.println(scores[2]);

		// 배열 요소의 추가삭제 불가능함
		System.out.println("-------값 변경-------");
		scores[0] = 100;
		System.out.println(scores[0]);
		System.out.println(scores[1]);
		System.out.println(scores[2]);
		scores[0] = 0; // 기본형 데이터 타입은 null 처리가 불가능함

		String[] languages = { "Java", "Typescript", "HTML", "CSS" };

	}

}
