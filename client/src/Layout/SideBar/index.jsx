import { useLocation } from "react-router-dom";

// component
import Icon from "../../components/common/Icon";

// style
import StyledSideBar, { StyledLink } from "./style";

/** 2023/04/12 - SideBar 컴포넌트 - by 1-blue */
const SideBar = () => {
  const { pathname } = useLocation();

  if (pathname === "/Login") return null;
  if (pathname === "/signup") return null;
  if (pathname === "/logout") return null;
  if (pathname.includes("/questions/ask")) return null;

  return (
    <StyledSideBar>
      <ul>
        <StyledLink
          to="/"
          match={
            pathname === "/" || pathname.includes("/questions/")
              ? "true"
              : undefined
          }
        >
          <button type="button">
            <Icon
              shape="earth"
              fill={pathname === "/" || pathname.includes("/questions/")}
            />
          </button>
          <span>Questions</span>
        </StyledLink>

        <StyledLink
          to="/members"
          match={pathname === "/members" ? "true" : undefined}
        >
          <button type="button">
            <Icon shape="users" fill={pathname === "/members"} />
          </button>
          <span>Members</span>
        </StyledLink>

        <StyledLink
          to="/tags"
          match={pathname === "/tags" ? "true" : undefined}
        >
          <button type="button">
            <Icon shape="tags" fill={pathname === "/tags"} />
          </button>
          <span>Tags</span>
        </StyledLink>
      </ul>
    </StyledSideBar>
  );
};

export default SideBar;
