import styled from "styled-components";

/** 2023/04/12 - 메인 내용을 감싸는 컴포넌트의 스타일 - by 1-blue */
const StyledMain = styled.main`
  max-width: 1280px;
  margin: 0 auto;

  display: flex;

  min-height: calc(100vh - 50px - 320px);

  @media (max-width: 980px) {
    min-height: calc(100vh - 50px - 428px);
  }
`;

export default StyledMain;
