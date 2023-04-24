import { Link } from "react-router-dom";
// Nav
import Nav from "../NavBar";

// component
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";

// style
import {
  StyledHeader,
  StyledContaier,
  StyledLogo,
  StyledSearchFrom,
} from "./style";

// image
import LogoImg from "./images/Logo-stackoverflow.png";
import MinLogoImg from "./images/MinLogo-stackoverflow.png";

/** 2023/04/16 - 헤더를 감싸는 컴포넌트 - by sinyaenok */
const Header = () => {
  return (
    <StyledHeader>
      <StyledContaier>
        <Logo />
        <Searchbar />
        <Nav />
      </StyledContaier>
    </StyledHeader>
  );
};

// 로고
const Logo = () => {
  return (
    <StyledLogo>
      {/* 큰 로고 */}
      <Link to="/">
        <img src={LogoImg} alt="스택오버플로우 로고" className="Logo-img" />
      </Link>

      {/* 작은 로고 */}
      <Link to="/">
        <img
          src={MinLogoImg}
          alt="스택오버플로우 로고"
          className="MinLogo-img"
        />
      </Link>
    </StyledLogo>
  );
};

// 서치창
export const Searchbar = () => {
  return (
    <StyledSearchFrom>
      <div className="search-bar">
        <FontAwesomeIcon icon={faMagnifyingGlass} className="search-icon" />
        <input type="text" placeholder="Search..." />
      </div>
    </StyledSearchFrom>
  );
};
export default Header;
