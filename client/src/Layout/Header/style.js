import styled from "styled-components";

/** 2023/04/16 - 헤더를 감싸는 컴포넌트의 스타일 - by sinyaenok */
export const StyledHeader = styled.header`
  width: 100%;
  height: 50px;
  background-color: ${({ theme }) => theme.colors.gray50};
  border-top: 3px solid ${({ theme }) => theme.colors.main500};
  box-shadow: 0px 3px 7px ${({ theme }) => theme.colors.gray200};
  position: sticky;
  left: 0;
  top: 0;
  z-index: 1;
`;

/** 2023/04/17 - 헤더 속 엘레먼트들을 감싸는 컴포넌트 - by sinyaenok*/
export const StyledContaier = styled.div`
  max-width: 1280px;
  margin: 0 auto;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
`;

/** 2023/04/17 - stackoverflow 로고 - by sinyaenok*/
export const StyledLogo = styled.a`
  height: 100%;
  display: flex;
  align-items: center;
  padding: 10px 20px;
  :hover {
    background-color: rgba(0, 0, 0, 0.2);
  }

  .Logo-img {
    height: 30px;
    cursor: pointer;

    //640px보다 작아지면 실행
    @media (max-width: 720px) {
      display: none;
    }
  }
  .MinLogo-img {
    height: 30px;
    cursor: pointer;

    //640px보다 커지면 실행
    @media (min-width: 720px) {
      display: none;
    }
  }
`;

/** 2023/04/17 - 검색창 구현 by sinyaenok*/
export const StyledSearchFrom = styled.form`
  padding: 7px 10px;
  display: flex;
  height: 100%;
  align-items: center;
  width: 87em;
  background-color: ${({ theme }) => theme.colors.gray50};

  //input을 감싸는 div
  .search-bar {
    width: 100%;
    height: 100%;
    border-radius: 3px;
    border: 1px solid rgba(0, 0, 0, 0.3);
    display: flex;
    align-items: center;
    background-color: #fff;
  }

  //아이콘
  .search-icon {
    margin-left: 0.7em;
    color: ${({ theme }) => theme.colors.gray500};
  }

  //텍스트 입력 인풋
  input {
    width: 100%;
    border: none;
    margin: 0.7em;
    background-color: #fff;
    overflow: auto;

    :focus {
      outline: none;
    }
  }
`;
