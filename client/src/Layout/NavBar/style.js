import styled from "styled-components";

/** 2023/03/24 - 하단 네비게이션 바 스타일 컴포넌트 - by 1-blue */
export const StyledNavBar = styled.nav`
  display: flex;
  height: 100%;
  align-items: center;
  background-color: ${({ theme }) => theme.colors.gray50};
`;

// 로그인 버튼
export const StyledLoginBtn = styled.div`
  display: none;
  /* display: flex; */
  justify-content: center;
  width: 60px;
  font-size: 14px;
  border-radius: 3px;
  font-weight: 300;
  border: 0.5px solid ${({ theme }) => theme.colors.blue700};
  background-color: ${({ theme }) => theme.colors.sky100};
  white-space: nowrap;
  :hover {
    background-color: ${({ theme }) => theme.colors.blue200};
  }
  .loginink {
    padding: 8px 10px;
    color: ${({ theme }) => theme.colors.blue800};
    :hover {
      color: ${({ theme }) => theme.colors.blue700};
    }
  }
`;

// 회원가입 버튼
export const StyledSignupBtn = styled.div`
  display: none;
  /* display: flex; */
  margin-left: 5px;
  justify-content: center;
  width: 70px;
  font-size: 14px;
  border-radius: 3px;
  border: 0.5px solid ${({ theme }) => theme.colors.blue500};
  background-color: ${({ theme }) => theme.colors.blue500};
  white-space: nowrap;
  :hover {
    background-color: ${({ theme }) => theme.colors.blue600};
  }
  .signuplink {
    padding: 8px 10px;
    color: ${({ theme }) => theme.colors.slate50};
    :hover {
      color: ${({ theme }) => theme.colors.slate100};
    }
  }
`;

//로그아웃 버튼
export const StyledLogoutBtn = styled.div`
  /* display: none; */
  display: flex;
  margin-left: 5px;
  justify-content: center;
  width: 70px;
  font-size: 14px;
  border-radius: 3px;
  border: 0.5px solid ${({ theme }) => theme.colors.blue500};
  background-color: ${({ theme }) => theme.colors.blue500};
  white-space: nowrap;
  :hover {
    background-color: ${({ theme }) => theme.colors.blue600};
  }
  .logoutlink {
    padding: 8px 10px;
    color: ${({ theme }) => theme.colors.slate50};
    :hover {
      color: ${({ theme }) => theme.colors.slate100};
    }
  }
`;

//멤버 이미지 버튼
export const StyledMemberBtn = styled.div`
  /* display: none; */
  display: flex;
  width: 40px;
  height: 100%;

  .memberlink {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100%;
    height: 100%;
  }
  img {
    height: 30px;
    width: 30px;
    border-radius: 3px;
    opacity: 0.8;
    :hover {
      opacity: 1;
    }
  }
`;
