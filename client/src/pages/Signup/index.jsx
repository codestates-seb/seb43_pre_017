import axios from "axios";
import { useState } from "react";
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
import { useNavigate } from "react-router-dom";

/** 2023/04/21 - 회원가입 페이지 작성  - by JHH0906 */
const Signup = () => {
  const [email, setEmail] = useState("");
  const [emailMessage, setEmailMessage] = useState("");
  const [password, setPassword] = useState("");
  const [passwordMessage, setPasswordMessage] = useState("");
  const [name, setName] = useState("");
  const navigate = useNavigate();
  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };
  /** 2023/04/19 비밀번호 유효성 검사 이벤트  -by JHH0906 */
  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };
  const handleNameChange = (e) => {
    setName(e.target.value);
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
    }
    const reqbody = JSON.stringify({
      name: name,
      username: email,
      password: password,
    });
    axios
      .post(
        "http://ec2-13-125-208-253.ap-northeast-2.compute.amazonaws.com:8080//api/signup",
        reqbody,
        {
          headers: {
            "Content-Type": "application/json",
          },
          withCredentials: "true",
        },
      )
      .then((res) => {
        alert("성공");
        navigate("/login");
        console.log(res);
      })
      .catch((err) => {
        console.log(err.data);
        alert("실패");
        setEmail("");
        setPassword("");
        setName("");
      });
  };
  return (
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
          <StyledSignupInput
            type="text"
            name="name"
            value={name}
            onChange={handleNameChange}
          ></StyledSignupInput>
          <StyledTitle>Email</StyledTitle>
          <StyledSignupInput
            type="text"
            name="email"
            value={email}
            onChange={handleEmailChange}
          ></StyledSignupInput>
          {emailMessage ? (
            <div className="emailError">{emailMessage}</div>
          ) : null}
          <StyledTitle>password</StyledTitle>
          <StyledSignupInput
            ype="password"
            name="password"
            value={password}
            onChange={handlePasswordChange}
          ></StyledSignupInput>
          {passwordMessage ? (
            <div className="passwordError">{passwordMessage}</div>
          ) : null}
          <StyledValid>
            Passwords must contain at least eight characters, including at least
            1 letter and 1 number.
          </StyledValid>
        </StyledInputContainer>
        <StyledSignupBtn type="submit" onClick={handleSubmit}>
          Sign up
        </StyledSignupBtn>
      </StyledSignupContainer>
      <StyledLogin>
        Already have an account?
        <StyledSignupLink href="/login"> Log in</StyledSignupLink>
      </StyledLogin>
    </StyledSignup>
  );
};

export default Signup;
