import {
  StyledSortTab,
  StyledNewsetTab,
  StyledArticleTab,
  StyledAnswerTab,
} from "./style";

/** 20230422 - 유저 정렬 탭 - by sinyaenok */
const SortTab = () => {
  return (
    <StyledSortTab>
      <NewTab />
      <ArticleTab />
      <AnswerTab />
    </StyledSortTab>
  );
};

/** 20230422 - 신규 유저 정렬 탭  - by sinyaenok*/
const NewTab = () => {
  return (
    <StyledNewsetTab>
      <a href="/members?tab=newset">New Set</a>
    </StyledNewsetTab>
  );
};

/** 20230422 - 질문 많은 순 정렬 탭  - by sinyaenok*/
const ArticleTab = () => {
  return (
    <StyledArticleTab>
      <a href="/members?tab=article">Article</a>
    </StyledArticleTab>
  );
};

/** 20230422 - 답변 많은 순 정렬 탭  - by sinyaenok*/
const AnswerTab = () => {
  return (
    <StyledAnswerTab>
      <a href="/members?tab=answer">Answer</a>
    </StyledAnswerTab>
  );
};

export default SortTab;
