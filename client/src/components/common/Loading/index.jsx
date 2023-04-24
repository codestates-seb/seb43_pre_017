import { useEffect } from "react";

// style
import StyledLoading from "./style";

/** 2023/04/23 - 로딩 컴포넌트 - by 1-blue */
const Loading = () => {
  /** 2023/04/23 - 스피너 있는 경우 스크롤 금지 - by 1-blue */
  useEffect(() => {
    document.body.style.overflow = "hidden";

    return () => (document.body.style.overflow = "auto");
  }, []);

  return (
    <StyledLoading>
      <div className="loader">Loading...</div>
    </StyledLoading>
  );
};
export default Loading;
