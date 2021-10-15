// import { memo, useRef, useState } from "react";

// // import Alert from "../components/Alert";
// // import { table } from "console";
// import produce from "immer";
// // import { isTemplateExpression } from "typescript";

// interface ContactState {
//   id: number;
//   name: string | undefined;
//   email: string | undefined;
//   tell: string | undefined; 
//  // dataUrl: string | undefined; 
// }
//   export type { ContactState };


// const ContactCreate = () => {
//    const [contactList, setContactList] = useState<ContactState[]>([]); 
    
//  // const [isError, setIsError] = useState(false);

// const tellRef = useRef<HTMLInputElement>(null);
// const nameRef = useRef<HTMLInputElement>(null);
// const emailRef = useRef<HTMLInputElement>(null);

// const textRef = useRef<HTMLTextAreaElement>(null);

// const formRef = useRef<HTMLFormElement>(null);  



// const add = () => {


//   const contact: ContactState = {
//     id: contactList.length > 0 ? contactList[0].id + 1 : 1,
   
//    // content: textRef.current?.value 
//      tell: tellRef.current?.value, 
//      email: emailRef.current?.value, 
//      name: nameRef.current?.value
//   };
//   setContactList(
//     produce((state) => {
//       state.unshift(contact);
//     })
//   );

//   formRef.current?.reset();

// }; 
// // 입력값 초기화
 
//   const del = (id: number, index: number) => {
//     console.log(id);
//     setContactList(
//       produce((state) => {
//         state.splice(index, 1);
//       })
//     );
//   };

  

// return (
//   <>
//   <h2 className="text-center my-5">연락처 관리</h2> 

// <form className="d-flex">
//     <input
//       type="text"
//       className="form-control me-2"
//       placeholder="이름"
//       ref={nameRef}
//     />

//     <input
//       type="text"
//       className="form-control me-2"
//       placeholder="이메일"
//       ref={emailRef}
//     />

//   <input
//     type="text"
//     className="form-control me-2"
//     placeholder="전화번호"
//     ref={tellRef}
//   />

//   <button
//     type="button"
//     className="btn btn-primary text-nowrap"
//    onClick={() => {
//      add();
//        }}>
//     입력 
//   </button>
// </form>

// <table className="table table-striped">

//   <thead>
//      <tr>
//        <th scope="col">이름</th>
//        <th scope="col">이메일</th>
//        <th scope="col">전화번호</th>
//        <th scope="col">작업</th>
//      </tr>
//   </thead>
//   {contactList.map((item,index) => (
//     <tbody>
//        <tr key={item.id}>
//          <td>{item.name}</td>
//          <td>{item.email}</td>
//          <td>{item.tell}</td>
        
//             <button
//               className="btn btn-outline-secondary btn-sm text-nowrap"
//               onClick={() => {
//                 del(item.id, index);
//               }}
//             >
//               삭제
//             </button>
//          </tr>
//        </tbody>
//   ))}
  
// </table>
// </>
// )
// } 


// export default ContactCreate; 


import { useRef } from "react";
import { useSelector, useDispatch } from "react-redux";
import { useHistory } from "react-router-dom";
import { AppDispatch, RootState } from "../../store";
import { addContact, ContactItem } from "./ContactSlice";

const ContactCreate = () => {

  const nameInput = useRef<HTMLInputElement>(null);
  const tellNumber = useRef<HTMLInputElement>(null);
  const textMail = useRef<HTMLInputElement>(null);
  const descTxta = useRef<HTMLTextAreaElement>(null);



  const contactData = useSelector((state: RootState)=> state.contact.data);

  const dispatch = useDispatch<AppDispatch>(); 

  const history = useHistory();

  const handleAddClick = () => {
   
         const item: ContactItem = {
          id: contactData.length ? contactData[0].id + 1 : 1,
          username: nameInput.current ? nameInput.current.value : "", 
          mail: textMail.current ? textMail.current.value : "",
          tellnumber: tellNumber.current ? tellNumber.current.value : "",
          description: descTxta.current?.value,
          createdTime: new Date().getTime(),  
          
         };
       
         console.log(item); 

         dispatch(addContact(item)); 
         history.push("/contact");
      };
    
  

  return (
     <div style= {{width:"40vw"}} className="mx-auto">
       <h2 className="text-center">Contact Create</h2>
        <form>
          <table className="table">
            <tbody>
              <tr>
                <th>이름</th>
                <td>
                  <input className="form-control" type="text" ref={nameInput} />
                </td>
              </tr>
              <tr>
                <th>전화번호</th>
                <td>
                  <input className="form-control" type="text" ref={tellNumber} />
                </td>
            </tr>
            <tr>
                <th>이메일</th>  
                <td>
                  <input
                     className="form-control"
                     type="text"
                     ref={textMail} /> 
                </td>              
            </tr> 
            <tr>
              <th>메모 </th>
                <textarea
                className="formcontrol"
                style={{width: "32vh"}}
                ref={descTxta} />
            </tr>
        </tbody>
       </table>
     </form>

       <div>
         <button 
           className="btn btn-secondary float-start"
           onClick={()=> {
             history.push("/contact"); 
           }} >
             <i className="bi bi=grid-3x3-gap me-1"></i>
             목록 
           </button>
         <button className="btn btn-primary float-end"
           onClick={() => {
             handleAddClick(); 
           }}  
         >
            <i className="bi bi-check" />
            저장 
         </button>
       </div>
     </div>
 );
};

export default ContactCreate; 