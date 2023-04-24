// style
import { StyledNavBar, StyledLoginBtn, StyledSignupBtn } from "./style";

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
      <a href="/login">Log in</a>
    </StyledLoginBtn>
  );
};

/** 2023/04/23 - 회원가입 버튼 - by sinyaenok */
const SignupBtn = () => {
  return (
    <StyledSignupBtn>
      <a href="/signup">Sign up</a>
    </StyledSignupBtn>
  );
};
export default NavBar;
