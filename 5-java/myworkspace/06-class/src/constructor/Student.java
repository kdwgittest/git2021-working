package constructor;

public class Student {

	String name;
	int age;
	int semester;
	String major;

	public Student() {
		// �ƹ��͵� ó������ , ��ü�� ��������
	}

	Student(String name, int age) {
		this.name = name;
		this.age = age;
		// �ڹٿ��� �� ������� �ʴ� ���
	}

	Student(String name, String major) {

	}

	Student(int age, String name) {

	}

	// �̸�, ����, �б�, �а� �ް� �ʵ� �ʱ�ȭ �� ��ü ����
	Student(String name, int age, int semester, String major) {
		this.name = name;
		this.age = age;
		this.semester = semester;
		this.major = major;
	}

	void joinCourse() {

	}

}
