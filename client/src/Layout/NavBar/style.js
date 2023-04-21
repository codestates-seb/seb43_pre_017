import styled from "styled-components";

/** 2023/03/24 - 하단 네비게이션 바 스타일 컴포넌트 - by 1-blue */
export const StyledNavBar = styled.nav`
  width: 200px;
  height: 100%;
  display: flex;
  align-items: center;

  //버튼 스타일 베이스
  .header-btn {
    font-size: 14px;
    border-radius: 3px;
    padding: 8px 10px;
    border: 1px solid red;
  }

  /** 2023/04/21 - 로그인 버튼 수정 - by sinyaenok*/
  #login-btn {
    font-weight: 300;
    border: 0.5px solid ${({ theme }) => theme.colors.blue700};
    color: ${({ theme }) => theme.colors.blue700};
    background-color: ${({ theme }) => theme.colors.sky100};
    :hover {
      color: ${({ theme }) => theme.colors.blue800};
      background-color: ${({ theme }) => theme.colors.blue200};
    }
  }

  /** 2023/04/21 - 회원가입 버튼 수정 - by sinyaenok*/
  #signup-btn {
    border: 0.5px solid ${({ theme }) => theme.colors.blue700};
    color: ${({ theme }) => theme.colors.slate50};
    background-color: ${({ theme }) => theme.colors.blue500};
    margin-left: 5px;
    :hover {
      color: ${({ theme }) => theme.colors.slate100};
      background-color: ${({ theme }) => theme.colors.blue700};
    }
  }
`;
