//style
import {
  StyledUserInfo,
  StyledImgBox,
  StyledInfoBox,
  StyledEditBtn,
} from "./style";

//data
import MockupData from "./Data/data";

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
            src={MockupData.user[0].img}
            alt="고양이 유저"
          />
        </a>
      </StyledImgBox>
      <StyledInfoBox className="d-flex di-column ">
        <div id="user-name" className="d-flex ai-end mb10">
          {MockupData.user[0].name}
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
