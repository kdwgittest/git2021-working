import axios from "axios";
// import { access } from "fs";

// 서버로부터 받아오는 데이터 1건의 타입 
export interface ContactItemResponse {
  id: number;
  username: string; 
  mail: string;
  tellnumber: string;
  description?: string; 
  memo ?: String;
  createdTime: number;
}

export interface ContactItemRequest {
  username: string; 
  mail: string;
  tellnumber: string;
  description?: string; 
  memo ?: String;
  createdTime: number;
}


// api 처리 연동 
const contactApi = {

  fetch: () => 
  axios.get<ContactItemResponse[]>(`${process.env.REACT_APP_API_BASE}/contact`),

add: (contactItem: ContactItemRequest) => 
  axios.post<ContactItemResponse>(
    `${process.env.REACT_APP_API_BASE}/contact`,
    contactItem
  ),

  remove: (id: number) => 
    axios.delete<boolean>(`${process.env.REACT_APP_API_BASE}/contact/${id}`),

    modify: (id: number, contactItem: ContactItemRequest) => 
      axios.put<ContactItemResponse> (
        `${process.env.REACT_APP_API_BASE}/contact/${id}`,
        contactItem
      ), 
}; 

export default contactApi; 