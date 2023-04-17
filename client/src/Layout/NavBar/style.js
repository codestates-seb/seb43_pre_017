import styled from "styled-components";

/** 2023/03/24 - 하단 네비게이션 바 스타일 컴포넌트 - by 1-blue */
const StyledNavBar = styled.nav`
  width: 100%;
  height: 100%;
  /* display: flex; */
  /* align-items: center; */
  /* justify-content: center; */
`;

const StyledBtn = styled.button`
  height: 30px;
  border: 1px solid ${({ theme }) => theme.colors.cyan700};
  border-radius: 3px;
  cursor: pointer;
`;

const StyledLogin = styled(StyledBtn)`
  color: ${({ theme }) => theme.colors.cyan700};
  background-color: ${({ theme }) => theme.colors.blue50};
  :hover {
    color: ${({ theme }) => theme.colors.cyan800};
    background-color: ${({ theme }) => theme.colors.blue100};
  }
`;
const StyledSignup = styled(StyledBtn)`
  margin-left: 3px;
  color: ${({ theme }) => theme.colors.zinc50};
  background-color: ${({ theme }) => theme.colors.blue500};
  :hover {
    color: ${({ theme }) => theme.colors.zinc100};
    background-color: ${({ theme }) => theme.colors.blue600};
  }
`;
export { StyledNavBar, StyledLogin, StyledSignup };
