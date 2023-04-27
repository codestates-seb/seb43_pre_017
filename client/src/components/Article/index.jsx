import { useCallback } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";

// redux
import {
  articleThunkService,
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
import StyledArticle from "./style";

/** 2023/04/21 - article 컴포넌트 - by 1-blue */
const Article = ({
  evaluationScore,
  articleId,
  content,
  member,
  comments,
  count,
  updatedAt,
}) => {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  /** 2023/04/21 - 댓글 수정 - by 1-blue */
  const onUpdateComment = useCallback(({ commentId, content }) => {
    dispatch(
      commentThunkService.updateCommentOfArticleThunk({
        articleId,
        commentId,
        content,
      }),
    );
  }, []);

  /** 2023/04/21 - 댓글 제거 - by 1-blue */
  const onDeleteComment = useCallback(({ commentId }) => {
    dispatch(
      commentThunkService.deleteCommentOfArticleThunk({
        articleId,
        commentId,
      }),
    );
  }, []);

  /** 2023/04/21 - 댓글들 더 불러오기 - by 1-blue */
  const fetchComment = () => {
    dispatch(commentsThunkService.fetchCommentsOfArticleThunk({ articleId }));
  };

  /** 2023/04/21 - article 제거 - by 1-blue */
  const onDeleteArticle = useCallback(() => {
    dispatch(articleThunkService.deleteArticleThunk({ articleId }));

    navigate("/");
  }, []);

  /** 2023/04/21 - 더 가져올 수 있는 댓글의 개수 - by 1-blue */
  const commentCount = count.comment - comments.length;

  return (
    // like / content / share,edit / author / comment
    <StyledArticle>
      {/* like */}
      <ObjectSideButtons
        type="article"
        evaluationScore={evaluationScore}
        articleId={articleId}
      />

      {/* content / share,edit / author / comment */}
      <section className="article-content-wrapper">
        {/* content */}
        <ObjectContent content={content} />

        {/* share, edit / author */}
        {/* FIXME: 답변 수정은 어디서...? */}
        <ObjectFooter
          articleId={articleId}
          member={member}
          onDeleteObject={onDeleteArticle}
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
        <ObjectCommentForm type="article" articleId={articleId} />
      </section>
    </StyledArticle>
  );
};
export default Article;
