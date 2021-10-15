import { useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useHistory, useParams } from "react-router-dom";
import { AppDispatch, RootState } from "../../store";
import { modifyContact } from "./ContactSlice";

const ContactEdit = () => {
  // ------ 데이터를 가져오거나 변수를 선언하는 부분 --------
  const { id } = useParams<{ id: string }>();

  const ContactItem = useSelector((state: RootState) =>
    state.contact.data.find((item) => item.id === +id)
  );

  const dispatch = useDispatch<AppDispatch>();
  const history = useHistory();


  const nameInput = useRef<HTMLInputElement>(null);
  const tellNumber = useRef<HTMLInputElement>(null);
  const textMail = useRef<HTMLInputElement>(null);
  const descTxta = useRef<HTMLTextAreaElement>(null);



  const handleSaveClick = () => {
 

        if (ContactItem) {
          // 기존 데이터 카피
          const item = { ...ContactItem };
          // 변경할 속성만 대입
          item.username = nameInput.current ? nameInput.current.value : "";
          item.tellnumber = tellNumber.current ? tellNumber.current.value : "";
          item.mail = textMail.current ? textMail.current.value : "";
          item.description = descTxta.current?.value;

          // reducer로 state 수정 및 목록으로 이동
          dispatch(modifyContact(item));
          history.push("/contact");
        }


  };

  return (
    <div style={{ width: "40vw" }} className="mx-auto">
      <h2 className="text-center">Contact Edit</h2>
      <form>
        <table className="table">
          <tbody>
            <tr>
              <th>이름</th>
              <td>
                <input
                  className="form-control"
                  type="text"
                  defaultValue={ContactItem?.username}
                  ref={nameInput}
                />
              </td>
            </tr>
            
            <tr>
              <th>전화번호</th>
              <td>
              <input
                  className="form-control"
                  type="text"
                  defaultValue={ContactItem?.tellnumber}
                  ref={tellNumber}
                />
              </td>
            </tr>

            <tr>
              <th>이메일</th>
             <td>
             <input
                  className="form-control"
                  type="text"
                  defaultValue={ContactItem?.mail}
                  ref={textMail}
                />           
            </td> 
            </tr>

            <tr>
              <th>메모</th>
              <td>
                <textarea
                  className="form-control"
                  style={{ height: "40vh" }}
                  defaultValue={ContactItem?.description}
                  ref={descTxta}
                ></textarea>
              </td>
            </tr>
          
          </tbody>
        </table>
      </form>
      <div>
        <button
          className="btn btn-secondary me-1 float-start"
          onClick={() => {
            history.push("/contact");
          }}
        >
          <i className="bi bi-grid-3x3-gap me-1"></i>
          목록
        </button>
        <button
          className="btn btn-primary float-end"
          onClick={() => {
            handleSaveClick();
          }}
        >
          <i className="bi bi-check" />
          저장
        </button>
      </div>
    </div>
  );
};

export default ContactEdit;


