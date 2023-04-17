import styled from "styled-components";

/** 2023/04/16 - 헤더를 감싸는 컴포넌트의 스타일 - by sinyaenok */
const StyledHeader = styled.header`
  min-height: 50px;
  min-width: auto;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-around;
  position: sticky;
  top: 0;
  left: 0;
  background-color: ${({ theme }) => theme.colors.gray50};
  border-top: 3px solid ${({ theme }) => theme.colors.main500};
  box-shadow: 0px 3px 7px ${({ theme }) => theme.colors.gray200};
`;

const StyledLogo = styled.a`
  img {
    margin: 0 100px;
    min-width: 100px;
    height: 30px;
    cursor: pointer;
  }
`;

const StyledSearch = styled.input`
  min-width: 50vw;
  margin: 0px 10px 0px 100px;
  height: 30px;
  padding: 10px;
`;
export { StyledHeader, StyledLogo, StyledSearch };
