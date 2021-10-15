package exercise13;

public class ShopService {

	// static 객체 변수 선언
	static ShopService instance;

	// 객체생성 못하게 프라이빗으로
	private ShopService() {
	}

	// 객체 반환하는 메서드
	public static ShopService getInstance() {
		// null 일때만 객체를 한번 생성함
		// 그 다음부터는 이전에 생성된 객체를 반환
		if (instance == null) {
			instance = new ShopService();
		}

		return instance;
	}

}
