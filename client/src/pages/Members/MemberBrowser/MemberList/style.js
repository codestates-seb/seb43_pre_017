import styled from "styled-components";

export const StyledMemberList = styled.div`
  display: grid;
  grid-template-columns: repeat(4, minmax(0, 1fr));
  gap: 1em;
  @media (max-width: 1024px) {
    grid-template-columns: repeat(3, 1fr);
  }
  @media (max-width: 720px) {
    grid-template-columns: repeat(2, 1fr);
  }
`;

export const StyledMember = styled.div`
  display: flex;
  padding: 10px;

  #item-img-link {
    height: 100%;
  }

  .d-flex {
    display: flex;
  }
  .ai-center {
    align-items: center;
  }
  .js-center {
    justify-content: center;
  }
  .f1 {
    flex: 1;
  }
  /* 이미지 */
  .item-img {
    background-color: tomato;
    width: 48px;
    height: 48px;
  }

  /* 이름, 질문, 답변수 */
  .item-box {
    margin-left: 10px;
    display: flex;
    flex-direction: column;
  }

  #item-name-link {
    color: ${({ theme }) => theme.colors.blue600};
    /* border: 1px solid green; */
    font-weight: 500;
    font-size: 1.1em;
    flex: 1;
  }

  .item-question {
    color: ${({ theme }) => theme.colors.gray500};
    align-items: center;
    font-size: 1em;
    flex: 1;
    display: flex;
  }

  .item-answer {
    color: ${({ theme }) => theme.colors.gray500};
    font-size: 1em;
    align-items: center;
    flex: 1;
    display: flex;
  }
`;
