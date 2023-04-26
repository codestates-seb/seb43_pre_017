import styled from "styled-components";

const StyledProfile = styled.div`
  height: 100%;
  max-width: 1100px;
  width: calc(100% - 164px);
  padding: 24px;
  background-color: ${({ theme }) => theme.colors.gray50};

  /* display */
  .d-flex {
    display: flex;
  }
  .d-grid {
    display: grid;
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
