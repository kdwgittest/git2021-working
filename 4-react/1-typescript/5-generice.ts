// 제너릭 함수 
// 타입 매개변수 
// 다양한 타입에 따라서 실행 처리를 다르게 하기위함  
//

function identity<Type>(arg: Type): Type {
  // 타입에 따라서 내부코드를 분기함 
  // 넘버면 덧셈하고 스트링이면 구분자로 결합함 

   
  return arg;
}

                   // 함수에 타입이 매개변수가 올 수 있다. 
let output1= identity<string>("Typescript");
let output2= identity<number>(1);