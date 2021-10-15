import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { penguin } from "../../common/data"

export interface PhotoItem {
  id: number;
  profileUrl: string; 
  username: string; 
  title: string;
  description?: string; 
  photoUrl: string; 
  fileType: string;
  fileName: string;
  createdTime: number;
}

interface PhotoState {
  data: PhotoItem[],   // 포토 아이템배열 
  isFetched: boolean;    // 서버에서 데이터를 받아온지에 대한 정보
}

const initialState: PhotoState = {
  data: [
    {
      id: 5, // id는 숫자이거나, 증가되는 문자열
      profileUrl: penguin,
      username: "Daekeun Ko",
      title: "펭귄이",
      description: "세 마리 펭귄들의 대화",
      photoUrl: penguin,
      fileType: "image/jpeg",
      fileName: "penguin.jpg",
      createdTime: new Date().getTime(),
    },
    {
      id: 4,
      profileUrl: penguin,
      username: "Daekeun Ko",
      title: "펭귄이",
      description: "세 마리 펭귄들의 대화",
      photoUrl: penguin,
      fileType: "image/jpeg",
      fileName: "penguin.jpg",
      createdTime: new Date().getTime(),
    },
    {
      id: 3,
      profileUrl: penguin,
      username: "Daekeun Ko",
      title: "펭귄이",
      description: "세 마리 펭귄들의 대화",
      photoUrl: penguin,
      fileType: "image/jpeg",
      fileName: "penguin.jpg",
      createdTime: new Date().getTime(),
    },
    {
      id: 2,
      profileUrl: penguin,
      username: "Daekeun Ko",
      title: "펭귄이",
      description: "세 마리 펭귄들의 대화",
      photoUrl: penguin,
      fileType: "image/jpeg",
      fileName: "penguin.jpg",
      createdTime: new Date().getTime(),
    },
    {
      id: 1,
      profileUrl: penguin,
      username: "Daekeun Ko",
      title: "펭귄이",
      description: "세 마리 펭귄들의 대화",
      photoUrl: penguin,
      fileType: "image/jpeg",
      fileName: "penguin.jpg",
      createdTime: new Date().getTime(),
    },
  ],
  isFetched: false,
};



const photoSlice = createSlice({
  name: "photo",
  initialState,
  reducers: {
    addPhoto: (state, action: PayloadAction<PhotoItem>) => {
      const photo = action.payload;
      console.log("--in reducer function--");
      console.log(photo);
      state.data.unshift(photo); 
    },
     // payload로 id값을 받음 
     // action: PayloadAction<number>
     // reducer 넘어오는 action은 payload에 있는 액션인데,
     // payload의 타입이 number이다
    removePhoto: (state, action: PayloadAction<number>) => {
      const id = action.payload;
      // id에 해당하는 아이템의 index를 찾고 그 index로 splice를 한다. 
      state.data.splice(state.data.findIndex(item => item.id === id),1)
    },
   
    modifyPhoto: (state, action: PayloadAction<PhotoItem>) => {
      // 생성해서 넘긴 객체
      const modifyItem = action.payload;
      // state에 있는 객체
      const photoItem = state.data.find((item) => item.id === modifyItem.id);
      // state에 있는 객체의 속성을 넘김 객체의 속성으로 변경
      if (photoItem) {
        photoItem.title = modifyItem.title;
        photoItem.description = modifyItem.description;
        photoItem.photoUrl = modifyItem.photoUrl;
        photoItem.fileName = modifyItem.fileName;
        photoItem.fileType = modifyItem.fileType;
      }
    },

  },
}); 


export const { addPhoto, removePhoto, modifyPhoto } = photoSlice.actions; 

export default photoSlice.reducer; 