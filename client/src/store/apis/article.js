import { serverInstance } from ".";

/** 2023/04/19 - 특정 article 패치 요청 - by 1-blue */
const apiFetchArticle = async ({ articleId }) =>
  serverInstance.get(`/article/${articleId}`);

/** 2023/04/21 - 특정 article 제거 요청 - by 1-blue */
const apiDeleteArticle = async ({ articleId }) =>
  serverInstance.delete(`/article/${articleId}`);

/** 2023/04/19 - article api 요청 메서드들을 갖는 객체 - by 1-blue */
export const articleApiService = {
  apiFetchArticle,
  apiDeleteArticle,
};
