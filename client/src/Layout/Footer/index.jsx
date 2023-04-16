// style
import StyledFoorter, {
  StyledLogo,
  StyledStackoverflow,
  StyledTitle,
  StyledUl,
  StyledLi,
  StyledProducts,
  StyledCompnay,
  StyledNetwork,
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
    <StyledProducts>
      <StyledTitle>PRODUCTS</StyledTitle>
      <StyledUl>
        <StyledLi>Teams</StyledLi>
        <StyledLi>Advertising</StyledLi>
        <StyledLi>Collectives</StyledLi>
        <StyledLi>Talent</StyledLi>
      </StyledUl>
    </StyledProducts>
    <StyledCompnay>
      <StyledTitle>COMPANY</StyledTitle>
      <StyledUl>
        <StyledLi>About</StyledLi>
        <StyledLi>Press</StyledLi>
        <StyledLi>Work Here</StyledLi>
        <StyledLi>Legal</StyledLi>
        <StyledLi>Privacy Policy</StyledLi>
        <StyledLi>Terms of Service</StyledLi>
        <StyledLi>Contact US</StyledLi>
        <StyledLi>Cookie Settings</StyledLi>
        <StyledLi>Cookie Policy</StyledLi>
      </StyledUl>
    </StyledCompnay>
    <StyledNetwork>
      <StyledTitle>STACK EXCHANGE NETWORK</StyledTitle>
      <StyledUl>
        <StyledLi>Technology</StyledLi>
        <StyledLi>Culture & recreation</StyledLi>
        <StyledLi>Life & arts</StyledLi>
        <StyledLi>Science</StyledLi>
        <StyledLi>Professional</StyledLi>
        <StyledLi>Business</StyledLi>
        <StyledLi></StyledLi>
        <StyledLi>API</StyledLi>
        <StyledLi>Data</StyledLi>
      </StyledUl>
    </StyledNetwork>
  </StyledFoorter>
);

export default Footer;
