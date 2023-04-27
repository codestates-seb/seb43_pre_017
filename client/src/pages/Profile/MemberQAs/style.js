import styled from "styled-components";

export const StyledMemberQAs = styled.div`
  display: flex;
  flex-direction: column;
  height: 100%;
`;

// 답
export const StyledAnswers = styled.div`
  display: flex;
  flex-direction: column;
  padding: 20px;
`;
export const StyledAnswersTitle = styled.div`
  .answers-title {
    font-size: 24px;
  }
`;
export const StyledAnswersForm = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 5em;
  border: 1px solid ${({ theme }) => theme.colors.gray300};
  border-radius: 3px;
  white-space: nowrap;
  div {
    color: ${({ theme }) => theme.colors.gray500};
  }

  .answers-link {
    color: ${({ theme }) => theme.colors.blue600};
    margin: 0 0.5em;
  }
`;

// 질문
export const StyledQuestions = styled.div`
  display: flex;
  flex-direction: column;
  padding: 20px;
  margin-bottom: 30px;
`;
export const StyledQuestionsTitle = styled.div`
  .questions-title {
    font-size: 24px;
  }
`;
export const StyledQuestionsForm = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 5em;
  border: 1px solid ${({ theme }) => theme.colors.gray300};
  border-radius: 3px;
  white-space: nowrap;

  div {
    color: ${({ theme }) => theme.colors.gray500};
  }

  .questions-link {
    color: ${({ theme }) => theme.colors.blue600};
    margin: 0 0.5em;
  }
`;
