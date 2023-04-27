import styled from "styled-components";
import { Link } from "react-router-dom";

/** 2023/04/25 - 질문 li - by JHH0906 */
const StyledList = styled.li`
  display: flex;
  border-bottom: 1px solid rgb(216, 217, 220);
  box-sizing: border-box;
  padding: 16px;
  margin-right: 20px;
`;

/** 2023/04/25 - votes, answers, views 수 - by JHH0906 */
export const StyledCounts = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  color: #6a737c;
  margin-right: 16px;
  line-height: 1.9;
  div {
    min-width: 105px;
    text-align: right;
  }
  .vote {
    color: #232629;
  }
  .count {
    font-weight: bold;
    width: 100px;
    margin-left: 10px;
  }
`;

/** 2023/04/25 - 질문 - by JHH0906 */
export const StyledQuestion = styled.article`
  color: #3b4045;
  line-height: 1.3;
  width: 100%;
`;

/** 2023/04/25 - 질문 제목 - by JHH0906 */
export const StyledQuestionTitle = styled(Link)`
  text-decoration: none;
  color: ${({ theme }) => theme.colors.blue500};
  cursor: pointer;
  font-size: 17px;
  vertical-align: top;
  word-break: break-all;
  &:hover {
    color: ${({ theme }) => theme.colors.sky500};
  }
  &:visited {
    text-decoration: none;
  }
`;

/** 2023/04/25 - 질문 내용 - by JHH0906 */
export const StyledContent = styled.div`
  display: inline-block;
  margin-top: 6px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: normal;
  line-height: 1.4;
  height: 2.8em;
  text-align: left;
  word-break: break-all;
  word-wrap: break-word; // 단어 단위로 줄바꿈
  display: -webkit-box; // 유연하게 height를 증감시킬 수 있는 플렉스 박스형태로 변환
  -webkit-line-clamp: 2; // 보여줄 줄 수
  -webkit-box-orient: vertical; // 플렉스 박스의 방향 설정(가로)
`;

/** 2023/04/25 - 유저 + 작성일을 담은 컨테이너 - by JHH0906 */
export const StyledInfoContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: flex-end;
  margin: 9px 0 14px 0;
  white-space: nowrap;
`;

/** 2023/04/25 - 유저 + 작성일 - by JHH0906 */
export const StyledPostInfo = styled.div`
  display: flex;
`;

export const StyeldAsk = styled.div`
  display: flex;
  margin-right: 2px;
`;
export const StyledUser = styled(Link)`
  text-decoration: none;
  color: ${({ theme }) => theme.colors.blue500};
  cursor: pointer;
  margin-right: 4px;
  :visited {
    text-decoration: none;
  }
  &:hover {
    color: ${({ theme }) => theme.colors.sky500};
  }
`;

export const StyledUserPic = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  color: rgb(250, 250, 250, 0.95);
  background-color: ${({ theme }) => theme.colors.blue500};
  font-size: 11px;
  width: 16px;
  height: 16px;
  border-radius: 4px;
  margin-right: 4px;
`;

export default StyledList;
