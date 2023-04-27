import styled from "styled-components";

/** 2023/04/19 - 하단 버튼들 스타일 - by 1-blue */
const StyledObjectFooter = styled.section`
  display: flex;
  justify-content: space-between;

  & > .buttons {
    & > * + * {
      margin-left: 0.4em;
    }
    & > button,
    & > a {
      color: ${({ theme }) => theme.colors.gray400};
      cursor: pointer;

      &:hover {
        color: ${({ theme }) => theme.colors.gray500};
      }
    }
  }

  & > .author {
    padding: 0.6em;

    display: flex;
    background-color: ${({ theme }) => theme.colors.blue200};
    border-radius: 0.2em;

    & > * + * {
      margin-left: 0.4em;
    }

    & > a > .avatar {
      width: 40px;
      height: 40px;

      border-radius: 0.2em;
      background-color: ${({ theme }) => theme.colors.gray200};
    }

    & > div {
      display: flex;
      flex-flow: column nowrap;
      justify-content: space-evenly;

      & > time {
        font-size: 0.7rem;
        color: ${({ theme }) => theme.colors.gray400};
      }

      a {
        color: ${({ theme }) => theme.colors.gray400};
        font-size: 0.9rem;
        cursor: pointer;

        &:hover {
          color: ${({ theme }) => theme.colors.gray500};
        }
      }
    }
  }
`;

export default StyledObjectFooter;
