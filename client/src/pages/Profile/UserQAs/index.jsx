import { StyledUserQAs } from "./style";

const UserQAs = () => {
  return (
    <StyledUserQAs className="d-grid">
      {/* Answers */}
      <div id="answer-box" className="d-grid p16 ">
        <div id="answer-title" className=" d-flex ai-end pb10">
          Answers
        </div>
        <div id="answers" className=" d-flex ai-center js-center">
          You have not <a href="/profile">answered</a> any questions.
        </div>
      </div>
      {/* Questions */}
      <div id="question-box" className="d-grid p16">
        <div id="question-title" className=" d-flex ai-end pb10">
          Questions
        </div>
        <div id="questions" className=" d-flex ai-center js-center">
          You have not <a href="/profile">asked</a> any questions.
        </div>
      </div>
    </StyledUserQAs>
  );
};

export default UserQAs;
