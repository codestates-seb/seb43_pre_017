import styled from "styled-components";

/** 2023/04/15 - 테스트용 스타일 - by 1-blue */
const StyledMyComponent = styled.section`
  width: 200px;
  height: 200px;
  border-radius: 50%;
  background-color: ${({ theme }) => theme.colors.main500};
`;

export default StyledMyComponent;
