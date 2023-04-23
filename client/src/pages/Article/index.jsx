import { useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";

// rtk
import {
  answersThunkService,
  answerThunkService,
  articleThunkService,
} from "../../store/thunks";

// hook
import useWysiwyg from "../../hooks/useWysiwyg";

// component
import Article from "../../components/Article";
import Answer from "../../components/Answer";
import Wysiwyg from "../../components/common/Wysiwyg";

// style
import StyledArticlePage from "./style";

/** 2023/04/18 - 상세 article 페이지 컴포넌트 - by 1-blue */
const ArticlePage = () => {
  const { questionId } = useParams();

  /** 2023/04/19 - article 상세 데이터 요청 및 가져오기 - by 1-blue */
  const dispatch = useDispatch();
  const { article } = useSelector((state) => state.article);

  /** 2023/04/19 - article 상세 데이터 요청 - by 1-blue */
  useEffect(() => {
    // FIXME: article 가져오기
    dispatch(articleThunkService.fetchArticleThunk({ articleId: questionId }));

    // FIXME: answer들 가져오기
    dispatch(answersThunkService.fetchAnswersThunk({ articleId: questionId }));
  }, [dispatch, questionId]);

  /** 2023/04/20 - 위지윅 훅 - by 1-blue */
  const { wysiwygRef, getContents } = useWysiwyg();

  /** 2023/04/20 - 답변 제출 폼 - by 1-blue */
  const onSubmitAnswer = (e) => {
    e.preventDefault();

    const content = getContents();

    if (content.trim().length <= 11) return alert("내용을 입력해주세요!");

    // FIXME: 최종 제거
    console.log(questionId, content);

    // answer 생성
    dispatch(
      answerThunkService.createAnswerThunk({ articleId: questionId, content }),
    );
  };

  // FIXME: 스피너
  if (!article) return <span>로딩중...</span>;

  return (
    <StyledArticlePage>
      {/* title / ask btn */}
      <section className="top-wrapper">
        <h1>{article.article.title}</h1>

        <Link to="/question/ask">Ask Question</Link>
      </section>

      {/* time */}
      <section className="sub-top-wrapper">
        <time>작성일 {11}일전</time>
        <time>수정 {5}일전</time>
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
      />
      <hr />

      {/* answers */}
      <ul>
        {article.answers.map((answer) => (
          <>
            <li key={answer.id}>
              <Answer
                evaluationScore={answer.evaluationScore}
                articleId={questionId}
                answerId={answer.id}
                content={answer.content}
                member={answer.member}
                comments={answer.comments}
                count={answer.count}
              />
            </li>

            <hr />
          </>
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
