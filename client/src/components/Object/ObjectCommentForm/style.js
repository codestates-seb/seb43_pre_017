import styled from "styled-components";

/** 2023/04/19 - 댓글 폼 컴포넌트 스타일 - by 1-blue */
const StyledObjectCommentForm = styled.form`
  display: flex;

  & > textarea {
    flex: 1;
    padding: 0.4em 0.6em;
    margin: auto 0;
    border: 0;
    border: 1px solid ${({ theme }) => theme.colors.gray400};

    resize: none;

    /* 스크롤바 제거 */
    -ms-overflow-style: none;
    scrollbar-width: none;
    &::-webkit-scrollbar {
      display: none;
    }

    &:focus {
      outline: none;
    }
  }
  & > button {
    padding: 0.6em;
    margin-left: 0.6em;

    border-radius: 0.2em;
    color: #fff;
    background-color: ${({ theme }) => theme.colors.blue500};
    cursor: pointer;

    &:hover {
      background-color: ${({ theme }) => theme.colors.blue600};
    }
  }
`;

export default StyledObjectCommentForm;
