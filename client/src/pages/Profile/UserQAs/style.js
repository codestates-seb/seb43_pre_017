import styled from "styled-components";

export const StyledUserQAs = styled.div`
  /* border: 1px solid rgba(0, 0, 0, 0.2); */
  height: calc(100% - 300px);

  #answer-title,
  #question-title {
    font-size: 26px;
  }

  #answers,
  #questions {
    border: 1px solid rgba(0, 0, 0, 0.2);
    border-radius: 5px;
  }
  a {
    margin: 0 5px 0;
    color: ${({ theme }) => theme.colors.blue600};
  }
`;
