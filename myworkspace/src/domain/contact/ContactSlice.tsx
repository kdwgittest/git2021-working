import { createSlice, PayloadAction } from "@reduxjs/toolkit";


export interface ContactItem {
  id: number;
  username: string; 
  mail: string;
  tellnumber: string;
  description?: string; 
  memo ?: String;
  createdTime: number;
}

interface ContactState {
  data: ContactItem[],   // 포토 아이템배열 
  isFetched: boolean;    // 서버에서 데이터를 받아온지에 대한 정보
}

const initialState: ContactState = {
  data: [
    {
      id: 2, // id는 숫자이거나, 증가되는 문자열
      username: "dowon k",
      mail: "pilotkdw999@naver.com",
      tellnumber: "010-123-123",
      // description: "세 마리 펭귄들의 대화",
      memo:"memo",
      createdTime: new Date().getTime(),
    },
    {
      id: 1,
      username: "dowon k",
      mail: "pilotkdw999@naver.com",
      tellnumber: "010-123-123",
      // description: "세 마리 펭귄들의 대화",
      memo:"memo",
      createdTime: new Date().getTime(),
    },

  ],
  isFetched: false,
};


const contactSlice = createSlice({
  name: "contact",
  initialState,
  reducers: {
    addContact: (state, action: PayloadAction<ContactItem>) => {
      const contact = action.payload;
      state.data.unshift(contact); 
    },
     // payload로 id값을 받음 
     // action: PayloadAction<number>
     // reducer 넘어오는 action은 payload에 있는 액션인데,
     // payload의 타입이 number이다
    removeContact: (state, action: PayloadAction<number>) => {
      const id = action.payload;
      // id에 해당하는 아이템의 index를 찾고 그 index로 splice를 한다. 
      state.data.splice(state.data.findIndex(item => item.id === id),1)
    },
   
    modifyContact: (state, action: PayloadAction<ContactItem>) => {
      // 생성해서 넘긴 객체
      const modifyItem = action.payload;
      // state에 있는 객체
      const contactItem = state.data.find((item) => item.id === modifyItem.id);
      // state에 있는 객체의 속성을 넘김 객체의 속성으로 변경
      if (contactItem) {
        contactItem.mail = modifyItem.mail;
        contactItem.description = modifyItem.description;
        contactItem.username = modifyItem.username;
        contactItem.tellnumber = modifyItem.tellnumber;
        contactItem.createdTime = modifyItem.createdTime; 
     
      }
    },

  },
}); 


export const { addContact, removeContact, modifyContact } = contactSlice.actions; 

export default contactSlice.reducer; 