import styled from "styled-components";

/** 20230422 - 정렬 탭들을 감싸는 스타일 - by sinyaenok */
export const StyledSortTab = styled.div`
  height: 45px;
  border: 1px solid ${({ theme }) => theme.colors.gray500};
  border-radius: 3px;
  display: flex;
  align-items: center;
`;

// 탭 기본 세팅
const StyledBaseTab = styled.div`
  height: 100%;
  font-size: 15px;
  display: flex;
  align-items: center;
  a {
    color: ${({ theme }) => theme.colors.gray600};
    height: 100%;
    display: flex;
    align-items: center;
    padding: 8px 10px;
  }
`;

// 신규 유저 (햔재 탭)
export const StyledNewsetTab = styled(StyledBaseTab)`
  background-color: ${({ theme }) => theme.colors.gray200};
  border-right: 1px solid gray;
`;

// 질문 많은 순
export const StyledArticleTab = styled(StyledBaseTab)`
  border-right: 1px solid gray;
  a {
    :hover {
      background-color: ${({ theme }) => theme.colors.gray50};
      color: ${({ theme }) => theme.colors.gray700};
    }
  }
`;

// 답변 많은 순
export const StyledAnswerTab = styled(StyledBaseTab)`
  a {
    :hover {
      background-color: ${({ theme }) => theme.colors.gray50};
      color: ${({ theme }) => theme.colors.gray700};
    }
  }
`;
