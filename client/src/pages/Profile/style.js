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

  .content {
    width: 100%;
    padding: 0;
  }
  /* margin */
  .m16 {
    margin: 16px;
  }
  .ml10 {
    margin-left: 10px;
  }
  .mb10 {
    margin-bottom: 10px;
  }
  .mb16 {
    margin-bottom: 16px;
  }
  .mr10 {
    margin-right: 10px;
  }

  .d-flex {
    display: flex;
  }
  .imgbox {
    margin: 16px;
  }
  .userImg {
    border-radius: 3px;
  }
  .wh100 {
    width: 100%;
    height: 100%;
  }
  .ai-center {
    align-items: center;
  }
  .username {
    font-size: 30px;
  }
  .info-box1 {
    border: 1px solid blue;
    /* position: relative; */
  }
  .edit-btn-box {
    padding: 10px;
    border: 1px solid red;
    align-items: end;
    /* flex-grow: 1; */
  }

  .info-box {
    /* border: 1px solid green; */
    height: 550px;
  }
  .answers {
    /* border: solid 1px red; */
    height: 250px;
    flex-direction: column;
  }
  .questions {
    /* border: solid 1px blue; */
    height: 250px;
    flex-direction: column;
  }

  .fg1 {
    flex-grow: 1;
  }
  .fg2 {
    flex-grow: 2;
  }

  .title {
    /* border: 1px solid black; */
    align-items: end;
    font-size: 30px;
  }
  .eg {
    border: 1px solid black;
    border-radius: 3px;
    text-align: center;
    width: 100%;
    justify-content: center;
  }
`;

export default StyledProfile;
