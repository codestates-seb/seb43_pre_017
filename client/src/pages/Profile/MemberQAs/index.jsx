import {
  StyledMemberQAs,
  StyledAnswers,
  StyledQuestions,
  StyledAnswersTitle,
  StyledQuestionsTitle,
  StyledAnswersForm,
  StyledQuestionsForm,
} from "./style";

import { Link } from "react-router-dom";

const MemberQAs = () => {
  return (
    <StyledMemberQAs>
      <Answers />
      <Questions />
    </StyledMemberQAs>
  );
};

const Answers = () => {
  return (
    <StyledAnswers>
      <StyledAnswersTitle>
        <div className="answers-title mb10">Answers</div>
      </StyledAnswersTitle>
      <StyledAnswersForm className="mb10">
        <div>
          You have not
          <Link to="/questions/1" className="answers-link">
            answered
          </Link>
          any questions.
        </div>
      </StyledAnswersForm>
    </StyledAnswers>
  );
};

const Questions = () => {
  return (
    <StyledQuestions>
      <StyledQuestionsTitle>
        <div className="questions-title mb10">Questions</div>
      </StyledQuestionsTitle>
      <StyledQuestionsForm className="mb10">
        <div>
          You have not{" "}
          <Link to="/questions/1" className="questions-link">
            asked
          </Link>{" "}
          any questions.
        </div>
      </StyledQuestionsForm>
    </StyledQuestions>
  );
};
export default MemberQAs;
