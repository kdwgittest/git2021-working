import { fork } from "redux-saga/effects";
// import photoSaga from "../features/photo/photoSaga";
import contactSaga from "../domain/contact/contactSaga";

export default function* rootSaga() {
  // 비동기로 하위 사가를 처리함
  // 각각 하위사가가 다른 실행 컨텍스트에서 수행됨
  // yield fork(photoSaga);
  yield fork(contactSaga);
}