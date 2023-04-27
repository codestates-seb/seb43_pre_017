import StyledList, {
  StyledCounts,
  StyledQuestion,
  StyledQuestionTitle,
  StyledContent,
  StyledInfoContainer,
  StyledPostInfo,
  StyledUserPic,
  StyledUser,
} from "./style";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUser } from "@fortawesome/free-solid-svg-icons";
import TimeDiff from "../TimeDiff";

function QuestionsList({
  id,
  answerCount,
  content,
  votes,
  createdAt,
  title,
  user,
  viewCount,
}) {
  return (
    <StyledList>
      <StyledCounts>
        <div className="vote">
          <span className="count">{votes} </span>
          votes
        </div>
        <div>
          <span className="count">{answerCount} </span>
          answers
        </div>
        <div>
          <span className="count">{viewCount} </span>
          views
        </div>
      </StyledCounts>
      <StyledQuestion>
        <StyledQuestionTitle to={`/questions/${id}`}>
          {title}
        </StyledQuestionTitle>
        <StyledContent>{content}</StyledContent>
        <StyledInfoContainer>
          <StyledPostInfo>
            <StyledUserPic>
              <FontAwesomeIcon icon={faUser} />
            </StyledUserPic>
            <StyledUser to={`/api/members/${user.id}`}>{user.name}</StyledUser>
            <TimeDiff createAt={createdAt} target="asked" />
          </StyledPostInfo>
        </StyledInfoContainer>
      </StyledQuestion>
    </StyledList>
  );
}

export default QuestionsList;
