// style
import { StyledNavBar } from "./style";

/** 2023/04/12 - NarBar 컴포넌트 - by 1-blue */
const NavBar = () => (
  <StyledNavBar>
    {/* 로그인 버튼 */}
    <a href="/login">
      <div id="login-btn" className="header-btn">
        Log in
      </div>
    </a>

    {/* 회원가입 버튼 */}
    <a href="/signup">
      <div id="signup-btn" className="header-btn">
        Sign up
      </div>
    </a>
  </StyledNavBar>
);

export default NavBar;
