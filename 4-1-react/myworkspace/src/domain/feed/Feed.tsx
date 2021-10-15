// import { useRef, useState } from "react";
// // import { lorem, penguin, robot } from "../common/data";
// // import { getTimeString } from "../common/lib/string";

//  import FeedEditModal from "./FeedEditModal"; 
// // ./type.ts/js/tsx가 없으면, ./type/index.ts/js/tsx 로딩함
//  import { FeedState } from "./type";
//  import produce from "immer";
// //  import { iteratorSymbol } from "immer/dist/internal";

// const getTimeString = (unixtime: number) => {
//   const dateTime = new Date(unixtime);
//   return `${dateTime.toLocaleDateString()} ${dateTime.toLocaleTimeString()}`;
// };
// const Feed = () => {
//   const [feedList, setFeedList] = useState<FeedState[]>([]);

//   const textRef = useRef<HTMLTextAreaElement>(null);
//   const inputRef = useRef<HTMLInputElement>(null);
//   const formRef = useRef<HTMLFormElement>(null);

//   const [isEdit, setIsEdit] = useState(false);

//   const add = (e: React.KeyboardEvent<HTMLInputElement> | null) => {
//     if (e) {    
//       if (e.code !== "Enter") return;
//     }
//     if (inputRef.current?.files?.length) {
//       const file = inputRef.current?.files[0];
//       const reader = new FileReader();
//       reader.readAsDataURL(file);

//       reader.onload = () => {
//         post(reader.result?.toString(), file.type);
//       };
//     } else {
//       post(undefined, undefined); 
//     }
//   };              

//   const post = (dataUrl: string | undefined, fileType: string | undefined) => {
//     const feed: FeedState = {
//       id: feedList.length > 0 ? feedList[0].id + 1 : 1,
//       // optional chaning
//       content: textRef.current?.value,
//       dataUrl: dataUrl,  
//       fileType: fileType,
//       createTime: new Date().getTime(),
//     };
//     setFeedList([feed, ...feedList]);
//     // 입력값 초기화
//     formRef.current?.reset();
//   };
//   const del = (id: number) => {
//     setFeedList(feedList.filter((item) => item.id !== id));
//   };

//   const editItem = useRef<FeedState>({id: 0, content: "", createTime: 0});

//   const edit = (item: FeedState) => {
//     // 수정할 todo객체
//     editItem.current = item;
//     // 모달 팝업을 보여주기
//     setIsEdit(true);
//   };

//   const save = (editItem: FeedState) => {
//     console.log(editItem);
//     setFeedList(
//       produce((state) => {
//         const item = state.find((item) => item.id === editItem.id);
//         if (item) {
//           item.content = editItem.content;
//         }
//       })
//     );
//   }

//   return (
//     <div>
//       <h2 className="text-center my-5">Feeds</h2>

//       {isEdit && (
//         <FeedEditModal
//           item={editItem.current} 
//           onClose={() => {
//             setIsEdit(false);
//           }}
//           onSave={(editItem) => {
//             save(editItem);                  
//           }}
//         />
//       )}


//       <form
//         className="mt-5"
//         onSubmit={(e) => {
//           e.preventDefault();
//         }}
//         ref={formRef}
//       >
//         <textarea
//           className="form-control mb-1"
//           placeholder="Leave a post here"
//           ref={textRef}
//           style={{ boxSizing: "border-box", height: "15vh" }}
//         ></textarea>
//         <div className="d-flex">
//           <input
//             type="file"
//             className="form-control me-1"
//             accept="image/png, image/jpeg, video/mp4"
//             ref={inputRef}
//           />
//           <button
//             className="btn btn-primary text-nowrap"
//             type="button"
//             onClick={() => {
//               add(null);
//             }}
//           >
//             입력
//           </button>
//         </div>
//       </form>
//       <div className="mt-3">
//         {feedList.map((item) => (
//           <div className="card mt-1" key={item.id}>
//               <div className="card-header">Feed 작성한 profile이미지, 사용자명
//               </div>
//             {item.fileType &&
//               (item.fileType?.includes("image") ? (
//                 <img
//                   src={item.dataUrl}
//                   className="card-img-top"
//                   alt={item.content}
//                 />
//               ) : (
            
//                 <video className="card-img-top" controls src={item.dataUrl} />
//               ))}

