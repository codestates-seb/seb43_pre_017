import { useCallback, useState } from "react";
import { useDispatch } from "react-redux";

// redux
import {
  answerThunkService,
  commentThunkService,
  commentsThunkService,
} from "../../store/thunks";

// component
import ObjectSideButtons from "../Object/ObjectSideButtons";
import ObjectContent from "../Object/ObjectContent";
import ObjectFooter from "../Object/ObjectFooter";
import ObjectComment from "../Object/ObjectComment";
import ObjectCommentForm from "../Object/ObjectCommentForm";

// style
import StyledAnswer from "./style";

/** 2023/04/21 - answer 컴포넌트 - by 1-blue */
const Answer = ({
  evaluationScore,
  articleId,
  answerId,
  content,
  member,
  comments,
  count,
  updatedAt,
}) => {
  const dispatch = useDispatch();

  /** 2023/04/24 - 현재 댓글 페이지 - by 1-blue */
  const [commentPage, setCommentPage] = useState(1);

  /** 2023/04/21 - 댓글 수정 - by 1-blue */
  const onUpdateComment = useCallback(({ commentId, content }) => {
    dispatch(
      commentThunkService.updateCommentOfAnswerThunk({
        answerId,
        commentId,
        content,
      }),
    );
  }, []);

  /** 2023/04/21 - 댓글 제거 - by 1-blue */
  const onDeleteComment = useCallback(
    ({ commentId }) => {
      dispatch(
        commentThunkService.deleteCommentOfAnswerThunk({
          answerId,
          commentId,
        }),
      );
    },
    [answerId],
  );

  /** 2023/04/21 - 댓글들 더 불러오기 - by 1-blue */
  const fetchComment = () => {
    dispatch(
      commentsThunkService.fetchCommentsOfAnswerThunk({
        answerId,
        page: commentPage,
      }),
    );

    setCommentPage((prev) => prev + 1);
  };

  /** 2023/04/21 - answer 제거 - by 1-blue */
  const onDeleteAnswer = useCallback(() => {
    dispatch(answerThunkService.deleteAnswerThunk({ articleId, answerId }));
  }, [articleId, answerId]);

  /** 2023/04/21 - 더 가져올 수 있는 댓글의 개수 - by 1-blue */
  const commentCount = count.comments - comments.length;

  return (
    // like / content / share,edit / author / comment
    <StyledAnswer>
      {/* like */}
      <ObjectSideButtons
        type="answer"
        evaluationScore={evaluationScore}
        articleId={articleId}
        answerId={answerId}
      />

      {/* content / share,edit / author / comment */}
      <section className="article-content-wrapper">
        {/* content */}
        <ObjectContent content={content} />

        {/* share, edit / author */}
        {/* FIXME: 답변 수정 링크는 무엇...? */}
        <ObjectFooter
          articleId={articleId}
          member={member}
          onDeleteObject={onDeleteAnswer}
          updatedAt={updatedAt}
        />

        {/* comment */}
        <ul className="comments-wrapper">
          {comments.map((comment) => (
            <ObjectComment
              key={comment.id}
              commentId={comment.id}
              member={comment.member}
              content={comment.content}
              updatedAt={comment.updatedAt}
              onUpdateComment={onUpdateComment}
              onDeleteComment={onDeleteComment}
            />
          ))}
        </ul>

        {/* 댓글 더 불러오기 */}
        {commentCount > 0 && (
          <button type="button" onClick={fetchComment}>
            댓글 {commentCount}개 더 불러오기
          </button>
        )}

        {/* comment-form */}
        <ObjectCommentForm
          type="answer"
          articleId={articleId}
          answerId={answerId}
        />
      </section>
    </StyledAnswer>
  );
};
export default Answer;
