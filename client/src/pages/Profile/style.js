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
  #profile-info {
    width: 100%;
    padding: 0;
  }
  .mb16 {
    margin-bottom: 16px;
  }
  .ps-relative {
    position: relative;
  }
  .d-flex {
    display: flex;
  }
  .mb48 {
    margin-bottom: 48px;
  }
  .ai-center {
    align-items: center;
  }
  .fw-wrap {
    flex.wrap: wrap;
  }
  .gs16 {
    /* margin: calc(var(--su16) / 2 * -1); */
    margin: 16px;
  }
  .gs16 > .flex--item {
    margin: 16px;
  }
  .imgbox {
    border-radius: 5px;
  }
  a {
    text-decoration: none;
    cursor: pointer;
  }
  .userimg {
    display: block;
    border-radius: 5px;
  }
  .gs8 {
    margin: calc(8px / 2 * 1);
  }
  .gs8 > .flex--item {
    margin: calc(8px / 2);
  }

  .lh-xs {
    line-height: 1;
  }
  .fs-headline2 {
    font-size: 2.61538461rem;
  }
  .mb12 {
    margin-bottom: 12px;
  }
  .list-reset {
    list-style: none;
    margin: 0;
    padding: 0;
  }
  .fc-light .fc-black-350 {
    color: gray;
  }
  .mln4 {
    margin-left: calc(4px * -1);
  }
  .gsx {
    margin-top: 0;
    margin-bottom: 0;
  }
  .gs4 {
    margin: calc(4px / 2 * -1);
  }
  .gs4 > .flex--item {
    margin: calc(4px / 2);
  }
  .cake {
    height: 18px;
    width: 18px;
  }
  .ps-absolute {
    position: absolute;
  }
  .r0 {
    right: 0;
  }
  .t0 {
    top: 0;
  }
  .gs6 {
    margin: calc(6px / 2 * -1);
  }
  .gs6 > .flex--item {
    margin: calc(6px / 2);
  }
  #info-edit {
    padding: 9px;
    border: 1px solid rgba(0, 0, 0, 0.2);
    border-radius: 5px;
    :hover {
      background: rgba(0, 0, 0, 0.2);
    }
  }
  .wmx100 {
    max-width: 100%;
  }

  .fl-grow1 {
    flex-grow: 1;
  }
  .lg\:grid__1 {
    grid-template-columns: repeat(1, minmax(0, 1fr));
  }
  .d-grid {
    display: grid;
  }
  .g24 {
    y: 24px;
    x: 24px;
  }
  .h100 {
    height: 100%;
  }
  .fd-column {
    flex-direction: column;
  }
  .mb8 {
    margin-bottom: 8px;
  }
  .ai-end {
    align-items: flex-end;
  }
  .jc-space-between {
    justify-content: space-between;
  }
  .bc-black-3 .bc-black-100 {
    border-color: gray;
  }
  .bar-md {
    border-radius: 5px;
  }
  .ba {
    border-style: solid;
    border-width: 1px;
  }
  .fs-title {
    font-size: 1.61538462rem;
  }
  .mb0 {
    margin-bottom: 0;
  }

  h3 {
    line-height: 1.3;
    margin: 0 0 1em;
  }
  .p24 {
    padding: 24px;
  }
  .flex__center {
    justify-content: center;
    align-items: center;
  }
  .s-empty-state {
    color: green;
    text-align: center;
    margin-left: auto;
    margin-right: auto;
  }
  .wmx4 {
    max-width: 4px;
  }
  .m0 {
    margin: 0;
  }
`;

export default StyledProfile;
