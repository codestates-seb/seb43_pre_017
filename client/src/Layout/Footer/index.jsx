// style
import StyledFoorter, {
  StyledLogo,
  StyledStackoverflow,
  StyledTitle,
  StyledUl,
  StyledLi,
} from "./style";

/** 2023/04/12 - 푸터를 감싸는 컴포넌트 - by 1-blue */
const Footer = () => (
  <StyledFoorter>
    <StyledLogo>
      <img
        src="https://cdn.sstatic.net/Sites/stackoverflow/Img/apple-touch-icon@2.png?v=73d79a89bded"
        alt="logo"
      />
    </StyledLogo>
    <StyledStackoverflow>
      <StyledTitle>STACK OVERFLOW</StyledTitle>
      <StyledUl>
        <StyledLi>Questions</StyledLi>
        <StyledLi>Help</StyledLi>
      </StyledUl>
    </StyledStackoverflow>
  </StyledFoorter>
);

export default Footer;
