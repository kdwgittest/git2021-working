import { useSelector } from "react-redux";
import { useHistory } from "react-router";
import { RootState } from "../../store";

const getTimeString = (unixtime: number) => {
  const dateTime = new Date(unixtime);
  return `${dateTime.toLocaleDateString()} ${dateTime.toLocaleTimeString()}`;
};

const Contact = () => {
  const contact = useSelector((state: RootState) => state.contact);
  const history = useHistory();
  
return (
  <div>
  <h2 className="text-center">Contact</h2>
  <div className="d-flex justify-content-end mb-2">
    <button
      className="btn btn-primary"
      onClick={() => {
        history.push("/contact/create");    
      }}
    >
      <i className="bi bi-plus" />
      추가
    </button>
  </div>

<form>
  <table className="table table-striped table-hover w-100">
  <thead> 
      <tr>
         <th>번호</th>
         <th>이름</th>
         <th>전화번호</th>
         <th>이메일</th>
         <th>작성시간</th>
       </tr>   
     </thead>
   
    {contact.data.map((item, index) => (
         <tbody>
           <tr key={`contact-item-${index}`} style={{ cursor: "pointer" }} onClick={()=>{
              history.push(`/contact/detail/${item.id}`);  
             }}> 
             <td>{item.id}</td>
             <td>{item.username}</td>
             <td>{item.tellnumber}</td>
             <td>{item.mail}</td>
             <td>{getTimeString(item.createdTime)}</td>
           
           </tr>           
        </tbody>
      )  
    )}
  </table>
  </form>
</div>
);

};

export default Contact; 