//             <div className="card-body">
//               <p className="card-text">{item.content}</p>
//               <div className="d-flex">
//                 <div className="w-100">
//                   <span className="text-secondary">
//                     {getTimeString(
//                       item.modifyTime ? item.modifyTime : item.createTime
//                     )}
//                   </span>
//                 </div>
                                             
                 
//                 <button
//               className="btn btn-outline-secondary btn-sm text-nowrap me-1"
//               onClick={() => {
//                 // 수정 모달 팝업 띄우고 데이터 객체 넘겨주기
//                 edit(item);
//               }}
//             >
//               수정
//             </button>

//                 <a
//                   href="#!"
//                   onClick={(e) => {
//                     e.preventDefault(); // 기본 동작 방지
//                     del(item.id);
//                   }}
//                   className="link-secondary fs-6 text-nowrap"
//                 >
//                   삭제
//                 </a>
//               </div>
//             </div>
//           </div>
//         ))}
//       </div>
//     </div>
//   );
// };


//  export default Feed;

import { useRef, useState } from "react";
// import { lorem, penguin, robot } from "../common/data";
// import { getTimeString } from "../common/lib/string";

interface FeedItemState {
  id: number;
  content?: string | undefined;
  dataUrl?: string | undefined;
  fileType?: string | undefined;
  createTime: number;
  modifyTime?: number;
  isEdit?: boolean;
}

const getTimeString = (unixtime: number) => {
  // Locale: timezone, currency 등
  // js에서는 브라우저의 정보를 이용함
  const dateTime = new Date(unixtime);
  return `${dateTime.toLocaleDateString()} ${dateTime.toLocaleTimeString()}`;
};

const Feed = () => {
  const [feedList, setFeedList] = useState<FeedItemState[]>([]);

  const textRef = useRef<HTMLTextAreaElement>(null);
  const fileRef = useRef<HTMLInputElement>(null);
  const formRef = useRef<HTMLFormElement>(null);

  const add = (e: React.KeyboardEvent<HTMLInputElement> | null) => {
    if (e) {
      if (e.code !== "Enter") return;
    }

    if (fileRef.current?.files?.length) {
      const file = fileRef.current?.files[0];
      const reader = new FileReader();

      // 로딩이 끝났을 때 처리될 함수를 선언
      reader.onload = () => {
        post(reader.result?.toString(), file.type);
        console.log("1");
      };

      // read하라고 실행 한거임
      reader.readAsDataURL(file);

      console.log("2");
    } else {
      post(undefined, undefined);
    }
  };

  const post = (dataUrl: string | undefined, fileType: string | undefined) => {
    const feed: FeedItemState = {
      id: feedList.length > 0 ? feedList[0].id + 1 : 1,
      // optional chaning
      content: textRef.current?.value,
      dataUrl: dataUrl,
      fileType: fileType,
      createTime: new Date().getTime(),
    };

    setFeedList([feed, ...feedList]);

    // 입력값 초기화
    formRef.current?.reset();
  };

  const del = (id: number) => {
    setFeedList(feedList.filter((item) => item.id !== id));
  };

  return (
    <div style={{ width: "40vw" }} className="mx-auto">
      <h2 className="text-center my-5">Feeds</h2>
      <form
        className="mt-5"
        onSubmit={(e) => {
          e.preventDefault();
        }}
        ref={formRef}
      >
        <textarea
          className="form-control mb-1"
          placeholder="Leave a post here"
          ref={textRef}
          style={{ height: "15vh" }}
        ></textarea>
        <div className="d-flex">
          <input
            type="file"
            className="form-control me-1"
            accept="image/png, image/jpeg, video/mp4"
            ref={fileRef}
          />
          <button
            className="btn btn-primary text-nowrap"
            type="button"
            onClick={() => {
              add(null);
            }}
          >
            입력
          </button>
        </div>
      </form>
      <div className="mt-3">
        {feedList.map((item) => (
          <div className="card mt-1" key={item.id}>
            <div className="card-header">
              Feed 작성한 profile이미지, 사용자명
            </div>
            {item.fileType &&
              (item.fileType?.includes("image") ? (
                <img
                  src={item.dataUrl}
                  className="card-img-top"
                  alt={item.content}
                />
              ) : (
                <video className="card-img-top" controls src={item.dataUrl} />
              ))}
            <div className="card-body">
              <p className="card-text">{item.content}</p>
              <div className="d-flex">
                <div className="w-100">
                  <span className="text-secondary">
                    {getTimeString(
                      item.modifyTime ? item.modifyTime : item.createTime
                    )}
                  </span>
                </div>
                <a
                  href="#!"
                  onClick={(e) => {
                    e.preventDefault(); // 기본 동작 방지
                    del(item.id);
                  }}
                  className="link-secondary fs-6 text-nowrap"
                >
                  삭제
                </a>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Feed;