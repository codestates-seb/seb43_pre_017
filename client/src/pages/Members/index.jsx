import { StyledMembers } from "./style";
import { StyledSearchFrom } from "../../Layout/Header/style";

//component
import SortTeb from "./SortTeb";
import MemberBrowser from "./MemberBrowser";

const Members = () => {
  return (
    <StyledMembers>
      <h1>Members</h1>
      <StyledSearchFrom>?</StyledSearchFrom>
      <SortTeb></SortTeb>
      <MemberBrowser></MemberBrowser>
    </StyledMembers>
  );
};

export default Members;
