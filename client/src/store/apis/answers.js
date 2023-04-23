import { serverInstance } from ".";

/** 2023/04/20 - 특정 article의 answer들 패치 요청 - by 1-blue */
const apiFetchAnswersOfArticle = async ({ articleId, page = 1, size = 10 }) =>
  serverInstance.get(`/article/${articleId}/answers?page=${page}&size=${size}`);

/** 2023/04/20 - answer들 api 요청 메서드들을 갖는 객체 - by 1-blue */
export const answersApiService = {
  apiFetchAnswersOfArticle,
};
