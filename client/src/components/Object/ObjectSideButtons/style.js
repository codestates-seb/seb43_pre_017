import styled from "styled-components";

/** 2023/04/19 - 좌측 사이드 버튼들 스타일 - by 1-blue */
const StyledObjectSideButtons = styled.section`
  display: flex;
  flex-flow: column nowrap;
  align-items: center;

  padding-right: 1em;

  /** 좋아요, svg */
  & > button[type="button"] {
    cursor: pointer;

    & > svg {
      fill: ${({ theme }) => theme.colors.gray400};
      transition: all 0.2s;

      &:hover {
        fill: ${({ theme }) => theme.colors.gray600};
      }
    }

    &:hover ~ span {
      color: ${({ theme }) => theme.colors.gray600};
    }
  }
  /** svg */
  & > svg {
    color: ${({ theme }) => theme.colors.gray400};
    transition: all 0.2s;
    cursor: pointer;
    margin-bottom: 0.6em;

    &:hover {
      color: ${({ theme }) => theme.colors.gray600};
    }
  }

  & > span {
    font-size: 1.2rem;
    color: ${({ theme }) => theme.colors.gray400};
  }
`;

export default StyledObjectSideButtons;
