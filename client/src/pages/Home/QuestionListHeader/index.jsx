import { useState } from "react";

import StyledQuestionHeader, {
  StyledInfo,
  StyledPageMove,
  StyledAskBtn,
  StyledSortContainer,
  StyledSortBtn,
  StyledBtnContainer,
} from "./style";

function QuestionListHeader({ totalQNum, setSort }) {
  const [clicked, setClicked] = useState("Newest");
  const token = localStorage.getItem("accessToken");

  const handleBtnClick = (e) => {
    setClicked(e.target.innerText);
    if (e.target.innerText === "Newest") {
      setSort("updatedAt");
    } else if (e.target.innerText === "Active") {
      setSort("questionId");
    }
  };

  return (
    <StyledQuestionHeader>
      <StyledInfo>
        All Questions
        <StyledPageMove to={token ? "/questions/ask" : "/login"}>
          <StyledAskBtn>Ask Question</StyledAskBtn>
        </StyledPageMove>
      </StyledInfo>
      <StyledBtnContainer>
        <div className="questions-num">{totalQNum} questions</div>
        <StyledSortContainer>
          <StyledSortBtn
            onClick={handleBtnClick}
            className={clicked === "Newest" ? "left-btn clicked" : "left-btn"}
          >
            Newest
          </StyledSortBtn>
          <StyledSortBtn
            onClick={handleBtnClick}
            className={clicked === "Active" ? "clicked" : ""}
          >
            Active
          </StyledSortBtn>
        </StyledSortContainer>
      </StyledBtnContainer>
    </StyledQuestionHeader>
  );
}

export default QuestionListHeader;
