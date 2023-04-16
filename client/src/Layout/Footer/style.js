import styled from "styled-components";

/** 2023/04/12 - 푸터를 감싸는 컴포넌트의 스타일 - by 1-blue */
const StyledFoorter = styled.footer`
  width: 100%;
  height: 320px;
  background-color: ${({ theme }) => theme.colors.zinc800};
`;
/** 2023/04/16 - 푸터 스택오버플로우 스타일 - by JHH0906 */
export const StyledLogo = styled.div`
  width: 11%;
  height: 320px;

  > img {
    width: 60px;
    float: right;
    margin-top: 6px;
    margin-right: 8px;
  }
`;
export default StyledFoorter;
