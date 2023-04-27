import { serverInstance } from ".";

/** 2023/04/20 - 특정 article의 answer 생성 요청 - by 1-blue */
const apiCreateAnswerOfArticle = async ({ articleId, ...body }) =>
  serverInstance.post(`/article/${articleId}/answer`, body);

/** 2023/04/20 - 특정 article의 answer 수정 요청 - by 1-blue */
const apiUpdateAnswerOfArticle = async ({ articleId, answerId, ...body }) =>
  serverInstance.patch(`/article/${articleId}/answer/${answerId}`, body);

/** 2023/04/20 - 특정 article의 answer 제거 요청 - by 1-blue */
const apiDeleteAnswerOfArticle = async ({ articleId, answerId }) =>
  serverInstance.delete(`/article/${articleId}/answer/${answerId}`);

/** 2023/04/20 - answer api 요청 메서드들을 갖는 객체 - by 1-blue */
export const answerApiService = {
  apiCreateAnswerOfArticle,
  apiUpdateAnswerOfArticle,
  apiDeleteAnswerOfArticle,
};
