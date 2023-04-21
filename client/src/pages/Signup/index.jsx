import StyledSignup, {
  StyledSignupContainer,
  StyledGoggleLogo,
  StyledGoogleBtn,
  StyledTitle,
  StyledSignupInput,
  StyledValid,
  StyledInputContainer,
  StyledSignupBtn,
  StyledSignupLink,
  StyledLogin,
} from "./style";

/** 2023/04/21 - 회원가입 페이지 작성  - by JHH0906 */
const Signup = () => (
  <StyledSignup>
    <StyledGoogleBtn>
      <StyledGoggleLogo>
        <img
          src=" https://cdn-icons-png.flaticon.com/512/2991/2991148.png"
          alt="Googlelogo"
        />
      </StyledGoggleLogo>
      Sign up with Google
    </StyledGoogleBtn>
    <StyledSignupContainer>
      <StyledInputContainer>
        <StyledTitle>Display Name</StyledTitle>
        <StyledSignupInput></StyledSignupInput>
        <StyledTitle>Email</StyledTitle>
        <StyledSignupInput></StyledSignupInput>
        <StyledTitle>password</StyledTitle>
        <StyledSignupInput></StyledSignupInput>
        <StyledValid>
          Passwords must contain at least eight characters, including at least 1
          letter and 1 number.
        </StyledValid>
      </StyledInputContainer>
      <StyledSignupBtn>Sign up</StyledSignupBtn>
    </StyledSignupContainer>
    <StyledLogin>
      Already have an account?
      <StyledSignupLink href="/Login"> Log in</StyledSignupLink>
    </StyledLogin>
  </StyledSignup>
);

export default Signup;
