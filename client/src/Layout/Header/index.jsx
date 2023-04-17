// style
import {
  StyledHeader,
  StyledContaier,
  StyledLogo,
  StyledForm,
  StyledLogin,
  StyledSignup,
} from "./style";

/** 2023/04/16 - 헤더를 감싸는 컴포넌트 - by sinyaenok */
const Header = () => {
  return (
    <StyledHeader>
      <StyledContaier>
        <StyledLogo>
          <img
            src="https://stackoverflow.design/assets/img/logos/so/logo-stackoverflow.svg"
            alt="stackoverflow"
          />
        </StyledLogo>
        <StyledForm>
          <input type="text" placeholder="Search..." />
        </StyledForm>
        <StyledLogin>Login</StyledLogin>
        <StyledSignup>Signup</StyledSignup>
      </StyledContaier>
    </StyledHeader>
  );
};

export default Header;
