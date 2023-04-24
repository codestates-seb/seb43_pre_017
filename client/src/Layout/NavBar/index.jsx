// style
import { StyledNavBar, StyledLoginBtn, StyledSignupBtn } from "./style";

import { Link } from "react-router-dom";
/** 2023/04/12 - NarBar 컴포넌트 - by 1-blue */
const NavBar = () => {
  return (
    <StyledNavBar>
      <LoginBtn />
      <SignupBtn />
    </StyledNavBar>
  );
};

/** 2023/04/23 - 로그인 버튼 - by sinyaenok */
const LoginBtn = () => {
  return (
    <StyledLoginBtn>
      <Link to="/login">Log in</Link>
    </StyledLoginBtn>
  );
};

/** 2023/04/23 - 회원가입 버튼 - by sinyaenok */
const SignupBtn = () => {
  return (
    <StyledSignupBtn>
      <Link to="/signup">Sign up</Link>
    </StyledSignupBtn>
  );
};
export default NavBar;
