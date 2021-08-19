// interface: 객체구조의 형식
//interface 타입명 {
//  속성명: 타입;
//  속성명: 타입; 
// } 

interface User {
  firstname: string;
  // 잉여속성 
  lastname?: string;   //속성명? : 옵션속성, 필수값이 아닌 옵션 
}

function printName(obj: User) {
   console.log(obj.firstname + " " + obj.lastname); 
}

// 타입명[]
//number[], string[]
function printNames(arr: User[]) {

}

const user: User = {
   firstname: "John",
 //  lastname: "Smith"       
};

const users: User[] = [
  {firstname: "John", lastname: "Smith" },
  {firstname: "kim", lastname: "lee"}
];


printName(user);
printNames(users);
