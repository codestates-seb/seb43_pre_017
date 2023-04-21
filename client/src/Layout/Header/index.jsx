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

/** 2023/04/16 - 헤더를 감싸는 컴포넌트 - by sinyaenok */
const Header = () => {
  return (
    <StyledHeader>
      <StyledContaier className="container">
        {/* 로고 */}
        <StyledLogo>
          {/* 큰 로고 */}
          <img
            src="https://stackoverflow.design/assets/img/logos/so/logo-stackoverflow.svg"
            alt="스택오버플로우 로고"
            className="stack-overflow"
          />
          {/* 작은 로고 */}
          <img
            src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Stack_Overflow_icon.svg/1024px-Stack_Overflow_icon.svg.png?20190716190036"
            alt="스택오버플로우 로고"
            className="min-stack-overflow"
          />
        </StyledLogo>

        {/* 서치창 */}
        <StyledSearchFrom>
          <div className="search-bar">
            <FontAwesomeIcon icon={faMagnifyingGlass} className="search-icon" />
            <input
              className="search-bar-input"
              type="text"
              placeholder="Search..."
            />
          </div>
        </StyledSearchFrom>

        <Nav />
      </StyledContaier>
    </StyledHeader>
  );
};

export default Header;
