import styled from "styled-components";

/** 2023/04/18 - 상세 article 페이지 스타일 - by 1-blue */
const StyledArticlePage = styled.article`
  flex: 1;
  padding: 2em 1em;

  & > * + * {
    margin-top: 1em;
  }

  /* title / ask */
  & > .top-wrapper {
    display: flex;
    justify-content: space-between;
    align-items: center;

    & > h1 {
      font-size: 2rem;
      font-weight: bolder;

      white-space: pre-wrap;
    }

    & > a {
      padding: 0.8em 1em;

      color: #fff;
      background-color: ${({ theme }) => theme.colors.blue500};
      border-radius: 0.2em;
      white-space: nowrap;

      transition: all 0.2s;

      &:hover {
        background-color: ${({ theme }) => theme.colors.blue600};
      }
    }
  }

  /*  */
  & > .sub-top-wrapper {
    display: flex;

    /* time */
    & > time,
    & > span {
      margin-left: 1em;

      color: ${({ theme }) => theme.colors.gray600};
      font-size: 0.8rem;

      &:first-child {
        margin-left: 0;
      }
    }
  }

  & hr {
    border: 1px solid ${({ theme }) => theme.colors.gray300};
    margin: 1.4em 0;
  }

  /* 답변 제출 폼 */
  & > form {
    & > * + * {
      margin-top: 1.6em;
    }

    & > span {
      font-size: 1.2rem;
      font-weight: bold;
    }

    & > button[type="submit"] {
      padding: 0.8em 1em;

      color: #fff;
      background-color: ${({ theme }) => theme.colors.blue500};
      border-radius: 0.2em;
      cursor: pointer;

      transition: all 0.2s;

      &:hover {
        background-color: ${({ theme }) => theme.colors.blue600};
      }
    }
  }
`;

export default StyledArticlePage;
