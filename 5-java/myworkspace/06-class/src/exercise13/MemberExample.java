package exercise13;

import constructor.Student;

public class MemberExample {

	public static void main(String[] args) {

		// �̸�, id�� �Ű������� �޾Ƽ� ��ü ����
		// �ش��ϴ� �����ڸ� ����
		Member member1 = new Member("ȫ�浿", "hong");
		Member member2 = new Member("���ڹ�", "java");

		Student student = new Student();
//		constructor.Student = new constructor.Student(); 

		System.out.println(member1.name + " " + member1.id);
		System.out.println(member2.name + " " + member2.id);

	}

}
