// component
import NavBar from "../NavBar";

// style
import StyledHeader from "./style";

/** 2023/04/12 - 헤더를 감싸는 컴포넌트 - by 1-blue */
const Header = () => {
  return (
    <StyledHeader>
      <NavBar />
    </StyledHeader>
  );
};

export default Header;
