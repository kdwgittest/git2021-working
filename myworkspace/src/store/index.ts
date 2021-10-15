
import { configureStore } from "@reduxjs/toolkit";
import profileReducer from "../domain/profile/profileSlice";
import photoReducer from "../domain/photo/photoSlice"; 
 import contactReducer from "../domain/contact/ContactSlice"; 




// global state 저장소 만듬 
export const store = configureStore ({
  reducer: {
    profile: profileReducer,
    photo: photoReducer,

    contact: contactReducer,


  },          // 각 state 별로 처리할 reduce 목록 
 

  devTools: true,      // 개발툴 사용여부 
});

// root state 타입 정의 
// 가장 최상위 state 
export type RootState = ReturnType<typeof store.getState>;

// dispatch 타입 정의 
// dispatch 함수의 generic type  
export type AppDispatch = typeof store.dispatch; 