import { useEffect } from "react";
import { Link, useParams, useSearchParams } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { toast } from "react-toastify";

// rtk
import {
  answersThunkService,
  answerThunkService,
  articleThunkService,
} from "../../store/thunks";

// util
import { dateOrTimeFormat } from "../../utils";

// hook
import useWysiwyg from "../../hooks/useWysiwyg";

// component
import Article from "../../components/Article";
import Answer from "../../components/Answer";
import Wysiwyg from "../../components/common/Wysiwyg";
import Loading from "../../components/common/Loading";

// style
import StyledArticlePage from "./style";

/** 2023/04/18 - 상세 article 페이지 컴포넌트 - by 1-blue */
const ArticlePage = () => {
  const [searchParams] = useSearchParams();
  const { questionId } = useParams();

  /** 2023/04/19 - article 상세 데이터 요청 및 가져오기 - by 1-blue */
  const dispatch = useDispatch();
  const { article } = useSelector((state) => state.article);

  /** 2023/04/19 - article 상세 데이터 요청 - by 1-blue */
  useEffect(() => {
    dispatch(articleThunkService.fetchArticleThunk({ articleId: questionId }));

    setTimeout(() => {
      dispatch(
        answersThunkService.fetchAnswersThunk({ articleId: questionId }),
      );
    }, 200);
  }, [dispatch, questionId, searchParams]);

  /** 2023/04/20 - 위지윅 훅 - by 1-blue */
  const { wysiwygRef, getContents } = useWysiwyg();

  /** 2023/04/20 - 답변 제출 폼 - by 1-blue */
  const onSubmitAnswer = (e) => {
    e.preventDefault();

    const content = getContents();

    if (content.trim().length <= 11) return toast.error("내용을 입력해주세요!");

    // answer 생성
    dispatch(
      answerThunkService.createAnswerThunk({ articleId: questionId, content }),
    );
  };

  if (!article) return <Loading />;

  return (
    <StyledArticlePage>
      {/* title / ask btn */}
      <section className="top-wrapper">
        <h1>{article.article.title}</h1>

        <Link to="/questions/ask">Ask Question</Link>
      </section>

      {/* time */}
      <section className="sub-top-wrapper">
        <time>
          작성일{" "}
          {dateOrTimeFormat(article.article.createdAt, "YYYY-MM-DD-hh-mm-ss")}
        </time>
        <time>
          수정{" "}
          {dateOrTimeFormat(article.article.updatedAt, "YYYY-MM-DD-hh-mm-ss")}
        </time>
        <span>조회수 {5}</span>
      </section>

      <hr />

      <Article
        type="article"
        evaluationScore={article.article.evaluationScore}
        articleId={questionId}
        content={article.article.content}
        member={article.member}
        comments={article.comments}
        count={article.count}
        updatedAt={article.article.createdAt || article.article.updatedAt}
      />
      <hr />

      {/* pagination */}
      {Math.ceil(article.count.answer / 10) > 1 && (
        <section className="pagination">
          <span>총 답변 {article.count.answer}개</span>
          <ul>
            {Array(Math.ceil(article.count.answer / 10))
              .fill(null)
              .map((v, i) => (
                <Link
                  key={i}
                  to={`/questions/${article.article.id}?page=${i + 1}`}
                  data-p={+searchParams.get("page") === i + 1 && "p"}
                >
                  {i + 1}
                </Link>
              ))}
          </ul>
        </section>
      )}

      {/* answers */}
      <ul>
        {article.answers.map((answer) => (
          <li key={answer.id}>
            <Answer
              evaluationScore={answer.evaluationScore}
              articleId={questionId}
              answerId={answer.id}
              content={answer.content}
              member={answer.member}
              comments={answer.comments}
              count={answer.count}
              updatedAt={answer.createdAt || answer.updatedAt}
            />

            <hr />
          </li>
        ))}
      </ul>

      {/* answer input */}
      <form onSubmit={onSubmitAnswer}>
        <span>Your Answer</span>

        <Wysiwyg height="60vh" wysiwygRef={wysiwygRef} />

        <button type="submit">Post Your Answer</button>
      </form>
    </StyledArticlePage>
  );
};

export default ArticlePage;
