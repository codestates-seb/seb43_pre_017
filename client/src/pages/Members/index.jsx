import { StyledMembers } from "./style";
import { StyledSearchFrom } from "../../Layout/Header/style";

//component
import SortTeb from "./SortTeb";
import MemberBrowser from "./MemberBrowser";

/** 20230421 - 유저 목록 페이지 - by sinyaenok */
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
