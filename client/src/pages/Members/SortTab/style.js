import styled from "styled-components";

/** 20230422 - 정렬 탭들을 감싸는 스타일 - by sinyaenok */
export const StyledSortTab = styled.div`
  height: 100%;
  display: flex;
  align-items: center;
  border: 1px solid ${({ theme }) => theme.colors.gray500};
  border-radius: 5px;
`;

// 탭 기본 세팅
const StyledBaseTab = styled.button`
  display: flex;
  height: 100%;

  align-items: center;
  font-size: 12px;
  white-space: nowrap;
  padding: 0 10px;
  cursor: pointer;
`;

// 신규 유저 (햔재 탭)
export const StyledNewsetTab = styled(StyledBaseTab)`
  border-radius: 5px 0 0 5px;
  background-color: ${({ theme }) => theme.colors.gray200};
  border-right: 1px solid gray;
  color: ${({ theme }) => theme.colors.gray700};
`;

// 예전 유저
export const StyledOldTab = styled(StyledBaseTab)`
  background-color: #fff;
  border-radius: 0 5px 5px 0;
  color: ${({ theme }) => theme.colors.gray600};

  :hover {
    background-color: ${({ theme }) => theme.colors.gray50};
    color: ${({ theme }) => theme.colors.gray700};
  }
`;
