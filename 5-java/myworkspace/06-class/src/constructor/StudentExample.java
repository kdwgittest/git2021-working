package constructor;

public class StudentExample {

	public static void main(String[] args) {

		// 생성자 메서드를 실행하여 객체를 만들어라
		// Student 라는 생성자 메서드를 실행해서 Student 클래스 구조의 새로운 객체를 만들고
		// 형식의 s1 변수에 대입하라

		// 자바에서는 거이 쓰지 않는 방법
		// 객체를 생성하고 필드에 값을 대입
		Student s1 = new Student();
		s1.name = "홍길동";
		s1.age = 20;
		s1.semester = 1;
		s1.major = "컴퓨터공학";

		// 자바에서 객체를 생성하는 한가지 방법
		// 생성자로 필드를 초기화하여 생성
		Student s2 = new Student("존스미스", 21);
		System.out.println(s2.name + " " + s2.age);
		s2.semester = 3;
		s2.major = "경영학";

		// 다른 방법으로 객체 생성
		Student s3 = new Student("김도원", 23, 3, "자바웹");
		System.out.println(s3.name + " " + s3.age);

	}

}
