import { useState } from "react";

import { useStore } from "../../../store/reducers";

import StyledQuestionHeader, {
  StyledInfo,
  StyledPageMove,
  StyledAskBtn,
  StyledSortContainer,
  StyledSortBtn,
  StyledBtnContainer,
} from "./style";

function QuestionListHeader({ totalQNum, setSort }) {
  const {
    Userdata: { email },
  } = useStore();
  const [clicked, setClicked] = useState("Newest");

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
        <StyledPageMove to={email ? "/questions/ask" : "/login"}>
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
