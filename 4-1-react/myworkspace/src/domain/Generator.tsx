// Generator 
// 숫자값을 랜덤 -50 ~ 50 범위로 생성하고 
// 배열 state 에 추가 
// 숫자 목록을  ul > li 로 출력

import { useState } from "react";

 
// 기존 javascript 
// DOM 요소를 직접적으로 조작 



const Generator = () => {
   // useState<타입>
   // state의 타입을 저장해줄 수 있음 
   const [numbers, setNumber] = useState<number[]>([]); 

   const generate = () => {
      const num = Math.trunc(Math.random()*100-50);
      
      // number, string, boolean // 값이 바뀌어야만 다시 렌더링함 
      // object, array // 참조가 바뀌어야만 다시 렌더링함 
      // object -> 새로운 객체를 생성하고  state변경함수를 실행함 
      // array -> 새로운 배열을 생성하고 state함수로 변경함 

   // [0,1,2,3]   

  // []: 새로운 배열 생성
  // []

  // [...numbers]: 기존 배열 복사, ... 나열 연산 
  // [0,1,2,3]
  
  // [num, ...numbers]: 새로운 배열에 첫번째요소로 num값, 나머지는 기존 배열 
   //[-17,0,1,2,3]
   // [...numders, num]:은 반대로 [0,1,2,3,-17]


   //: 새로운 배열에 첫번쨰요소로 num값, 나머지는 기존 배열 

     setNumber([num, ...numbers]);
   }; 

   

     return (
     <div>
       <h2>Generator</h2>
       <button onClick={()=> {
         generate();
       } }>GENERATE</button>
       <div>{numbers}</div>
       {/* JSX에선 중괄호로 코드를 침 */}
       <ul>
         {   
           // JSX 내부에서는 한줄짜리 코드식만 가능함 
           // 세미콜론을 한번만 쓸 수 있는 코드 
           
           // map: 기존 배열크기와 동일하나 요소가 변경된 배열을 반환 
           // 숫자배열을 -> JSX 배열로 변환 
            numbers.map((num, index) => (
               <li key={index}>num</li>
            )
            )

            } 
       </ul>
     </div>

     );

}; 


export default Generator;



