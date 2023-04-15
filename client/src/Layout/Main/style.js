import styled from "styled-components";

/** 2023/04/12 - 메인 내용을 감싸는 컴포넌트의 스타일 - by 1-blue */
const StyledMain = styled.main`
  /** 임시 */
  background-color: ${({ theme }) => theme.colors.gray200};
  height: 100vh;
`;

export default StyledMain;
