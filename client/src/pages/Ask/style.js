import styled from "styled-components";

/** 2023/04/24 - 질문 생성 페이지 스타일 - by 1-blue */
const StyledAsk = styled.form`
  margin: 0em 1em 4em;

  & > * + * {
    margin-top: 1em;
  }

  & > .title-wraaper {
    margin: 4em 0;

    /* 제목 */
    & > h1 {
      font-size: 1.6rem;
      font-weight: bold;
    }
  }

  & > .description-wrapper {
    padding: 2em;
    background-color: ${({ theme }) => theme.colors.blue100};
    border-radius: 0.2em;
    border: 2px solid ${({ theme }) => theme.colors.blue200};

    & > * + * {
      margin-top: 1em;
    }

    & > h2 {
      font-size: 1.2rem;
      font-weight: bold;
    }

    & > p {
    }

    & > div {
      & > h5 {
        font-size: 0.9rem;
        font-weight: bold;
      }

      & > ul {
        margin-left: 2em;
        list-style: disc;
        font-size: 0.85rem;

        & > * + * {
          margin-top: 0.2em;
        }
      }
    }
  }

  & > .title-input-wrapper {
    padding: 2em;

    background-color: #fff;
    border: 2px solid ${({ theme }) => theme.colors.gray100};

    & > * + * {
      margin-top: 0.6em;
    }

    & > input {
      padding: 0.5em 0.6em;

      width: 100%;

      border-radius: 0.2em;
      border: 2px solid ${({ theme }) => theme.colors.gray300};

      &:focus {
        outline: none;
        border: 2px solid ${({ theme }) => theme.colors.blue300};
        box-shadow: 0 0 6px ${({ theme }) => theme.colors.blue400};
      }

      &::placeholder {
        color: ${({ theme }) => theme.colors.gray300};
      }
    }

    & > .title {
      font-weight: bold;
    }
    & > .description {
      font-size: 0.8rem;
    }
  }

  & > .description-input-wrapper {
    padding: 2em;

    background-color: #fff;
    border: 2px solid ${({ theme }) => theme.colors.gray100};

    & > * + * {
      margin-top: 0.6em;
    }

    & > .title {
      font-weight: bold;
    }
    & > .description {
      font-size: 0.8rem;
    }
  }

  & > button[type="submit"] {
    padding: 1em;

    border-radius: 0.4em;
    color: #fff;

    background-color: ${({ theme }) => theme.colors.blue500};
    cursor: pointer;

    & > * + * {
      margin-top: 0.6em;
    }

    &:hover {
      background-color: ${({ theme }) => theme.colors.blue600};
    }
  }
`;

export default StyledAsk;
