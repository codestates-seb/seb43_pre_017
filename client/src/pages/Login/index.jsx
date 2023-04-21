import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

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
const login = (setLogin) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [emailMessage, setEmailMessage] = useState("");
  const [passwordMessage, setPasswordMessage] = useState("");
  const navigate = useNavigate();

  /** 2023/04/19 이메일 유효성 검사 이벤트  -by JHH0906 */
  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };
  /** 2023/04/19 비밀번호 유효성 검사 이벤트  -by JHH0906 */
  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };
  /** 2023/04/19 이메일 비밀번호 유효성 검사  -by JHH0906 */
  const handleSubmit = async (e) => {
    e.preventDefault();

    if (email.length < 1) {
      setEmailMessage("Email cannot be empty.");
    } else if (!email.includes("@")) {
      setEmailMessage("The email is not a valid email address.");
    } else {
      setEmailMessage(null);
    }
    if (password.length < 1) {
      setPasswordMessage("Password cannot be empty.");
    } else {
      setPasswordMessage(null);
      return;
    }
    const header = {
      headers: {
        "Content-Type": `application/json`,
      },
    };

    const reqbody = JSON.stringify({
      email: email,
      password: password,
    });
    axios.defaults.withCredentials = true;
    axios
      .post("/api/login", { reqbody, header })
      .then((res) => {
        if (res.status === 200) {
          const accessToken = res.headers.get("Authorization");
          const refreshToken = res.headers.get("refresh");
          localStorage.setItem("access_token", accessToken);
          localStorage.setItem("refresh_token", refreshToken);
        }
        return res;
      })
      .then((res) => {
        console.log(res.data);
        setLogin(true);
        navigate("/");
      })
      .catch(() => {
        alert("실패");
        setEmail("");
        setPassword("");
      });
  };

  return (
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
        <StyledLoginInput
          type="text"
          name="email"
          value={email}
          onChange={handleEmailChange}
        ></StyledLoginInput>
        {emailMessage ? <div className="emailError">{emailMessage}</div> : null}

        <StyledTitle>
          Password <StyledForgotLink>Forgot password?</StyledForgotLink>
        </StyledTitle>
        <StyledLoginInput
          type="password"
          name="password"
          value={password}
          onChange={handlePasswordChange}
        ></StyledLoginInput>
        {passwordMessage ? (
          <div className="passwordError">{passwordMessage}</div>
        ) : null}
        <StyledLoginBtn type="submit" onClick={handleSubmit}>
          Log in
        </StyledLoginBtn>
      </StyledLoginContainer>
      <StyledSignup>
        Don’t have an account?
        <StyledSignupLink href="/login">Sign up</StyledSignupLink>
      </StyledSignup>
    </StyledLogin>
  );
};

export default login;
