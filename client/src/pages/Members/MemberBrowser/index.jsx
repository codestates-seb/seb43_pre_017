import dummy from "../Data/data.json";
import { StyledMemberBrowser, StyledMember } from "./style";

/** 20230424 - member list 컴포넌트 - by sinyaenok*/
const MemberBrowser = () => {
  return (
    <StyledMemberBrowser>
      {dummy.members.map((member) => (
        <StyledMember key={member.id} id="item-container">
          {/* 이미지 */}
          <div className="item-img d-flex ai-center js-center">
            <a href={member.id} id="item-img-link" className="d-flex ai-center">
              <img src={member.img} alt="memerimage" width={48} height={48} />
            </a>
          </div>

          {/* 이름, 질문, 답변수 */}
          <div className="item-box d-flex f1">
            <div className="d-flex">
              <a href="/members" id="item-name-link">
                {member.name}
              </a>
            </div>
            <div className="item-question d-flex">q : {member.question}</div>
            <div className="item-answer d-flex">a : {member.answer}</div>
          </div>
        </StyledMember>
      ))}
    </StyledMemberBrowser>
  );
};

export default MemberBrowser;
