import { useEffect, useRef } from "react";
import { useSelector, useDispatch } from "react-redux";
import { useHistory } from "react-router-dom";
import { AppDispatch, RootState } from "../../store";
import { requestAddContact } from "./contactSaga";
import { ContactItem } from "./ContactSlice";
import { addContact } from "./ContactSlice";

const ContactCreate = () => {
  const nameInput = useRef<HTMLInputElement>(null);
  const tellNumber = useRef<HTMLInputElement>(null);
  const textMail = useRef<HTMLInputElement>(null);
  const descTxta = useRef<HTMLTextAreaElement>(null);

  const contactData = useSelector((state: RootState) => state.contact.data);
  const isAddCompleted = useSelector(
    (state: RootState) => state.contact.isAddCompleted
  );

  const dispatch = useDispatch<AppDispatch>();
  const history = useHistory();

  useEffect(() => {
    console.log("--isAddCompleted 변경: " + isAddCompleted);
    // true이면 화면이동
    isAddCompleted && history.push("/contact");
  }, [isAddCompleted, history, dispatch]);

  const handleAddClick = () => {
    const item: ContactItem = {
      id: contactData.length ? contactData[0].id + 1 : 1,
      username: nameInput.current ? nameInput.current.value : "",
      mail: textMail.current ? textMail.current.value : "",
      tellnumber: tellNumber.current ? tellNumber.current.value : "",
      description: descTxta.current?.value,
      createdTime: new Date().getTime(),
    };

    //  console.log(item);

    dispatch(requestAddContact(item));
    dispatch(addContact(item));
    history.push("/contact");
  };

  return (
    <div style={{ width: "40vw" }} className="mx-auto">
      <h2 className="text-center">Contact Create</h2>
      <form>
        <table className="table">
          <tbody>
            <tr>
              <th>이름</th>
              <td>
                <input className="form-control" type="text" ref={nameInput} />
              </td>
            </tr>
            <tr>
              <th>전화번호</th>
              <td>
                <input className="form-control" type="text" ref={tellNumber} />
              </td>
            </tr>
            <tr>
              <th>이메일</th>
              <td>
                <input className="form-control" type="text" ref={textMail} />
              </td>
            </tr>
            <tr>
              <th>메모 </th>
              <td>
                <textarea
                  className="formcontrol"
                  style={{ width: "32vh" }}
                  ref={descTxta}
                />
              </td>
            </tr>
          </tbody>
        </table>
      </form>

      <div>
        <button
          className="btn btn-secondary float-start"
          onClick={() => {
            history.push("/contact");
          }}
        >
          <i className="bi bi=grid-3x3-gap me-1"></i>
          목록
        </button>
        <button
          className="btn btn-primary float-end"
          onClick={() => {
            handleAddClick();
          }}
        >
          <i className="bi bi-check" />
          저장
        </button>
      </div>
    </div>
  );
};

export default ContactCreate;
