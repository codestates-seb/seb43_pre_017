import StyledLogin, {
  StyledLoginContainer,
  StyledGoogleBtn,
  StyledLoginInput,
  StyledTitle,
  StyledLoginBtn,
  StyledSignup,
  StyledSignupLink,
  StyledForgotLink,
  StyledLogo,
  StyledGoggleLogo,
} from "./style";

/** 2023/04/18 - 로그인 페이지 작성  - by JHH0906 */
const Login = () => (
  <StyledLogin>
    <StyledLogo href="/">
      <img
        src="https://cdn.sstatic.net/Sites/stackoverflow/Img/apple-touch-icon@2.png?v=73d79a89bded"
        alt="logo"
      />
    </StyledLogo>
    <StyledGoogleBtn>
      <StyledGoggleLogo>
        <img
          src=" https://cdn-icons-png.flaticon.com/512/2991/2991148.png"
          alt="Googlelogo"
        />
      </StyledGoggleLogo>
      Log in with Google
    </StyledGoogleBtn>
    <StyledLoginContainer>
      <StyledTitle>Email</StyledTitle>
      <StyledLoginInput></StyledLoginInput>
      <StyledTitle>
        Password <StyledForgotLink>Forgot password?</StyledForgotLink>
      </StyledTitle>
      <StyledLoginInput></StyledLoginInput>
      <StyledLoginBtn>Log in</StyledLoginBtn>
    </StyledLoginContainer>
    <StyledSignup>
      Don’t have an account?
      <StyledSignupLink href="/login">Sign up</StyledSignupLink>
    </StyledSignup>
  </StyledLogin>
);

export default Login;
