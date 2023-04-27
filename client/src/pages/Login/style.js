import styled from "styled-components";

/** 2023/04/18 - 로그인 페이지 전체 컨테이너 - by JHH0906 */
const StyledLogin = styled.div`
  width: 2000px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 140px;
  cursor: pointer;
  .passwordError {
    width: 240px;
    font-size: 12px;
    display: flex;
    position: absolute;
    margin-top: 90px;
    color: red;
  }
  .emailError {
    width: 240px;
    font-size: 12px;
    display: flex;
    position: absolute;
    margin-bottom: 66px;
    color: red;
  }
`;
/** 2023/04/18 - 로그인 컨테이너 - by JHH0906 */
export const StyledLoginContainer = styled.div`
  width: 280px;
  height: 260px;
  display: flex;
  flex-direction: column;
  background: white;
  justify-content: center;
  align-items: center;
  border: 1px solid ${({ theme }) => theme.colors.gray300};
  border-radius: 8px;
  cursor: pointer;
`;
/** 2023/04/18 - 로그인 페이지 스택오버플로우 로고- by JHH0906 */
export const StyledLogo = styled.a`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  > img {
    width: 60px;
    margin-bottom: 8px;
  }
`;

/** 2023/04/18 - 로그인 페이지 구글 로고 - by JHH0906 */
export const StyledGoggleLogo = styled.div`
  display: flex;
  margin-right: 8px;
  > img {
    width: 20px;
  }
`;

/** 2023/04/18 - 로그인 페이지 구글 로그인 버튼 - by JHH0906 */
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

/** 2023/04/18 - 로그인 페이지 텍스트 - by JHH0906 */
export const StyledTitle = styled.div`
  width: 240px;
  font-weight: 700;
  display: flex;
  justify-content: space-between;
`;

/** 2023/04/18 - 로그인 페이지 인풋 - by JHH0906 */
export const StyledLoginInput = styled.input`
  width: 240px;
  height: 32px;
  border-radius: 4px;
  border: 1px solid ${({ theme }) => theme.colors.gray300};
  background: white;
  margin-bottom: 28px;
  cursor: pointer;
`;

/** 2023/04/18 - 로그인 페이지 로그인 버튼 - by JHH0906 */
export const StyledLoginBtn = styled.button`
  width: 240px;
  height: 36px;
  background: white;
  background-color: ${({ theme }) => theme.colors.sky500};
  color: ${({ theme }) => theme.colors.sky50};
  border-radius: 4px;
  cursor: pointer;
  &:hover {
    background-color: ${({ theme }) => theme.colors.sky600};
  }
`;

/** 2023/04/18 - 로그인 페이지 회원가입 텍스트 - by JHH0906 */
export const StyledSignup = styled.div`
  width: 280px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
  font-size: 12px;
`;

/** 2023/04/18 - 로그인 페이지 이메일 인증 이동 - by JHH0906 */
export const StyledForgotLink = styled.a`
  font-size: 12px;
  margin-left: 16px;
  color: ${({ theme }) => theme.colors.sky500};
`;

/** 2023/04/18 - 로그인 페이지 회원가입 이동 - by JHH0906 */
export const StyledSignupLink = styled.a`
  margin-left: 4px;
  color: ${({ theme }) => theme.colors.sky500};
`;

export default StyledLogin;
