package conversion;

public class CastingExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int intValue = 44032;
        
        // 값 손실이 없는 상태 
        char charValue = (char) intValue;
        System.out.println(charValue); 
        
        double doubleValue = 3.14;
        intValue = (int) doubleValue; 
        System.out.println(intValue); 
        
        
	}

}
