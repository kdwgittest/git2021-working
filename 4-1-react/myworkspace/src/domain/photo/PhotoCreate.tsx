import { useRef } from "react";
import { useSelector, useDispatch } from "react-redux";
import { useHistory } from "react-router-dom";
import { AppDispatch, RootState } from "../../store";
import { addPhoto, PhotoItem } from "./photoSlice";

const PhotoCreate = () => {
  const titleInput = useRef<HTMLInputElement>(null);
  const descTxta = useRef<HTMLTextAreaElement>(null);
  const fileInput = useRef<HTMLInputElement>(null);

  const photoData = useSelector((state: RootState) => state.photo.data);

  const profile = useSelector((state: RootState) => state.profile);

  const dispatch = useDispatch<AppDispatch>();
  const history = useHistory();

  const handleAddClick = () => {
    console.log(titleInput.current?.value);
    console.log(descTxta.current?.value);
    if (fileInput.current?.files?.length) {
      console.log(fileInput.current.files[0]);
    }

    if (fileInput.current?.files?.length) {
      const imageFile = fileInput.current.files[0];
      const reader = new FileReader();
      reader.onload = () => {
        const item: PhotoItem = {
          // 기존데이터의 id 중에서 가장 큰 것 + 1
          id: photoData.length ? photoData[0].id + 1 : 1,
          // 프로필 정보
          profileUrl: profile.image ? profile.image : "",
          username: profile.username ? profile.username : "",
          // 입력 정보
          title: titleInput.current ? titleInput.current.value : "",
          description: descTxta.current?.value,
          photoUrl: reader.result ? reader.result.toString() : "",
          fileType: imageFile.type,
          fileName: imageFile.name,
          createdTime: new Date().getTime(),
        };

        console.log(item);

        dispatch(addPhoto(item));
        history.push("/photos");
      };
      reader.readAsDataURL(imageFile);
    }
  };

  return (
    <div style={{ width: "40vw" }} className="mx-auto">
      <h2 className="text-center">Photo Create</h2>
      <form>
        <table className="table">
          <tbody>
            <tr>
              <th>제목</th>
              <td>
                <input className="form-control" type="text" ref={titleInput} />
              </td>
            </tr>
            <tr>
              <th>설명</th>
              <td>
                <textarea
                  className="form-control"
                  style={{ height: "40vh" }}
                  ref={descTxta}
                />
              </td>
            </tr>

            <tr>
              <th>이미지</th>
              <td>
                <input
                  className="form-control"
                  type="file"
                  accept="image/*"
                  ref={fileInput}
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
            history.push("/photos");
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

export default PhotoCreate;
