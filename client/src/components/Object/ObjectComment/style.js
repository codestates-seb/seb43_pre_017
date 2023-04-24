import styled from "styled-components";

/** 2023/04/21 - 댓글 컴포넌트 스타일 - by 1-blue */
const StyledObjectComment = styled.li`
  padding-top: 0.6em;
  border-top: 1px solid ${({ theme }) => theme.colors.gray300};

  & > * + * {
    margin-top: 0.6em;
  }

  & > textarea {
    width: 100%;

    resize: none;
    line-height: 1.3;

    background-color: transparent;
    border: 1px solid ${({ theme }) => theme.colors.blue400};

    &:disabled {
      border: 0;
    }
    &:focus {
      border: 2px solid ${({ theme }) => theme.colors.blue500};
      outline: none;
    }

    /* 스크롤바 제거 */
    -ms-overflow-style: none;
    scrollbar-width: none;
    &::-webkit-scrollbar {
      display: none;
    }
  }

  & > div {
    font-size: 0.9em;
    margin: 0;

    & > * + * {
      margin-left: 0.6em;
    }

    & > a {
      color: ${({ theme }) => theme.colors.blue400};

      &:hover {
        color: ${({ theme }) => theme.colors.blue500};
      }
    }

    & > time {
      color: ${({ theme }) => theme.colors.gray400};
    }

    & > button {
      font-size: 0.8rem;

      color: ${({ theme }) => theme.colors.gray400};

      cursor: pointer;

      &:hover {
        color: ${({ theme }) => theme.colors.gray500};
      }
    }
  }
`;

export default StyledObjectComment;
