import { useLocation } from "react-router-dom";

// component
import Icon from "../../components/common/Icon";

// style
import StyledSideBar, { StyledLink } from "./style";

/** 2023/04/12 - SideBar 컴포넌트 - by 1-blue */
const SideBar = () => {
  const { pathname } = useLocation();

  if (window.location.pathname === "/Login") return null;
  if (window.location.pathname === "/signup") return null;
  if (window.location.pathname === "/logout") return null;
  return (
    <StyledSideBar>
      <>
        <StyledLink to="/" match={pathname === "/" ? "true" : undefined}>
          <button type="button">
            <Icon shape="earth" fill={pathname === "/"} />
          </button>
          <span>Questions</span>
        </StyledLink>
      </>
      <>
        <StyledLink
          to="/users"
          match={pathname === "/users" ? "true" : undefined}
        >
          <button type="button">
            <Icon shape="users" fill={pathname === "/users"} />
          </button>
          <span>Users</span>
        </StyledLink>
      </>
      <>
        <StyledLink
          to="/tags"
          match={pathname === "/tags" ? "true" : undefined}
        >
          <button type="button">
            <Icon shape="tags" fill={pathname === "/tags"} />
          </button>
          <span>Tags</span>
        </StyledLink>
      </>
    </StyledSideBar>
  );
};

export default SideBar;
