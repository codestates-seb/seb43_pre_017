import styled from "styled-components";

/** 2023/04/16 - 아이콘 컴포넌트 스타일 - by 1-blue */
const StyledIcon = styled.svg`
  width: 1.4em;
  height: 1.4em;

  /** 아이콘 hover ( 버튼/앵커에 감싸져 있다면 버튼/앵커에 hover 시 실행 ) */
  button:hover > &,
  a:hover > & {
    color: ${({ hover }) => hover};
    stroke-width: ${({ strokeWidth }) => strokeWidth && +strokeWidth + 1};
    cursor: pointer;
  }
`;

export default StyledIcon;
