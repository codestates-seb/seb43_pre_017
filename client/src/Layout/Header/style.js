import styled from "styled-components";

/** 2023/04/16 - 헤더를 감싸는 컴포넌트의 스타일 - by sinyaenok */
export const StyledHeader = styled.header`
  box-sizing: border-box;
  width: 100%;
  height: 50px;
`;

/** 2023/04/17 - 헤더 속 엘레먼트들을 감싸는 컴포넌트 - by sinyaenok*/
export const StyledContaier = styled.div`
  display: flex;
  align-items: center;
  position: fixed;
  justify-content: center;
  top: 0;
  left: 0;
  height: 50px;
  width: 100%;
  min-width: auto;
  background-color: ${({ theme }) => theme.colors.gray50};
  border-top: 3px solid ${({ theme }) => theme.colors.main500};
  box-shadow: 0px 3px 7px ${({ theme }) => theme.colors.gray200};
`;

/** 2023/04/17 - stackoverflow 로고 - by sinyaenok*/
export const StyledLogo = styled.a`
  box-sizing: content-box;
  height: 100%;
  display: flex;
  align-items: center;
  padding: 0 10px;
  :hover {
    background-color: rgba(0, 0, 0, 0.2);
  }
  .stack-overflow {
    height: 30px;
    cursor: pointer;
    //640px보다 작아지면 실행
    @media (max-width: 640px) {
      display: none;
    }
  }
  .min-stack-overflow {
    height: 30px;
    cursor: pointer;
    //640px보다 커지면 실행
    @media (min-width: 640px) {
      display: none;
    }
  }
`;

/** 2023/04/17 - 검색창 구현 by sinyaenok*/
/*Todo : 형광색 hover인지 active인지...알아내서 넣기*/
export const StyledSearchFrom = styled.form`
  margin-right: 10px;
  .search-bar {
    width: 50vw;
    height: 30px;
    border-radius: 3px;
    border: solid 1px rgba(0, 0, 0, 0.3);
    display: flex;
    justify-content: center;
    align-items: center;
    // z-index: 1;
    // opacity: 1;
  }
  .search-bar-input {
    width: 50vw;
    border: none;
    margin: 10px;
    //search 기본 디자인 없애는 코드
    -webkit-appearance: none;
    /* overflow: auto; */
  }
  input:focus {
    outline: none;
  }
  .search-icon {
    margin-left: 10px;
    color: gray;
  }
`;
