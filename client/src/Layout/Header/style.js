import styled from "styled-components";

/** 2023/04/16 - 헤더를 감싸는 컴포넌트의 스타일 - by sinyaenok */
const StyledHeader = styled.header`
  /* box-sizing: border-box; */
  width: 100%;
  height: 50px;
`;

/** 2023/04/17 - 헤더 속 엘레먼트들을 감싸는 컴포넌트 - by sinyaenok*/
const StyledContaier = styled.div`
  display: flex;
  align-items: center;
  position: fixed;
  justify-content: center;
  top: 0;
  left: 0;
  height: 50px;
  width: 100%;
  background-color: ${({ theme }) => theme.colors.gray50};
  border-top: 3px solid ${({ theme }) => theme.colors.main500};
  box-shadow: 0px 3px 7px ${({ theme }) => theme.colors.gray200};
`;

const StyledLogo = styled.a`
  height: 100%;
  display: flex;
  align-items: center;
  padding: 10px;
  :hover {
    background-color: rgba(0, 0, 0, 0.2);
  }
  img {
    height: 30px;
    cursor: pointer;
  }
`;

const StyledForm = styled.form`
  margin: 10px;
  input {
    height: 30px;
    padding: 10px;
    width: 700px;
  }
`;

const StyledBtn = styled.a`
  height: 30px;
  border-radius: 3px;
  text-align: center;
  line-height: 33px;
  cursor: pointer;
  border: 1px solid ${({ theme }) => theme.colors.cyan700};
`;

/** 2023/04/17 - 로그인 버튼 */
const StyledLogin = styled(StyledBtn)`
  width: 50px;
  color: ${({ theme }) => theme.colors.cyan700};
  background-color: ${({ theme }) => theme.colors.blue50};
  :hover {
    color: ${({ theme }) => theme.colors.cyan800};
    background-color: ${({ theme }) => theme.colors.blue100};
  }
`;

/** 2023/04/17 - 회원가입 버튼 */
const StyledSignup = styled(StyledBtn)`
  width: 60px;
  margin-left: 3px;
  color: ${({ theme }) => theme.colors.zinc50};
  background-color: ${({ theme }) => theme.colors.blue500};
  :hover {
    color: ${({ theme }) => theme.colors.zinc100};
    background-color: ${({ theme }) => theme.colors.blue600};
  }
`;

export {
  StyledHeader,
  StyledContaier,
  StyledLogo,
  StyledForm,
  StyledLogin,
  StyledSignup,
};
