package extends_keyword;

// 관리자 
// 자식클래스 extends 부모클래스 
public class Admin extends User {
	private String deptNo;

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
}
