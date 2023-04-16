// style
import StyledFoorter, { StyledLogo } from "./style";

/** 2023/04/12 - 푸터를 감싸는 컴포넌트 - by 1-blue */
const Footer = () => (
  <StyledFoorter>
    <StyledLogo>
      <img
        src="https://cdn.sstatic.net/Sites/stackoverflow/Img/apple-touch-icon@2.png?v=73d79a89bded"
        alt="logo"
      />
    </StyledLogo>
  </StyledFoorter>
);

export default Footer;
