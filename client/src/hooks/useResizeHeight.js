import { useRef } from "react";

/** 2023/04/20 - textarea 자동 높이 조절 훅 - by 1-blue */
const useResizeHeight = () => {
  /** 2023/04/19 - textarea ref - by 1-blue */
  const textRef = useRef(null);

  /** 2023/04/19 - 입력된 내용에 맞게 textarea 높이 지정 - by 1-blue */
  const handleResizeHeight = () => {
    if (!textRef || !textRef.current) return;

    textRef.current.style.height = "0px";
    textRef.current.style.height = textRef.current.scrollHeight + "px";
  };

  return [textRef, handleResizeHeight];
};

export default useResizeHeight;
