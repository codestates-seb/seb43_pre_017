import { createSlice } from "@reduxjs/toolkit";

// thunk
import {
  answerThunkService,
  answersThunkService,
  articleThunkService,
  commentThunkService,
  commentsThunkService,
} from "../thunks";
import { evaluationThunkService } from "../thunks/evaluation";

const initialState = {
  // 특정 article 데이터
  article: null,

  // 특정 article 관련 가져오기
  fetchArticleLoading: false,
  fetchArticleDone: null,
  fetchArticleError: null,
  // 특정 article의 comment들 가져오기
  fetchCommentsOfArticleLoading: false,
  fetchCommentsOfArticleDone: null,
  fetchCommentsOfArticleError: null,
  // 특정 answer들 가져오기
  fetchAnswersLoading: false,
  fetchAnswersDone: null,
  fetchAnswersError: null,
  // 특정 answer의 comment들 가져오기
  fetchCommentsOfAnswerLoading: false,
  fetchCommentsOfAnswerDone: null,
  fetchCommentsOfAnswerError: null,
  // 특정 article의 답변 생성
  createAnswerOfArticleLoading: false,
  createAnswerOfArticleDone: null,
  createAnswerOfArticleError: null,
  // 특정 article의 답변 수정
  updateAnswerOfArticleLoading: false,
  updateAnswerOfArticleDone: null,
  updateAnswerOfArticleError: null,
  // 특정 article의 답변 삭제
  deleteAnswerOfArticleLoading: false,
  deleteAnswerOfArticleDone: null,
  deleteAnswerOfArticleError: null,
  // 특정 article의 댓글 생성
  createCommentOfArticleLoading: false,
  createCommentOfArticleDone: null,
  createCommentOfArticleError: null,
  // 특정 article의 댓글 수정
  updateCommentOfArticleLoading: false,
  updateCommentOfArticleDone: null,
  updateCommentOfArticleError: null,
  // 특정 article의 댓글 삭제
  deleteCommentOfArticleLoading: false,
  deleteCommentOfArticleDone: null,
  deleteCommentOfArticleError: null,
  // 특정 answer의 댓글 생성
  createCommentOfAnswerLoading: false,
  createCommentOfAnswerDone: null,
  createCommentOfAnswerError: null,
  // 특정 answer의 댓글 수정
  updateCommentOfAnswerLoading: false,
  updateCommentOfAnswerDone: null,
  updateCommentOfAnswerError: null,
  // 특정 answer의 댓글 삭제
  deleteCommentOfAnswerLoading: false,
  deleteCommentOfAnswerDone: null,
  deleteCommentOfAnswerError: null,
  // 특정 article의 평가 추가
  uploadEvaluationOfArticleLoading: false,
  uploadEvaluationOfArticleDone: null,
  uploadEvaluationOfArticleError: null,
  // 특정 answer의 평가 추가
  uploadEvaluationOfAnswerLoading: false,
  uploadEvaluationOfAnswerDone: null,
  uploadEvaluationOfAnswerError: null,
};

