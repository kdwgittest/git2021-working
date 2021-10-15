import { useRef, useState } from "react";

import produce from "immer";


// 1건에 대한 타입
interface TodoState {
  id: number;
  memo: string | undefined;
  createTime: number;
}

const getTimeString = (unixtime: number) => {
  // Locale: timezone, currency 등
  // js에서는 브라우저의 정보를 이용함
  const dateTime = new Date(unixtime);
  return `${dateTime.toLocaleDateString()} ${dateTime.toLocaleTimeString()}`;
};

// { 함수속성 }
// 함수속성의 타입: (매개변수타입) => 리턴타입 

// 함수를 부모 컴포넌트로 부터 매개변수(prop)를 받음 
// 자식컴포넌트에서 이벤트가 발생하면 prop으로 받음함수를 실행 
const TodoEditModal = ({onClose} : {onClose: () =>void}) => {

 return (
  <div className="modal d-block">
  <div className="modal-dialog">
    <div className="modal-content">
      <div className="modal-header">
        <h5 className="modal-title">Modal title</h5>
        <button type="button" className="btn-close" aria-label="Close" onClick={()=>{onClose();}}></button>
      </div>
      <div className="modal-body">
        <p>Modal body text goes here.</p>
      </div>
      <div className="modal-footer">
        <button type="button" className="btn btn-secondary" onClick={()=>{onClose();}}>Close</button>
        <button type="button" className="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>

 )
}

const Todo = () => {
  console.log("--todowithmodal--")
  const [todoList, setTodoList] = useState<TodoState[]>([
    { id: 2, memo: "Typescript", createTime: new Date().getTime() },
    { id: 1, memo: "React State 연습", createTime: new Date().getTime() },
  ]);

  // 수정파업을 띄울지 아닐지 
const [isEdit, setIsEdit] = useState(false); 

  const inputRef = useRef<HTMLInputElement>(null);
  const formRef = useRef<HTMLFormElement>(null);
  const ulRef = useRef<HTMLUListElement>(null);

  const add = () => {
    const todo: TodoState = {
      id: todoList.length > 0 ? todoList[0].id + 1 : 1,
      memo: inputRef.current?.value,
      createTime: new Date().getTime(),
    };
    setTodoList(
      produce((state) => {
        state.unshift(todo);
      })
    );

    // 입력값 초기화
    formRef.current?.reset();
  };

  const del = (id: number, index: number) => {
    console.log(id);
    // immer로 state 배열 직접 조작(index로 삭제)
    setTodoList(
      produce((state) => {
        state.splice(index, 1);
      })
    );
  };

 const edit = (mod: boolean) => {
   // 모달 팝업을 띄우기 
   setIsEdit(mod); 
 }

  return (
    <>
      <h2 className="text-center my-5">할 일 관리</h2>
      {isEdit && <TodoEditModal onClose={() => {edit(false);}} />}
      <form
        className="d-flex"
        ref={formRef}
        onSubmit={(e) => e.preventDefault()}
      >
        <input
          type="text"
          className="form-control me-2"
          placeholder="할 일 ..."
          ref={inputRef}
        />
        <button
          type="button"
          className="btn btn-primary text-nowrap"
          onClick={() => {
            add();
          }}
        >
          추가
        </button>
      </form>
      <ul id="ul-list" className="list-group list-group-flush mt-3" ref={ulRef}>
        {/* 데이터와 UI요소 바인딩 */}
        {todoList.map((item, index) => (
          <li className="list-group-item d-flex" key={item.id}>
            <div className="w-100">
              <span className="me-1">{item.memo}</span>
              <span style={{ fontSize: "0.75rem" }}>
                - {getTimeString(item.createTime)}
              </span>
            </div>
            <button
              className="btn btn-outline-secondary btn-sm text-nowrap me-1"
              onClick={() => {
                edit(true); 
              }}
            >
              수정 
            </button>
            <button
              className="btn btn-outline-secondary btn-sm text-nowrap"
              onClick={() => {
                del(item.id, index);
              }}
            >
              삭제
            </button>
          </li>
        ))}
      </ul>
    </>
  );
};

export default Todo;