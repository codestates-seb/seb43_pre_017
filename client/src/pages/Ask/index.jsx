import { useState } from "react";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

// api
import { articleApiService } from "../../store/apis";

// component
import Wysiwyg from "../../components/common/Wysiwyg";

// hook
import useWysiwyg from "../../hooks/useWysiwyg";

// style
import StyledAsk from "./style";

/** 2023/04/24 - 질문 생성 페이지 - by 1-blue */
const Ask = () => {
  const navigate = useNavigate();

  /** 2023/04/24 - title - 1-blue */
  const [title, setTitle] = useState("");

  /** 2023/04/24 - 위지윅 훅 - by 1-blue */
  const { wysiwygRef, getContents } = useWysiwyg();

  /** 2023/04/24 - 게시글 생성 - by 1-blue */
  const onSubmitPost = async (e) => {
    e.preventDefault();

    const content = getContents().trim();

    if (!title.trim()) return toast.error("제목을 입력해주세요!");
    if (content.length <= 11) return toast.error("내용을 입력해주세요!");

    try {
      const { data } = await articleApiService.apiUploadArticle({
        title,
        content,
      });

      navigate("/");

      toast.success(data.message);
    } catch (error) {
      console.error("error >> ", error);
    }
  };

  return (
    <StyledAsk onSubmit={onSubmitPost}>
      {/* 상단 */}
      <section className="title-wraaper">
        <h1>Ask a public question</h1>
      </section>

      {/* 설명 박스 */}
      <section className="description-wrapper">
        <h2>Writing a good question</h2>
        <p>
          You’re ready to ask a programming-related question and this form will
          help guide you through the process. Looking to ask a non-programming
          question? See the topics here to find a relevant site.
        </p>

        <div>
          <h5>Steps</h5>
          <ul>
            <li>Summarize your problem in a one-line title.</li>
            <li>Describe your problem in more detail.</li>
            <li>Describe what you tried and what you expected to happen.</li>
            <li>
              Add “tags” which help surface your question to members of the
              community.
            </li>
            <li>Review your question and post it to the site.</li>
          </ul>
        </div>
      </section>

      {/* title-input */}
      <section className="title-input-wrapper">
        <div className="title">Title</div>
        <div className="description">
          Be specific and imagine you’re asking a question to another person.
        </div>
        <input
          type="text"
          placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
      </section>

      {/* description-input */}
      <section className="description-input-wrapper">
        <div className="title">What are the details of your problem?</div>
        <div className="description">
          Introduce the problem and expand on what you put in the title. Minimum
          20 characters.
        </div>

        <Wysiwyg height="60vh" wysiwygRef={wysiwygRef} />
      </section>

      {/* submit */}
      <button type="submit">Post Your Answer</button>
    </StyledAsk>
  );
};

export default Ask;
