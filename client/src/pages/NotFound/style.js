import styled from "styled-components";

/** 2023/04/23 - 찾을 수 없는 페이지 컴포넌트 스타일 - by 1-blue */
const StyledNotFound = styled.article`
  display: flex;
  flex-flow: column nowrap;
  justify-content: center;
  align-items: center;

  width: 100%;
  height: 80vh;

  & > section {
    & > * + * {
      margin-top: 1em;
    }

    & > h1 {
      font-size: 1.6rem;
      font-weight: bold;
    }

    & > div {
      & > * + * {
        margin-top: 1em;
      }
    }
  }

  @media ${({ theme }) => theme.mediaSize.md} {
    flex-flow: row nowrap;
  }
`;

export default StyledNotFound;
