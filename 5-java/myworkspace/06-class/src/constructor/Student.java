package constructor;

public class Student {

	String name;
	int age;
	int semester;
	String major;

	public Student() {
		// 아무것도 처리안함 , 객체만 생성만함
	}

	Student(String name, int age) {
		this.name = name;
		this.age = age;
		// 자바에서 잘 사용하지 않는 방법
	}

	Student(String name, String major) {

	}

	Student(int age, String name) {

	}

	// 이름, 나이, 학기, 학과 받고 필드 초기화 및 객체 생성
	Student(String name, int age, int semester, String major) {
		this.name = name;
		this.age = age;
		this.semester = semester;
		this.major = major;
	}

	void joinCourse() {

	}

}
