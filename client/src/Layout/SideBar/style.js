import { Link } from "react-router-dom";
import styled, { css } from "styled-components";

/** 2023/03/24 - 하단 네비게이션 바 스타일 컴포넌트 - by 1-blue */
const StyledSideBar = styled.nav`
  position: relative;
  width: 141px;
  height: 100%;

  & > ul {
    position: fixed;
    top: 50px;
    height: inherit;
    padding: 1em 0;

    border-right: 1px solid ${({ theme }) => theme.colors.gray400};
  }
`;

/** 2023/04/16 - 링크 폴더 스타일 컴포넌트 - by 1-blue */
const StyledLink = styled(Link)`
  min-width: 140px;
  padding: 0.4em 1em;

  display: flex;
  align-items: center;

  cursor: pointer;

  ${({ match }) =>
    match &&
    css`
      background-color: ${({ theme }) => theme.colors.gray200};
      border-right: 3px solid ${({ theme }) => theme.colors.main400};

      font-weight: bold;

      &:hover {
        svg {
          stroke-width: 2.5;
        }
      }
    `}

  & > * + * {
    margin-left: 0.4em;
  }

  &:hover {
    text-decoration: underline;
    text-underline-offset: 4px;

    font-weight: bold;

    svg {
      stroke-width: 2.5;
    }
  }
`;

export default StyledSideBar;
export { StyledLink };
