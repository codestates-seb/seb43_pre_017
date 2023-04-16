import styled from "styled-components";

/** 2023/04/12 - 푸터를 감싸는 컴포넌트의 스타일 - by 1-blue */
const StyledFoorter = styled.footer`
  width: 100%;
  height: 320px;
  background-color: ${({ theme }) => theme.colors.zinc800};
  display: flex;
`;
/** 2023/04/16 - 푸터 스택오버플로우로고 스타일 - by JHH0906 */
export const StyledLogo = styled.div`
  width: 10%;
  height: 100%;
  margin-left: 4px;
  > img {
    width: 60px;
    float: right;
    margin-top: 6px;
    margin-right: 8px;
  }
`;
/** 2023/04/16 - 푸터 스택오버플로우카테고리 스타일 - by JHH0906 */
export const StyledStackoverflow = styled.div`
  width: 15%;
  display: flex;
  flex-direction: column;
  margin-left: 8px;
  margin-top: 32px;
`;
/** 2023/04/16 - 푸터 스택오버플로우카테고리Title 스타일 - by JHH0906 */
export const StyledTitle = styled.div`
  color: ${({ theme }) => theme.colors.stone300};
  font-size: 14px;
  font-weight: 700;
`;
/** 2023/04/16 - 푸터 스택오버플로우카테고리Ul 스타일 - by JHH0906 */
export const StyledUl = styled.ul`
  float: left;
  margin-top: 12px;
  color: ${({ theme }) => theme.colors.gray400};
  font-size: 14px;
  font-weight: 500;
`;
/** 2023/04/16 - 푸터 스택오버플로우카테고리Li 스타일 - by JHH0906 */
export const StyledLi = styled.li`
  padding-top: 12px;
`;
/** 2023/04/16 - 푸터 프로덕트카테고리 스타일 - by JHH0906 */
export const StyledProducts = styled.div`
  width: 10%;
  display: flex;
  flex-direction: column;
  margin-top: 32px;
`;
/** 2023/04/16 - 푸터 컴퍼니카테고리 스타일 - by JHH0906 */
export const StyledCompnay = styled.div`
  width: 15%;
  display: flex;
  flex-direction: column;
  margin-top: 32px;
  margin-left: 32px;
`;
export default StyledFoorter;
