package exercise13;

public class ShopService {

	// static ��ü ���� ����
	static ShopService instance;

	// ��ü���� ���ϰ� �����̺�����
	private ShopService() {
	}

	// ��ü ��ȯ�ϴ� �޼���
	public static ShopService getInstance() {
		// null �϶��� ��ü�� �ѹ� ������
		// �� �������ʹ� ������ ������ ��ü�� ��ȯ
		if (instance == null) {
			instance = new ShopService();
		}

		return instance;
	}

}
