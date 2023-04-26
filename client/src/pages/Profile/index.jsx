//component
import MemberInfo from "./MemberInfo/index";
import MemberQAs from "./MemberQAs/index";

//style
import StyledProfile from "./style";

/** 2023/04/18 - 유저 프로필  - by sinyaenok*/
const Profile = () => {
  return (
    <StyledProfile>
      <MemberInfo />
      <MemberQAs />
    </StyledProfile>
  );
};

export default Profile;
