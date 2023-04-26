import styled from "styled-components";

/** 2023/04/21 - article 컴포넌트 스타일 - by 1-blue */
const StyledArticle = styled.article`
  display: flex;

  /* content / share,edit / author / comment */
  & > .article-content-wrapper {
    flex: 1;

    & > * + * {
      margin-top: 1em;
    }

    & > .comments-wrapper {
      font-size: 0.8rem;
      color: ${({ theme }) => theme.colors.gray700};
      padding-bottom: 0.6em;
      border-bottom: 1px solid ${({ theme }) => theme.colors.gray300};

      & > * + * {
        margin-top: 0.6em;
      }
    }

    & > button[type="button"] {
      cursor: pointer;

      &:hover {
        font-weight: bold;
      }
    }
  }
`;

export default StyledArticle;
