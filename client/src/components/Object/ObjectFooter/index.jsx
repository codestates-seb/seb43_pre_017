import { Link } from "react-router-dom";
import { toast } from "react-toastify";

// util
import { dateOrTimeFormat } from "../../../utils";

// style
import StyledObjectFooter from "./style";

/** 2023/04/19 - 하단 버튼들 - by 1-blue */
const ObjectFooter = ({ articleId, member, onDeleteObject, updatedAt }) => (
  <StyledObjectFooter>
    {/* share, edit */}
    <div className="buttons">
      <button
        type="button"
        onClick={() => {
          navigator.clipboard.writeText(window.location.href);
          toast.success("링크가 복사되었습니다.");
        }}
      >
        Share
      </button>
      <Link to={`/question/ask/${articleId}`}>Edit</Link>
      <button type="button" onClick={onDeleteObject}>
        Delete
      </button>
    </div>

    {/* author */}
    <div className="author">
      <Link to={`/users/${member.id}`}>
        <div className="avatar" />
      </Link>
      <div>
        <Link to={`/users/${member.id}`}>{member.name}</Link>
        <time>{dateOrTimeFormat(updatedAt)}</time>
      </div>
    </div>
  </StyledObjectFooter>
);

export default ObjectFooter;
