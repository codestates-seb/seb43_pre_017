import styled from "styled-components";
/** 2023/04/21 - 회원가입 페이지 전체 컨테이너 - by JHH0906 */
const StyledSignup = styled.div`
  width: 2000px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  .passwordError {
    width: 240px;
    font-size: 12px;
    display: flex;
    position: absolute;
    margin-top: 152px;
    color: red;
  }
  .emailError {
    width: 240px;
    font-size: 12px;
    display: flex;
    position: absolute;

    color: red;
  }
`;
/** 2023/04/21 - 회원가입 페이지 컨테이너 - by JHH0906 */
export const StyledSignupContainer = styled.div`
  width: 280px;
  height: 380px;
  display: flex;
  flex-direction: column;
  background: white;
  justify-content: space-between;
  align-items: center;
  border: 1px solid ${({ theme }) => theme.colors.gray300};
  border-radius: 8px;
  cursor: pointer;
`;

/** 2023/04/21 - 회원가입 페이지 구글 로고 - by JHH0906 */
export const StyledGoggleLogo = styled.div`
  display: flex;
  margin-right: 8px;
  > img {
    width: 20px;
  }
`;

/** 2023/04/21 - 회원가입 페이지 구글 버튼- by JHH0906 */
export const StyledGoogleBtn = styled.button`
  width: 280px;
  height: 36px;
  display: flex;
  justify-content: center;
  align-items: center;
  background: white;
  border-radius: 8px;
  border: 1px solid ${({ theme }) => theme.colors.gray300};
  margin-bottom: 20px;
  cursor: pointer;
  &:hover {
    background-color: ${({ theme }) => theme.colors.slate50};
  }
`;

/** 2023/04/21 - 회원가입 페이지 텍스트 - by JHH0906 */
export const StyledTitle = styled.div`
  width: 240px;
  font-weight: 700;
  display: flex;
  justify-content: space-between;
  margin-bottom: 2px;
`;
/** 2023/04/21 - 회원가입 페이지 텍스트,인풋 컨테이너 - by JHH0906 */
export const StyledInputContainer = styled.div`
  width: 280px;
  height: 300px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;
/** 2023/04/21 - 회원가입 페이지 인풋 - by JHH0906 */
export const StyledSignupInput = styled.input`
  width: 240px;
  height: 32px;
  border-radius: 4px;
  border: 1px solid ${({ theme }) => theme.colors.gray300};
  background: white;
  cursor: pointer;
  margin-bottom: 24px;
`;
/** 2023/04/21 - 회원가입 페이지 유효성 텍스트 - by JHH0906 */
export const StyledValid = styled.div`
  width: 240px;
  color: gray;
  font-size: 12px;
  margin-top: 8px;
`;
/** 2023/04/21 - 회원가입 페이지 회원가입 버튼 - by JHH0906 */
export const StyledSignupBtn = styled.button`
  width: 240px;
  height: 36px;
  background: white;
  background-color: ${({ theme }) => theme.colors.sky500};
  color: ${({ theme }) => theme.colors.sky50};
  border-radius: 4px;
  margin-bottom: 40px;
  cursor: pointer;
  &:hover {
    background-color: ${({ theme }) => theme.colors.sky600};
  }
`;

export const StyledLogin = styled.div`
  width: 240px;
  height: 80px;
  font-size: 12px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

/** 2023/04/18 - 로그인 페이지 회원가입 이동 - by JHH0906 */
export const StyledSignupLink = styled.a`
  margin-left: 4px;
  color: ${({ theme }) => theme.colors.sky500};
`;
export default StyledSignup;
