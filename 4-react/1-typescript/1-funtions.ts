// 함수의 매개변수 
// function 함수명(매개변수1: 타입, 매개변수2: 타입): 함수의 반환타입 {
//...
//}
function sum (a : number, b: number ) : number {
  return  a+b;
}


console.log(sum(1,2)); 
// console.log(sum("1","2")); // type error, number 타입만 매개변수로 가능함 


// 첫번째 글자를 대문자로 반환하는 함수 
function capitalize(str: string) : string {
 // 매개변수가 문자열인것을 인지함 
 // 자동완성하여 사용할수 있게됨 
  return str[0].toUpperCase() +  str.substring(1); 
}

console.log(capitalize("javascript"));