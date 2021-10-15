import { AxiosResponse } from "axios";
import api, { ContactItemRequest, ContactItemResponse } from "./contactApi";
import { ContactItem  } from "./ContactSlice";
import { createAction, PayloadAction } from "@reduxjs/toolkit";
import contactReducer, { addContact, removeContact, initialContact, initialCompleted } from "./ContactSlice";
import { call, put, takeEvery, takeLatest } from "@redux-saga/core/effects";


/* --------------------------saga 생성부분-----------------------------*/

// contact 를 추가 action creator
export const requestAddContact = createAction<ContactItem>(
  `${contactReducer.name}/requestAddContact`
); 

// contact 가져오는 action 
export const requsetFetchContacts = createAction(
  `${contactReducer.name}/requestFetchContacts`
);
// 삭제 
export const requestRemoveContact = createAction<number>(
  `${contactReducer.name}/requestRemoveContact`
);

// // 수정하는 action
export const requestModifyContact = createAction<ContactItem>(
  `${contactReducer.name}/requestModifyContact`
);

/*------------------------------saga action 처리부분------------------------------*/ 
 // post로 데이터 추가 
function* addData(action: PayloadAction<ContactItem>) {
  yield console.log("-addData-");
  yield console.log(action);

  // action의 payload로 넘어온 객체 
  const contactItemPayload = action.payload;
// 보낼 객체 
  const contactItemRequest: ContactItemRequest = {
      username:contactItemPayload.username,
      mail:contactItemPayload.mail,
      tellnumber:contactItemPayload.tellnumber,
      description:contactItemPayload.description,
      memo:contactItemPayload.memo,
      createdTime:contactItemPayload.createdTime
  };

  const result: AxiosResponse<ContactItemResponse> = yield call (
    api.add,
    contactItemRequest
  );

  // 백엔드에서 처리한 데이터로 state를 변경할 payload 객체를 생성 
  const contactItem: ContactItem = {
    id: result.data.id,
    username: result.data.username,
    mail: result.data.mail,
    tellnumber: result.data.tellnumber,
    description: result.data.description,
    memo: result.data.memo,
    createdTime: result.data.createdTime
  };
  // put 
  yield put(addContact(contactItem));
  yield put(initialCompleted());
}

// 서버에서 GET 으로 데이터를 가져오고 redux state를 초기화 
function* fetchData() {
  yield console.log("-fetchData-"); 
  // 백엔드에서 데이터 받아오기 
  const result: AxiosResponse<ContactItemResponse[]> = yield call(api.fetch);
    // 응답데이터를 액션페이로드배열로 변환 
   const contact = result.data.map(
     (item) => 
     ({                       
      id: item.id,                       
      username: item.username,
      mail: item.mail,                  
      tellnumber: item.tellnumber,            
      description: item.description,
      memo: item.memo,                            
      createdTime: item.createdTime                  
     } as ContactItem)
   );


  
   yield put(initialContact(contact)); 

}


 
function* removeData(action: PayloadAction<number>) {
  yield console.log("--removeData--");

  // id값
  const id = action.payload; 

  // spinner 보여주기
  // yield put(startProgress());

  // rest api 연동
  const result: AxiosResponse<boolean> = yield call(api.remove, id);

  // spinner 사라지게 하기
  // yield put(endProgress());

  // 반환 값이 true이면
  if (result.data) {
    // state 변경(1건삭제)
    yield put(removeContact(id));
  }

  // completed 속성 삭제
  yield put(initialCompleted());
}

function* modifyContact (action: PayloadAction<ContactItem>){
  yield console.log("--modifyContact--");

  const contactItemPayload = action.payload;

  const ContactItemRequest: ContactItemRequest = {
    username:contactItemPayload.username,
    mail:contactItemPayload.mail,
    tellnumber:contactItemPayload.tellnumber,
    description:contactItemPayload.description,
    memo:contactItemPayload.memo,
    createdTime:contactItemPayload.createdTime
};

    // await api.add(photoItemRequest) 이 구문과 일치함
    // // 결과값을 형식을 지졍해야함
    // const result: AxiosResponse<ContactItemResponse> = yield call(
    //   api.modify,
    //   contactItemPayload.id, 
    //   ContactItemRequest
    // );

    

//  const contactItem: ContactItem = {
//   username:result.data.username,
//   mail:result.data.mail,
//   tellnumber:result.data.tellnumber,
//   description:result.data.description,
//   memo:result.data.memo,
//   createdTime:result.data.createdTime
//  }

// yield put(modifyContact(contactItem)); 


// yield put(initialCompleted());  // completed 속성 삭제  

}

 

export default function* contactSaga() {
  yield console.log("--contact-saga--") 
  yield takeEvery(requestAddContact, addData);
  yield takeLatest(requsetFetchContacts, fetchData); 
  yield takeEvery(requestRemoveContact , removeData);
  yield takeEvery(requestModifyContact, modifyContact); 
}


