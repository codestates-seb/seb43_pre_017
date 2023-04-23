import { serverInstance } from ".";

/** 2023/04/23 - 특정 article의 evaluation 요청 - by 1-blue */
const apiEvaluationOfArticle = async ({ articleId, ...body }) =>
  serverInstance.post(`/article/${articleId}/evaluation`, body);

/** 2023/04/23 - 특정 answer의 evaluation 요청 - by 1-blue */
const apiEvaluationOfAnswer = async ({ answerId, ...body }) =>
  serverInstance.post(`/answer/${answerId}/evaluation`, body);

/** 2023/04/23 - evaluation api 요청 메서드들을 갖는 객체 - by 1-blue */
export const evaluationApiService = {
  apiEvaluationOfArticle,
  apiEvaluationOfAnswer,
};
