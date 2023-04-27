//style
import {
  StyledMemberInfo,
  StyledImgBox,
  StyledInfoBox,
  StyledEditBtn,
} from "./style";

// url 연결을 위한 useParams
import { Link, useParams } from "react-router-dom";

//data
import dummy from "../../Members/Data/data.json";

//icon
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCakeCandles } from "@fortawesome/free-solid-svg-icons";

/** 20230420 - 멤버 상세정보 페이지(이름, 날짜, 수정버튼) - by sinyaenok*/
const MemberInfo = () => {
  const { id } = useParams();
  const member = dummy.members.find((member) => member.id === parseInt(id));

  return (
    <StyledMemberInfo className="d-flex mb10">
      <StyledImgBox className="d-flex ai-center">
        <Link to="/profile">
          <img width="128" height="128" src={member.img} alt="유저 정보" />
        </Link>
      </StyledImgBox>
      <StyledInfoBox className="d-flex di-column ">
        <div id="member-name" className="d-flex ai-end mb10">
          {member.name}
        </div>
        <div id="member-info" className="d-flex fw">
          <FontAwesomeIcon icon={faCakeCandles} className="icon-cack" />
          <div>member created for {member.createdAt}</div>
        </div>
      </StyledInfoBox>

      <StyledEditBtn className="d-flex">
        <Link to="/profile">
          <div className="editBtn">Edit Profile</div>
        </Link>
      </StyledEditBtn>
    </StyledMemberInfo>
  );
};

export default MemberInfo;
