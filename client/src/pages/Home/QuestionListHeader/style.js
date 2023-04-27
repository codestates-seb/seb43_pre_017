import styled from "styled-components";
import { Link } from "react-router-dom";

/** 2023/04/25 - 질문페이지 헤더 - by JHH0906 */
const StyledQuestionHeader = styled.header`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 135px;
  padding: 25px 0px 10px 25px;
  border-bottom: 1px solid ${({ theme }) => theme.colors.gray300};
  color: ${({ theme }) => theme.colors.gray900};
`;

/** 2023/04/25 - 질문페이지 타이틀 + 버튼 - by JHH0906 */
export const StyledInfo = styled.h1`
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 26px;
`;

/** 2023/04/25 - 질문페이지 버튼컨테이너 - by JHH0906 */
export const StyledBtnContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-top: 25px;
  .questions-num {
    font-size: 17px;
  }
`;

/** 2023/04/25 - 질문페이지 페이지이동 - by JHH0906 */
export const StyledPageMove = styled(Link)`
  text-decoration: none;
`;

/** 2023/04/25 - 질문페이지 질문버튼 - by JHH0906 */
export const StyledAskBtn = styled.button`
  min-width: 103px;
  height: 38px;
  color: white;
  background-color: ${({ theme }) => theme.colors.sky500};
  border: 1px solid transparent;
  box-shadow: inset 0 1px 0 0 ${({ theme }) => theme.colors.sky300};
  border-radius: 3px;
  white-space: nowrap;
  margin-left: 10px;
  cursor: pointer;
  &:hover {
    background-color: ${({ theme }) => theme.colors.sky700};
  }
`;

/** 2023/04/25 - 질문페이지 정렬 컨테이너 - by JHH0906 */
export const StyledSortContainer = styled.div`
  vertical-align: baseline;
  border: 1px solid rgb(148, 156, 163);
  border-radius: 5px;
`;

/** 2023/04/25 - 질문페이지 정렬 버튼 - by JHH0906 */
export const StyledSortBtn = styled.button`
  padding: 10px;
  height: 35px;
  color: #6a737c;
  background-color: white;
  border: none;
  font-size: 12px;
  &.left-btn {
    border-right: 1px solid rgb(148, 156, 163);
    border-top-left-radius: 5px 5px;
    border-bottom-left-radius: 5px 5px;
  }
  &.right-btn {
    border-left: 1px solid rgb(148, 156, 163);
    border-top-right-radius: 5px 5px;
    border-bottom-right-radius: 5px 5px;
  }
  &.clicked {
    background-color: #e3e6e8;
    color: #3b4045;
    pointer-events: none;
  }
  &:hover {
    background-color: rgb(247, 247, 247);
    color: #525960;
  }
`;

export default StyledQuestionHeader;
