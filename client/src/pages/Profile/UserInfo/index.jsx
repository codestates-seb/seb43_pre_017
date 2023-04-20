//style
import {
  StyledUserInfo,
  StyledImgBox,
  StyledInfoBox,
  StyledEditBtn,
} from "./style";

//icon
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCakeCandles, faClock } from "@fortawesome/free-solid-svg-icons";

const UserInfo = () => {
  return (
    <StyledUserInfo className="d-flex mb10">
      <StyledImgBox className="d-flex ai-center">
        <a href="/profile">
          <img
            width="128"
            height="128"
            src="https://images.unsplash.com/photo-1533738363-b7f9aef128ce?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1035&q=80"
            alt="고양이 유저"
          />
        </a>
      </StyledImgBox>
      <StyledInfoBox className="d-flex di-column ">
        <div id="user-name" className="d-flex ai-end mb10">
          고양이
        </div>
        <div id="user-info" className="d-flex fw">
          <FontAwesomeIcon icon={faCakeCandles} className="icon-cack" />
          <div>member for 변수 years,</div>
          <FontAwesomeIcon icon={faClock} className="icon-clock" />
          <div>Last seen this 변수</div>
        </div>
      </StyledInfoBox>
      {/* Todo : 수정버튼이 우측으로 촥 붙어야됨. */}
      <StyledEditBtn className="d-flex di-column fwn">
        <a href="/profile">
          <div className="editBtn">Edit Profile</div>
        </a>
      </StyledEditBtn>
    </StyledUserInfo>
  );
};

export default UserInfo;
