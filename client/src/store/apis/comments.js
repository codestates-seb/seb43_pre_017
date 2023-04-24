import { serverInstance } from ".";

/** 2023/04/20 - 특정 article의 댓글들 패치 요청 - by 1-blue */
const apiFetchCommentsOfArticle = async ({ articleId, page = 1, size = 10 }) =>
  serverInstance.get(
    `/api/article/${articleId}/comments?page=${page}&size=${size}`,
  );

/** 2023/04/20 - 특정 answer의 댓글들 패치 요청 - by 1-blue */
const apiFetchCommentsOfAnswer = async (answerId, page = 1, size = 10) =>
  serverInstance.get(
    `/api/answer/${answerId}/comments?page=${page}&size=${size}`,
  );

/** 2023/04/20 - 댓글들 api 요청 메서드들을 갖는 객체 - by 1-blue */
export const commentsApiService = {
  apiFetchCommentsOfArticle,
  apiFetchCommentsOfAnswer,
};
