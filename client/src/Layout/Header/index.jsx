// component
import NavBar from "../NavBar";

// style
import { StyledHeader, StyledLogo, StyledSearch } from "./style";

/** 2023/04/16 - 헤더를 감싸는 컴포넌트 - by sinyaenok */
const Header = () => {
  return (
    <StyledHeader>
      <StyledLogo>
        <img
          src="https://stackoverflow.design/assets/img/logos/so/logo-stackoverflow.svg"
          alt="stackoverflow"
        />
      </StyledLogo>
      <form>
        <StyledSearch type="text" placeholder="Search..." />
      </form>
      <NavBar />
    </StyledHeader>
  );
};

export default Header;
