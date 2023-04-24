//component
import UserInfo from "./UserInfo/index";
import UserQAs from "./UserQAs/index";

//style
import StyledProfile from "./style";

/** 2023/04/18 - 유저 프로필  - by sinyaenok*/
const Profile = () => {
  return (
    <StyledProfile>
      <UserInfo />
      <UserQAs />
    </StyledProfile>
  );
};

export default Profile;
