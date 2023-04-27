import { useDispatch } from "react-redux";
import { toast } from "react-toastify";

// redux
import { commentThunkService } from "../../../store/thunks";

// hook
import useResizeHeight from "../../../hooks/useResizeHeight";

// style
import StyledObjectCommentForm from "./style";

/** 2023/04/19 - 댓글 폼 컴포넌트 - by 1-blue */
const ObjectCommentForm = ({ type, articleId, answerId }) => {
  const dispatch = useDispatch();

  const [textRef, handleResizeHeight] = useResizeHeight();

  /** 2023/04/20 - 댓글 생성 - by 1-blue */
  const onCreateComment = (e) => {
    e.preventDefault();

    const content = textRef.current.value.trim();

    if (!content) return toast.error("댓글을 입력해주세요!");

    // article의 댓글
    if (type === "article") {
      dispatch(
        commentThunkService.createCommentOfArticleThunk({ articleId, content }),
      );
    }
    // answer의 댓글
    if (type === "answer") {
      dispatch(
        commentThunkService.createCommentOfAnswerThunk({ answerId, content }),
      );
    }
  };

  return (
    <StyledObjectCommentForm onSubmit={onCreateComment}>
      <textarea rows={1} ref={textRef} onChange={handleResizeHeight} />
      <button type="submit">댓글 추가</button>
    </StyledObjectCommentForm>
  );
};

export default ObjectCommentForm;