/** 2023/04/19 - ariticle 관련 슬라이스 - by 1-blue */
const ariticleSlice = createSlice({
  name: "article",
  initialState,
  reducers: {
    resetMessage(state) {
      state.fetchArticleLoading = false;
      state.fetchArticleDone = null;
      state.fetchArticleError = null;

      state.fetchCommentsOfArticleLoading = false;
      state.fetchCommentsOfArticleDone = null;
      state.fetchCommentsOfArticleError = null;

      state.fetchAnswersLoading = false;
      state.fetchAnswersDone = null;
      state.fetchAnswersError = null;

      state.fetchCommentsOfAnswerLoading = false;
      state.fetchCommentsOfAnswerDone = null;
      state.fetchCommentsOfAnswerError = null;

      state.createAnswerOfArticleLoading = false;
      state.createAnswerOfArticleDone = null;
      state.createAnswerOfArticleError = null;

      state.updateAnswerOfArticleLoading = false;
      state.updateAnswerOfArticleDone = null;
      state.updateAnswerOfArticleError = null;

      state.deleteAnswerOfArticleLoading = false;
      state.deleteAnswerOfArticleDone = null;
      state.deleteAnswerOfArticleError = null;

      state.createCommentOfArticleLoading = false;
      state.createCommentOfArticleDone = null;
      state.createCommentOfArticleError = null;

      state.updateCommentOfArticleLoading = false;
      state.updateCommentOfArticleDone = null;
      state.updateCommentOfArticleError = null;

      state.deleteCommentOfArticleLoading = false;
      state.deleteCommentOfArticleDone = null;
      state.deleteCommentOfArticleError = null;

      state.createCommentOfAnswerLoading = false;
      state.createCommentOfAnswerDone = null;
      state.createCommentOfAnswerError = null;

      state.updateCommentOfAnswerLoading = false;
      state.updateCommentOfAnswerDone = null;
      state.updateCommentOfAnswerError = null;

      state.deleteCommentOfAnswerLoading = false;
      state.deleteCommentOfAnswerDone = null;
      state.deleteCommentOfAnswerError = null;

      state.uploadEvaluationOfArticleLoading = false;
      state.uploadEvaluationOfArticleDone = null;
      state.uploadEvaluationOfArticleError = null;

      state.uploadEvaluationOfAnswerLoading = false;
      state.uploadEvaluationOfAnswerDone = null;
      state.uploadEvaluationOfAnswerError = null;
    },
    reset(state) {
      state.article = null;
    },
  },

  // "thunk"가 적용된 액션 ( 비동기 액션 )
  extraReducers(builder) {
    // article 상세 정보 불러오기
    builder.addCase(articleThunkService.fetchArticleThunk.pending, (state) => {
      state.fetchArticleLoading = true;
    });
    builder.addCase(
      articleThunkService.fetchArticleThunk.fulfilled,
      (state, action) => {
        state.fetchArticleLoading = false;
        state.fetchArticleDone = action.payload.message;

        state.article = action.payload;
        if (!state.article.answers) state.article.answers = [];
        if (!state.article.comments) state.article.comments = [];
      },
    );
    builder.addCase(
      articleThunkService.fetchArticleThunk.rejected,
      (state, action) => {
        state.fetchArticleLoading = false;
        if (action.payload?.message) {
          state.fetchArticleError = action.payload.message;
        }

        console.error("fetchArticleThunk >> ", action);
      },
    );

    // 특정 article 제거
    builder.addCase(articleThunkService.deleteArticleThunk.pending, (state) => {
      state.fetchArticleLoading = true;
    });
    builder.addCase(
      articleThunkService.deleteArticleThunk.fulfilled,
      (state, action) => {
        state.fetchArticleLoading = false;
        state.fetchArticleDone = action.payload.message;

        state.article = null;
      },
    );
    builder.addCase(
      articleThunkService.deleteArticleThunk.rejected,
      (state, action) => {
        state.fetchArticleLoading = false;
        if (action.payload?.message) {
          state.fetchArticleError = action.payload.message;
        }

        console.error("fetchArticleThunk >> ", action);
      },
    );

    // 특정 article의 comment들 불러오기
    builder.addCase(
      commentsThunkService.fetchCommentsOfArticleThunk.pending,
      (state) => {
        state.fetchCommentsOfArticleLoading = true;
      },
    );
    builder.addCase(
      commentsThunkService.fetchCommentsOfArticleThunk.fulfilled,
      (state, action) => {
        state.fetchCommentsOfArticleLoading = false;
        state.fetchCommentsOfArticleDone = action.payload.message;

        state.article.comments.push(...action.payload.comments);
      },
    );
    builder.addCase(
      commentsThunkService.fetchCommentsOfArticleThunk.rejected,
      (state, action) => {
        state.fetchCommentsOfArticleLoading = false;
        if (action.payload?.message) {
          state.fetchArticleError = action.payload.message;
        }

        console.error("fetchCommentsOfArticleThunk >> ", action);
      },
    );

    // 특정 article의 answer들 불러오기
    builder.addCase(answersThunkService.fetchAnswersThunk.pending, (state) => {
      state.fetchAnswersLoading = true;
    });
    builder.addCase(
      answersThunkService.fetchAnswersThunk.fulfilled,
      (state, action) => {
        state.fetchAnswersLoading = false;
        state.fetchAnswersDone = action.payload.message;

        state.article.answers = action.payload.answers.map((answer) => ({
          ...answer,
          comments: [],
        }));
      },
    );
    builder.addCase(
      answersThunkService.fetchAnswersThunk.rejected,
      (state, action) => {
        state.fetchAnswersLoading = false;
        if (action.payload?.message) {
          state.fetchAnswersError = action.payload.message;
        }

        console.error("fetchAnswersThunk >> ", action);
      },
    );

    // 특정 answer의 comment들 불러오기
    builder.addCase(
      commentsThunkService.fetchCommentsOfAnswerThunk.pending,
      (state) => {
        state.fetchCommentsOfAnswerLoading = true;
      },
    );
    builder.addCase(
      commentsThunkService.fetchCommentsOfAnswerThunk.fulfilled,
      (state, action) => {
        state.fetchCommentsOfAnswerLoading = false;
        state.fetchCommentsOfAnswerDone = action.payload.message;

        const targetIndex = state.article.answers.findIndex(
          (answer) => answer.id === action.payload.answerId,
        );
        state.article.answers[targetIndex].comments.push(
          ...action.payload.comments,
        );
      },
    );
    builder.addCase(
      commentsThunkService.fetchCommentsOfAnswerThunk.rejected,
      (state, action) => {
        state.fetchCommentsOfAnswerLoading = false;
        if (action.payload?.message) {
          state.fetchCommentsOfAnswerError = action.payload.message;
        }

        console.error("fetchCommentsOfAnswerThunk >> ", action);
      },
    );

    // 특정 article의 answer 생성
    builder.addCase(answerThunkService.createAnswerThunk.pending, (state) => {
      state.createAnswerOfArticleLoading = true;
    });
    builder.addCase(
      answerThunkService.createAnswerThunk.fulfilled,
      (state, action) => {
        state.createAnswerOfArticleLoading = false;
        state.createAnswerOfArticleDone = action.payload.message;

        // FIXME: 답변 생성
        state.article.answers.push({
          id: action.payload.answerId,
          content: action.meta.arg.content,
          evaluationScore: 0,
          createdAt: "2023-04-19T21:00:00",
          updatedAt: "2023-04-19T21:00:00",
          // FIXME: 로그인한 유저 정보
          member: {
            id: 2,
            name: "2번 유저",
          },
          count: {
            comments: 0,
          },
          comments: [],
        });
      },
    );
    builder.addCase(
      answerThunkService.createAnswerThunk.rejected,
      (state, action) => {
        state.createAnswerOfArticleLoading = false;
        if (action.payload?.message) {
          state.createAnswerOfArticleError = action.payload.message;
        }

        console.error("createAnswerThunk >> ", action);
      },
    );

    // 특정 article의 answer 수정
    builder.addCase(answerThunkService.updateAnswerThunk.pending, (state) => {
      state.updateAnswerOfArticleLoading = true;
    });
    builder.addCase(
      answerThunkService.updateAnswerThunk.fulfilled,
      (state, action) => {
        state.updateAnswerOfArticleLoading = false;
        state.updateAnswerOfArticleDone = action.payload.message;

        // FIXME: 수정될 답변 찾고/넣기
        const targetIndex = state.article.answers.findIndex(
          (answer) => answer.id === 1,
        );
        state.article.answers[targetIndex].content = "수정";
      },
    );
    builder.addCase(
      answerThunkService.updateAnswerThunk.rejected,
      (state, action) => {
        state.updateAnswerOfArticleLoading = false;
        if (action.payload?.message) {
          state.updateAnswerOfArticleError = action.payload.message;
        }

        console.error("updateAnswerThunk >> ", action);
      },
    );

    // 특정 article의 answer 삭제
    builder.addCase(answerThunkService.deleteAnswerThunk.pending, (state) => {
      state.deleteAnswerOfArticleLoading = true;
    });
    builder.addCase(
      answerThunkService.deleteAnswerThunk.fulfilled,
      (state, action) => {
        state.deleteAnswerOfArticleLoading = false;
        state.deleteAnswerOfArticleDone = action.payload.message;

        state.article.answers = state.article.answers.filter(
          (answer) => answer.id !== +action.payload.answerId,
        );
      },
    );
    builder.addCase(
      answerThunkService.deleteAnswerThunk.rejected,
      (state, action) => {
        state.deleteAnswerOfArticleLoading = false;
        if (action.payload?.message) {
          state.deleteAnswerOfArticleError = action.payload.message;
        }

        console.error("deleteAnswerThunk >> ", action);
      },
    );

    // 특정 article의 comment 생성
    builder.addCase(
      commentThunkService.createCommentOfArticleThunk.pending,
      (state) => {
        state.createCommentOfArticleLoading = true;
      },
    );
    builder.addCase(
      commentThunkService.createCommentOfArticleThunk.fulfilled,
      (state, action) => {
        state.createCommentOfArticleLoading = false;
        state.createCommentOfArticleDone = action.payload.message;

        state.article.comments.push({
          id: action.payload.commentId,
          content: action.meta.arg.content,
          // FIXME: 작성자 추가
          member: {
            id: 1,
            name: "유저1",
          },
          createdAt: "2023-04-19T21:00:00",
          updatedAt: "2023-04-19T21:00:00",
        });
      },
    );
    builder.addCase(
      commentThunkService.createCommentOfArticleThunk.rejected,
      (state, action) => {
        state.createCommentOfArticleLoading = false;
        if (action.payload?.message) {
          state.createCommentOfArticleError = action.payload.message;
        }

        console.error("createCommentOfArticleThunk >> ", action);
      },
    );
    // 특정 article의 comment 수정
    builder.addCase(
      commentThunkService.updateCommentOfArticleThunk.pending,
      (state) => {
        state.updateCommentOfArticleLoading = true;
      },
    );
    builder.addCase(
      commentThunkService.updateCommentOfArticleThunk.fulfilled,
      (state, action) => {
        state.updateCommentOfArticleLoading = false;
        state.updateCommentOfArticleDone = action.payload.message;

        const targetIndex = state.article.comments.findIndex(
          (comment) => comment.id === +action.meta.arg.commentId,
        );
        state.article.comments[targetIndex].content = action.meta.arg.content;
      },
    );
    builder.addCase(
      commentThunkService.updateCommentOfArticleThunk.rejected,
      (state, action) => {
        state.updateCommentOfArticleLoading = false;
        if (action.payload?.message) {
          state.updateCommentOfArticleError = action.payload.message;
        }

        console.error("updateCommentOfArticleThunk >> ", action);
      },
    );
    // 특정 article의 comment 삭제
    builder.addCase(
      commentThunkService.deleteCommentOfArticleThunk.pending,
      (state) => {
        state.deleteCommentOfArticleLoading = true;
      },
    );
    builder.addCase(
      commentThunkService.deleteCommentOfArticleThunk.fulfilled,
      (state, action) => {
        state.deleteCommentOfArticleLoading = false;
        state.deleteCommentOfArticleDone = action.payload.message;

        state.article.comments = state.article.comments.filter(
          (comment) => comment.id !== +action.meta.arg.commentId,
        );
      },
    );
    builder.addCase(
      commentThunkService.deleteCommentOfArticleThunk.rejected,
      (state, action) => {
        state.deleteCommentOfArticleLoading = false;
        if (action.payload?.message) {
          state.deleteCommentOfArticleError = action.payload.message;
        }

        console.error("deleteCommentOfArticleThunk >> ", action);
      },
    );

    // 특정 answer의 comment 생성
    builder.addCase(
      commentThunkService.createCommentOfAnswerThunk.pending,
      (state) => {
        state.createCommentOfAnswerLoading = true;
      },
    );
    builder.addCase(
      commentThunkService.createCommentOfAnswerThunk.fulfilled,
      (state, action) => {
        state.createCommentOfAnswerLoading = false;
        state.createCommentOfAnswerDone = action.payload.message;

        const targetIndex = state.article.answers.findIndex(
          (answer) => answer.id === +action.meta.arg.answerId,
        );
        state.article.answers[targetIndex].comments.push({
          id: action.payload.commentId,
          content: action.meta.arg.content,
          // FIXME: 로그인한 유저
          member: {
            id: 1,
            name: "유저1",
          },
          createdAt: "2023-04-19T21:00:00",
          updatedAt: "2023-04-19T21:00:00",
        });
      },
    );
    builder.addCase(
      commentThunkService.createCommentOfAnswerThunk.rejected,
      (state, action) => {
        state.createCommentOfAnswerLoading = false;
        if (action.payload?.message) {
          state.createCommentOfArticleError = action.payload.message;
        }

        console.error("createCommentOfAnswerThunk >> ", action);
      },
    );
    // 특정 answer의 comment 수정
    builder.addCase(
      commentThunkService.updateCommentOfAnswerThunk.pending,
      (state) => {
        state.updateCommentOfArticleLoading = true;
      },
    );
    builder.addCase(
      commentThunkService.updateCommentOfAnswerThunk.fulfilled,
      (state, action) => {
        state.updateCommentOfArticleLoading = false;
        state.updateCommentOfArticleDone = action.payload.message;

        const targetAnswerIndex = state.article.answers.findIndex(
          (answer) => answer.id === +action.meta.arg.answerId,
        );
        const targetCommentIndex = state.article.answers[
          targetAnswerIndex
        ].comments.findIndex(
          (comment) => comment.id === +action.meta.arg.commentId,
        );
        state.article.answers[targetAnswerIndex].comments[
          targetCommentIndex
        ].content = action.meta.arg.content;
      },
    );
    builder.addCase(
      commentThunkService.updateCommentOfAnswerThunk.rejected,
      (state, action) => {
        state.updateCommentOfArticleLoading = false;
        if (action.payload?.message) {
          state.updateCommentOfArticleError = action.payload.message;
        }

        console.error("updateCommentOfAnswerThunk >> ", action);
      },
    );
    // 특정 answer의 comment 삭제
    builder.addCase(
      commentThunkService.deleteCommentOfAnswerThunk.pending,
      (state) => {
        state.deleteCommentOfArticleLoading = true;
      },
    );
    builder.addCase(
      commentThunkService.deleteCommentOfAnswerThunk.fulfilled,
      (state, action) => {
        state.deleteCommentOfArticleLoading = false;
        state.deleteCommentOfArticleDone = action.payload.message;

        const targetAnswerIndex = state.article.answers.findIndex(
          (answer) => answer.id === +action.meta.arg.answerId,
        );
        state.article.answers[targetAnswerIndex].comments =
          state.article.answers[targetAnswerIndex].comments.filter(
            (comment) => comment.id !== +action.meta.arg.commentId,
          );
      },
    );
    builder.addCase(
      commentThunkService.deleteCommentOfAnswerThunk.rejected,
      (state, action) => {
        state.deleteCommentOfArticleLoading = false;
        if (action.payload?.message) {
          state.deleteCommentOfArticleError = action.payload.message;
        }

        console.error("deleteCommentOfAnswerThunk >> ", action);
      },
    );

    // 특정 article의 평가 추가
    builder.addCase(
      evaluationThunkService.uploadEvaluationOfArticleThunk.pending,
      (state) => {
        state.uploadEvaluationOfArticleLoading = true;
      },
    );
    builder.addCase(
      evaluationThunkService.uploadEvaluationOfArticleThunk.fulfilled,
      (state, action) => {
        state.uploadEvaluationOfArticleLoading = false;
        state.uploadEvaluationOfArticleDone = action.payload.message;

        if (action.meta.arg.evaluationScore === "+1") {
          state.article.article.evaluationScore += 1;
        } else if (action.meta.arg.evaluationScore === "-1") {
          state.article.article.evaluationScore -= 1;
        }
      },
    );
    builder.addCase(
      evaluationThunkService.uploadEvaluationOfArticleThunk.rejected,
      (state, action) => {
        state.uploadEvaluationOfArticleLoading = false;
        if (action.payload?.message) {
          state.uploadEvaluationOfArticleError = action.payload.message;
        }

        console.error("uploadEvaluationOfArticleThunk >> ", action);
      },
    );
    // 특정 answer의 평가 추가
    builder.addCase(
      evaluationThunkService.uploadEvaluationOfAnswerThunk.pending,
      (state) => {
        state.uploadEvaluationOfAnswerLoading = true;
      },
    );
    builder.addCase(
      evaluationThunkService.uploadEvaluationOfAnswerThunk.fulfilled,
      (state, action) => {
        state.uploadEvaluationOfAnswerLoading = false;
        state.uploadEvaluationOfAnswerDone = action.payload.message;

        const targetIndex = state.article.answers.findIndex(
          (answer) => answer.id === action.meta.arg.answerId,
        );
        if (action.meta.arg.evaluationScore === "+1") {
          state.article.answers[targetIndex].evaluationScore += 1;
        } else if (action.meta.arg.evaluationScore === "-1") {
          state.article.answers[targetIndex].evaluationScore -= 1;
        }
      },
    );
    builder.addCase(
      evaluationThunkService.uploadEvaluationOfAnswerThunk.rejected,
      (state, action) => {
        state.uploadEvaluationOfAnswerLoading = false;
        if (action.payload?.message) {
          state.uploadEvaluationOfAnswerError = action.payload.message;
        }

        console.error("uploadEvaluationOfAnswerThunk >> ", action);
      },
    );
  },
});

/** 2023/04/19 - article 관련 액션 크리에이터 - by 1-blue */
export const articleActions = ariticleSlice.actions;

export default ariticleSlice.reducer;
