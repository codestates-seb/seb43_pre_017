import styled from "styled-components";

export const StyledMembers = styled.div`
  max-width: 1100px;
  width: calc(100% - 164px);
  border-top-width: 0;
  border-bottom-width: 0;
  border-left-width: 1px;
  border-right-width: 0;
  padding: 30px 24px 24px 24px;
  box-sizing: border-box;

  /* display */
  .d-flex {
    display: flex;
  }
  .d-grid {
    display: grid;
  }
  .fwn {
    flex-wrap: nowrap;
  }

  /* flex-direction */
  .di-column {
    flex-direction: column;
  }
  .di-row {
    flex-direction: row;
  }
  /* align-items */
  .ai-start {
    align-items: start;
  }
  .ai-center {
    align-items: center;
  }
  .ai-end {
    align-items: end;
  }

  /* justify-content */
  .js-center {
    justify-content: center;
  }
  .js-between {
    justify-content: space-between;
  }

  /* box-size */
  .fg1 {
    flex-grow: 1;
  }
  .mb10 {
    margin-bottom: 10px;
  }
  .mb24 {
    margin-bottom: 24px;
  }
  .p16 {
    padding: 16px;
  }
  .pb10 {
    padding-bottom: 10px;
  }

  h1 {
    font-size: 26px;
    margin-bottom: 24px;
  }

  #sub-bar {
    margin-bottom: 30px;
    height: 40px;
    @media (max-width: 720px) {
      height: 85px;
      align-items: start;
      flex-direction: column;
    }
  }
`;

// 서치창
export const StyledSearchFrom = styled.form`
  height: 100%;

  .search-bar {
    width: 250px;
    /* background-color: #fff; */
    border-radius: 3px;
    border: solid 1px rgba(0, 0, 0, 0.3);
    justify-content: center;
    align-items: center;
    margin: 0 10px 10px 0;
  }

  .search-bar-input {
    background-color: transparent;
    width: 100%;
    height: 100%;
    border: none;
    margin: 10px;
    overflow: auto;
    // 기본 디자인 없애는 코드
    -webkit-appearance: none;
  }
  input:focus {
    outline: none;
  }
  .search-icon {
    margin-left: 10px;
    color: gray;
    font-size: 20px;
  }
`;
