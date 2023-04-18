import styled from "styled-components";

/** 2023/03/24 - 하단 네비게이션 바 스타일 컴포넌트 - by 1-blue */
export const StyledNavBar = styled.nav`
  width: 100px;
  height: 100%;
  display: flex;
  align-items: center;
`;

/** 2023/04/17 - 로그인/로그아웃 버튼 베이스 - by sinyaenok*/
/* Todo : 버튼으로 할지, a링크로 할지 의논필요 */
/* Todo2 : 버튼 입체효과 넣기 */
const StyledBtn = styled.button`
  height: 30px;
  border: 1px solid ${({ theme }) => theme.colors.cyan700};
  border-radius: 3px;
  cursor: pointer;
`;

/** 2023/04/17 - 로그인 버튼 - by sinyaenok */
export const StyledLogin = styled(StyledBtn)`
  width: 50px;
  color: ${({ theme }) => theme.colors.cyan700};
  background-color: ${({ theme }) => theme.colors.blue50};
  :hover {
    color: ${({ theme }) => theme.colors.cyan800};
    background-color: ${({ theme }) => theme.colors.blue100};
  }
`;

/** 2023/04/17 - 회원가입 버튼 - by sinyaenok */
export const StyledSignup = styled(StyledBtn)`
  width: 60px;
  margin-left: 3px;
  color: ${({ theme }) => theme.colors.zinc50};
  background-color: ${({ theme }) => theme.colors.blue500};
  :hover {
    color: ${({ theme }) => theme.colors.zinc100};
    background-color: ${({ theme }) => theme.colors.blue600};
  }
`;
