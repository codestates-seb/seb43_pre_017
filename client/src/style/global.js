import { createGlobalStyle } from "styled-components";
import { reset } from "styled-reset";

/** 2023/03/23 - 전역 스타일 ( + reset css ) - by 1-blue */
export const GlobalStyle = createGlobalStyle`
  ${reset}
  * {
    box-sizing: border-box;
  }
  html {
    font-size: 16px;
  }

  /* 모바일에서는 14px */
`;
