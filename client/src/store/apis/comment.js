import { serverInstance } from ".";

/** 2023/04/20 - 특정 article의 댓글 생성 요청 - by 1-blue */
const apiCreateCommentOfArticle = async ({ articleId, ...body }) =>
  serverInstance.post(`/api/article/${articleId}/comment`, body);

/** 2023/04/20 - 특정 article의 댓글 수정 요청 - by 1-blue */
const apiUpdateCommentOfArticle = async ({ articleId, commentId, ...body }) =>
  serverInstance.patch(`/api/article/${articleId}/comment/${commentId}`, body);

/** 2023/04/20 - 특정 article의 댓글 삭제 요청 - by 1-blue */
const apiDeleteCommentOfArticle = async ({ articleId, commentId }) =>
  serverInstance.delete(`/api/article/${articleId}/comment/${commentId}`);

/** 2023/04/20 - 특정 answer의 댓글 생성 요청 - by 1-blue */
const apiCreateCommentOfAnswer = async ({ answerId, ...body }) =>
  serverInstance.post(`/api/answer/${answerId}/comment`, body);

/** 2023/04/20 - 특정 answer의 댓글 수정 요청 - by 1-blue */
const apiUpdateCommentOfAnswer = async ({ answerId, commentId, ...body }) =>
  serverInstance.patch(`/api/answer/${answerId}/comment/${commentId}`, body);

/** 2023/04/20 - 특정 answer의 댓글 삭제 요청 - by 1-blue */
const apiDeleteCommentOfAnswer = async ({ answerId, commentId }) =>
  serverInstance.delete(`/api/answer/${answerId}/comment/${commentId}`);

/** 2023/04/20 - 댓글들 api 요청 메서드들을 갖는 객체 - by 1-blue */
export const commentApiService = {
  apiCreateCommentOfArticle,
  apiUpdateCommentOfArticle,
  apiDeleteCommentOfArticle,
  apiCreateCommentOfAnswer,
  apiUpdateCommentOfAnswer,
  apiDeleteCommentOfAnswer,
};
