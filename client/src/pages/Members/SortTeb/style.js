import styled from "styled-components";

export const StyledSortTeb = styled.div`
  height: 45px;
  border: 1px solid gray;
  border-radius: 3px;
  display: flex;
  align-items: center;
`;

const StyledBaseTeb = styled.div`
  height: 100%;
  padding: 8px 10px;
  color: gray;
  font-size: 15px;
  display: flex;
  align-items: center;
`;

export const StyledNewsetTeb = styled(StyledBaseTeb)`
  background-color: ${({ theme }) => theme.colors.slate200};
  border-right: 1px solid gray;
`;

export const StyledArticleTeb = styled(StyledBaseTeb)`
  background-color: ${({ theme }) => theme.colors.slate200};
  border-right: 1px solid gray;
`;

export const StyledAnswerTeb = styled(StyledBaseTeb)``;
