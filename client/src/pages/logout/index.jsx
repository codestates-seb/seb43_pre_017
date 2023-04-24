/* eslint-disable react/no-unescaped-entities */
import StyledLogoutContainer, {
  StyledLogoutForm,
  StyledLogoutText,
  StyledLogoutContent,
  StyledLogoutBox,
  StyledCheckBoxText,
  StyledCheckBoxForm,
  StyledBtnForm,
  StyledLogOutBtn,
  StyledLogCancelBtn,
  StyledBottomText,
} from "./style";
import LogoutIcon from "./Icon";
import Checkbox from "./checkbox";

/** 2023/04/23 로그아웃 페이지 -by JHH0906 */
const Logout = () => {
  return (
    <StyledLogoutContainer>
      <StyledLogoutForm>
        <StyledLogoutText>
          Clicking “Log out” will log you out of the following domains on this
          device:
        </StyledLogoutText>
        <StyledLogoutContent>
          <StyledLogoutBox>
            <LogoutIcon />
            <StyledCheckBoxForm>
              <Checkbox />
              <StyledCheckBoxText>Log out on all devices</StyledCheckBoxText>
            </StyledCheckBoxForm>
            <StyledBtnForm>
              <StyledLogOutBtn>Log Out</StyledLogOutBtn>
              <StyledLogCancelBtn>Cancel</StyledLogCancelBtn>
            </StyledBtnForm>
            <StyledBottomText>
              If you’re on a shared computer, remember to log out of your Open
              ID provider (Facebook, Google, Stack Exchange, etc.) as well.
            </StyledBottomText>
          </StyledLogoutBox>
        </StyledLogoutContent>
      </StyledLogoutForm>
    </StyledLogoutContainer>
  );
};

export default Logout;
