import { useRef } from "react";

/** 2023/04/17 - "Wysiwyg"에서 사용할 값들을 얻는 훅 - by 1-blue */
const useWysiwyg = () => {
  const wysiwygRef = useRef(null);

  /** 2023/04/17 - "HTML"로 파싱된 결과물 얻기 - by 1-blue */
  const getContents = () => wysiwygRef.current?.getInstance().getHTML();

  return { wysiwygRef, getContents };
};

export default useWysiwyg;
