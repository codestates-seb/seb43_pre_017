import { StyledMemberBrowser, StyledMember } from "./style";
import { Link } from "react-router-dom";

/** 20230424 - 멤버들을 감싸는 컴포넌트 - by sinyaenok*/
const MemberBrowser = (filteredMembers) => {
  return (
    <StyledMemberBrowser>
      {filteredMembers.filteredMembers.map((member) => (
        <StyledMember key={member.id} id="item-container">
          {/* 멤버 이미지 */}
          <div className="item-img d-flex ai-center js-center">
            <Link
              to={`/members/${member.name}/${member.id}`}
              id="item-img-link"
              className="d-flex ai-center"
            >
              <img src={member.img} alt="memerimage" width={48} height={48} />
            </Link>
          </div>

          {/* 이름, 생성일 */}
          <div className="item-box d-flex f1">
            <div className="d-flex">
              <Link
                to={`/members/${member.name}/${member.id}`}
                id="item-name-link"
              >
                {member.name}
              </Link>
            </div>
            <div className="item-createdAt d-flex">{member.createdAt}</div>
          </div>
        </StyledMember>
      ))}
    </StyledMemberBrowser>
  );
};

export default MemberBrowser;
