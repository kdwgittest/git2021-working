package classnew;

public class StudentExample {

	public static void main(String[] args) {
		// new Ŭ������();
		// �ν��Ͻ� ���� -> Ŭ���� ������ �޸� ������ ����
		// �ν��Ͻ� == ��ü
		// Student s1 = ������ �ν��Ͻ�

		Student s1 = new Student();
		// �ʵ�: ȫ�浿 �л��� ������
		s1.name = "ȫ�浿";
		s1.age = 20;
		s1.semester = 2;
		s1.major = "�İ�";
		System.out.println(s1.name + " " + s1.age);
		// �޼���: ȫ�浿�̶�� �л��� ������û ���
		s1.joinCourse();

		Student s2 = new Student();
		s2.name = "abc";
		s2.age = 21;
		s2.semester = 3;
		s2.major = "�濵";
		System.out.println(s2.name + " " + s2.age);
		s2.joinCourse();

	}

}
