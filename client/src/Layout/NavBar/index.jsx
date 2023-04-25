// style
import {
  StyledNavBar,
  StyledLoginBtn,
  StyledSignupBtn,
  StyledLogoutBtn,
  StyledMemberBtn,
} from "./style";

//data
import dummy from "../../pages/Members/Data/data.json";

import { Link } from "react-router-dom";

/** 2023/04/12 - NarBar 컴포넌트 - by 1-blue */
const NavBar = () => {
  return (
    <StyledNavBar>
      <LoginBtn />
      <SignupBtn />
      <MemberBtn />
      <LogoutBtn />
    </StyledNavBar>
  );
};

/** 2023/04/23 - 로그인 버튼 - by sinyaenok */
const LoginBtn = () => {
  return (
    <StyledLoginBtn>
      <Link to="/login" className="loginink">
        Log in
      </Link>
    </StyledLoginBtn>
  );
};

/** 2023/04/23 - 회원가입 버튼 - by sinyaenok */
const SignupBtn = () => {
  return (
    <StyledSignupBtn>
      <Link to="/signup" className="signuplink">
        Sign up
      </Link>
    </StyledSignupBtn>
  );
};
/** 2023/04/25 - 로그아웃 버튼 - by sinyaenok */
const LogoutBtn = () => {
  return (
    <StyledLogoutBtn>
      <Link to="/logout" className="logoutlink">
        Log out
      </Link>
    </StyledLogoutBtn>
  );
};

/** 2023/04/25 -  멤버 이미지 버튼 - by sinyaenok */
const MemberBtn = () => {
  return (
    <StyledMemberBtn>
      <Link to="/members/karine/1" className="memberlink">
        <img src={dummy.members[2].img} alt="멤버 이미지" />
      </Link>
    </StyledMemberBtn>
  );
};
export default NavBar;
