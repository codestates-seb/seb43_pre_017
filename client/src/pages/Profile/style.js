import styled from "styled-components";

const StyledProfile = styled.div`
  max-width: 1100px;
  width: calc(100% - 164px);
  background-color: #fff;
  border-top-width: 0;
  border-bottom-width: 0;
  border-left-width: 1px;
  border-right-width: 0;
  padding: 24px;
  box-sizing: border-box;
  /* position: relative; */

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

  /* box-size */
  .fg1 {
    flex-grow: 1;
  }
  .mb10 {
    margin-bottom: 10px;
  }
  .p16 {
    padding: 16px;
  }
  .pb10 {
    padding-bottom: 10px;
  }
`;

export default StyledProfile;
