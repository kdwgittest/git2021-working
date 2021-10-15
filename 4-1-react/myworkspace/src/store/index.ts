
import { configureStore } from "@reduxjs/toolkit";
import profileReducer from "../domain/profile/profileSlice";
import photoReducer from "../domain/photo/photoSlice"; 
import contactReducer from "../domain/contact/ContactSlice"; 
import alertReducer from "../components/alert/AlertStack";
import progressReducer from "../components/progress/progressSlice";



// 최상위 사가
import rootSaga from "../saga";
import createSagaMiddleware from "@redux-saga/core";

// saga middleware 생성
// middleware: 중간에 먼가를 처리하는 소프트웨어
// redux saga는 redux 상태처리 전/후에 먼가를 해주는 라이브러리
const sagaMiddleware = createSagaMiddleware(); 

// global state 저장소 만듬 
export const store = configureStore ({
  reducer: {
    // reducer 등록 
    profile: profileReducer,
    photo: photoReducer,
    contact: contactReducer,
    progress: progressReducer,
    alert: alertReducer

  },          // 각 state 별로 처리할 reduce 목록 
 
 // redux store(dispatcher)에 미들웨어 적용
  // middleware는 여러개 사용할 수 있음, [defaultMiddlware, sagaMiddleware, thunkMiddlware]
  middleware: [sagaMiddleware],
  devTools: true,      // 개발툴 사용여부 
});

// saga middleware를 실행
// rootSaga와 하위에 정의한 감지(take)할 Saga Action들에 대해서 감지 시작
sagaMiddleware.run(rootSaga);

// root state 타입 정의 
// 가장 최상위 state 
export type RootState = ReturnType<typeof store.getState>;

// dispatch 타입 정의 
// dispatch 함수의 generic type  
export type AppDispatch = typeof store.dispatch; 