import Header from './Header';
import Button from './Button';


const components = () => {
  return <div>
      { /* <Header /> */ }
    {/* 내부에서 주석 달기 */} 
    {/* <h1 style={{color:"red"}}>Hello React with Typescript!</h1>  */}  
     {/* 속성값을 변경하여 재사용하는 컴포넌트 */}
     {/* 컴포넌트의 속성을 넘김 */} 
     {/* 속성명={속성값} */}
     <Header color={"red"} title={"React"} />
     <Header color={"green"} title={"javascript"} />
     <Header color={"blue"} title={"Function Component"} />      
     
     <Button variant={"primary"} text={"Add"} />
     <Button variant={"secondary"} text={"Typescript"} />
     <Button variant={"warning"} text={"Funtion Component"} />


  </div>
}



export default components; 