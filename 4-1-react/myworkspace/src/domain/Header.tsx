// 컴포넌트 정의
// 리액트 컴포넌트 = JSX를 반환하는 함수
// <></>: 조각 
// 어떤 태그형식으로도 변환되지 않음, 빈 태그 

// 컴포넌트에 속성으로 color, title을 받을 것임 
// 컴포넌트의 속성: 함수의 매개변수처럼 외부에서 넘겨받는 변수 

 interface HeaderProp {
  // color: string;
  color: "green" | "red" | "blue"; 
  title: string;
 }

 // const 함수명 : React.FC<속성타입> = ({속성1, 속성2}) => {
// ...
//}
const Header: React.FC<HeaderProp> = ({color, title})=> {
  return <h1 style={{color:color }}>{title}</h1>  ;
   // 여러개의 부모엘리먼트를 반환하지 못함 
}

// 컴포넌트 내보내기 
export default Header;