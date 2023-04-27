import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

// util
import { dateOrTimeFormat } from "../../../utils";

// hook
import useResizeHeight from "../../../hooks/useResizeHeight";

// style
import StyledObjectComment from "./style";

/** 2023/04/20 - 댓글 컴포넌트 - by 1-blue */
const ObjectComment = ({
  commentId,
  content,
  member,
  updatedAt,
  onUpdateComment,
  onDeleteComment,
}) => {
  /** 2023/04/20 - textarea 자동 높이 조절 - by 1-blue */
  const [textRef, handleResizeHeight] = useResizeHeight();

  /** 2023/04/21 - textarea 리사이징 - by 1-blue */
  useEffect(handleResizeHeight, []);

  /** 2023/04/20 - 댓글 내용 - by 1-blue */
  const [currentContent, setCurrentContent] = useState(content);

  /** 2023/04/20 - 댓글 수정 여부 - by 1-blue */
  const [disabled, setDisabled] = useState(true);

  /** 2023/04/20 - 댓글 수정 버튼 클릭 - by 1-blue */
  const onClickUpdate = () => {
    if (disabled) return setDisabled(false);
    setDisabled(true);

    onUpdateComment({ commentId, content: currentContent });
  };

  /** 2023/04/21 - 댓글 수정 취소 - by 1-blue */
  const onClickCancel = () => {
    setDisabled(true);
    setCurrentContent(content);
    setTimeout(handleResizeHeight, 0);
  };

  return (
    <StyledObjectComment>
      <textarea
        ref={textRef}
        value={currentContent}
        onChange={(e) => {
          setCurrentContent(e.target.value);
          handleResizeHeight();
        }}
        disabled={disabled}
        rows={1}
      />

      <div>
        <Link to={`/users/${member.id}`}>{member.name}</Link>

        <time>{dateOrTimeFormat(updatedAt, "YYYY-MM-DD-hh-mm-ss")}</time>

        {/* FIXME: 유저가 있다면 */}
        <button type="button" onClick={onClickUpdate}>
          수정
        </button>
        {disabled ? (
          <button type="button" onClick={() => onDeleteComment({ commentId })}>
            삭제
          </button>
        ) : (
          <button type="button" onClick={onClickCancel}>
            취소
          </button>
        )}
      </div>
    </StyledObjectComment>
  );
};

export default ObjectComment;
