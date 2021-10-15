import { useRef, useState } from "react";

import produce from "immer";

// 1건에 대한 타입
interface FeedState {
  id: number;
  memo: string | undefined;
  createTime: number;
  dataUrl: string | undefined;
}

const getTimeString = (unixtime: number) => {
  // Locale: timezone, currency 등
  // js에서는 브라우저의 정보를 이용함
  const dateTime = new Date(unixtime);
  return `${dateTime.toLocaleDateString()} ${dateTime.toLocaleTimeString()}`;
};

const Feed = () => {
  const [feedList, setFeedList] = useState<FeedState[]>([
    // { id: 1, memo: "Typescript", createTime: new Date().getTime(),  },
  ]);

  const inputRef = useRef<HTMLInputElement>(null);
  const formRef = useRef<HTMLFormElement>(null);
  const divRef = useRef<HTMLDivElement>(null);
  const textRef = useRef<HTMLTextAreaElement>(null);

  const add = () => {
    const reader = new FileReader();
    let file: File
    let memo = textRef.current?.value;
    if(inputRef.current?.files?.length) {
     file = inputRef.current.files[0]
     reader.readAsDataURL(file);
    }
    reader.onload = () => {
      let dataUrl = reader.result?.toString();
      
      const feed: FeedState = {
        id: feedList.length > 0 ? feedList[0].id + 1 : 1,
        memo: memo,
        createTime: new Date().getTime(),
        dataUrl: dataUrl,
      };
      
      setFeedList(
        produce((state) => {
          state.unshift(feed);
        })
      );
    }
    // 입력값 초기화
    formRef.current?.reset();
  };

  const del = (id: number, index: number) => {
    console.log(id);
    setFeedList(
      produce((state) => {
        state.splice(index, 1);
      })
    )
  };

  return (
    <>
      <form
        className="mt-5"
        ref={formRef}
        onSubmit={(e) => {
          e.preventDefault();
        }}
      >
        <textarea
          className="form-control mb-1 w-100"
          placeholder="Leave a post here"
          ref={textRef}
        ></textarea>
        <div className="d-flex">
          <input
            type="file" 
            className="form-control me-1"
            accept="image/png, image/jpeg, video/mp4"
            ref={inputRef}
          />
          <button  
            className="btn btn-primary text-nowrap" 
            type="button"
            onClick={() => {
              add();
            }}
            >
            추가
          </button>
        </div>
        
      </form>
      <div className="card" ref={divRef} >
        {feedList.map((item, index) => (
          <div className="card-body" key={item.id}>
            <img src={item.dataUrl} className="card-img-top" alt="..." />
            {/* <div className="card-title"></div> */}
            <p className="card-text">
              {item.memo}</p>
            <div className="d-flex w-100">
            <span style={{ fontSize: "0.75rem" }}>
              - {getTimeString(item.createTime)}
            </span>
            </div>
            <a href="!#" className="link-secondary d-flex justify-content-end" onClick={() => {
              del(item.id, index);
            }}
            >삭제</a>
           </div> 
          
        ))}
      </div>
    </>
  );
};
export default Feed;