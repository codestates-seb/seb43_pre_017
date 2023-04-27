import { useDispatch } from "react-redux";

// redux
import { evaluationThunkService } from "../../../store/thunks/evaluation";

// component
import Icon from "../../common/Icon";

// style
import StyledObjectSideButtons from "./style";

/** 2023/04/19 - 좌측 사이드 버튼들 - by 1-blue */
const ObjectSideButtons = ({ type, evaluationScore, articleId, answerId }) => {
  const dispatch = useDispatch();

  /** 2023/04/21 - 평가 좋아요 - by 1-blue */
  const onClickLike = () => {
    if (type === "article") {
      dispatch(
        evaluationThunkService.uploadEvaluationOfArticleThunk({
          articleId,
          evaluationScore: "+1",
        }),
      );
    }
    if (type === "answer") {
      dispatch(
        evaluationThunkService.uploadEvaluationOfAnswerThunk({
          answerId,
          evaluationScore: "+1",
        }),
      );
    }
  };
  /** 2023/04/21 - 평가 싫어요 - by 1-blue */
  const onClickHate = () => {
    if (type === "article") {
      dispatch(
        evaluationThunkService.uploadEvaluationOfArticleThunk({
          articleId,
          evaluationScore: "-1",
        }),
      );
    }
    if (type === "answer") {
      dispatch(
        evaluationThunkService.uploadEvaluationOfAnswerThunk({
          answerId,
          evaluationScore: "-1",
        }),
      );
    }
  };

  return (
    <StyledObjectSideButtons>
      <button type="button" onClick={onClickLike}>
        <svg width="36" height="36" viewBox="0 0 36 36">
          <path d="M2 25h32L18 9 2 25Z" />
        </svg>
      </button>
      <span>{evaluationScore}</span>
      <button type="button" onClick={onClickHate}>
        <svg width="36" height="36" viewBox="0 0 36 36">
          <path d="M2 11h32L18 27 2 11Z" />
        </svg>
      </button>
      <Icon shape="bookmark" />
      <button type="button">
        <svg width="19" height="18" viewBox="0 0 19 18">
          <path d="M3 9a8 8 0 1 1 3.73 6.77L8.2 14.3A6 6 0 1 0 5 9l3.01-.01-4 4-4-4h3L3 9Zm7-4h1.01L11 9.36l3.22 2.1-.6.93L10 10V5Z"></path>
        </svg>
      </button>
    </StyledObjectSideButtons>
  );
};

export default ObjectSideButtons;
