import { createSlice, PayloadAction } from "@reduxjs/toolkit";
// import { stat } from "fs";

export interface ContactItem {
  id: number;
  username: string;
  mail: string;
  tellnumber: string;
  description?: string;
  memo?: String;
  createdTime: number;
}

interface ContactState {
  data: ContactItem[]; // 포토 아이템배열
  isFetched: boolean; // 서버에서 데이터를 받아온지에 대한 정보
  isAddCompleted?: boolean;
  isRemoveCompleted?: boolean;
  isModifyCompleted?: boolean; // 데이터 수정이 완료되었는지 여부
}

const initialState: ContactState = {
  data: [],
  isFetched: false,
};

const contactSlice = createSlice({
  name: "contact",
  initialState,
  reducers: {
    addContact: (state, action: PayloadAction<ContactItem>) => {
      const contact = action.payload;
      console.log("--in reducer function--");
      console.log(contact);
      state.data.unshift(contact);
      state.isAddCompleted = true; // 추가가 되었음으로 표시
    },

    initialCompleted: (state) => {
      delete state.isAddCompleted;
      delete state.isRemoveCompleted;
      delete state.isModifyCompleted;
    },

    // payload로 id값을 받음
    // action: PayloadAction<number>
    // reducer 넘어오는 action은 payload에 있는 액션인데,
    // payload의 타입이 number이다
    removeContact: (state, action: PayloadAction<number>) => {
      const id = action.payload;
      // id에 해당하는 아이템의 index를 찾고 그 index로 splice를 한다.
      state.data.splice(
        state.data.findIndex((item) => item.id === id),
        1
      );
      state.isRemoveCompleted = true; // 삭제 되었음을 표시
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
      state.isModifyCompleted = true; // 변경 되었음을 표시
    },

    initialContact: (state, action: PayloadAction<ContactItem[]>) => {
      const contacts = action.payload;
      // 백엔드에서 받아온 데이터
      state.data = contacts;
      // 데이터를 받아옴으로 값을 남김
      state.isFetched = true;
    },
  },
});

export const {
  addContact,
  removeContact,
  modifyContact,
  initialContact,
  initialCompleted,
} = contactSlice.actions;

export default contactSlice.reducer;
