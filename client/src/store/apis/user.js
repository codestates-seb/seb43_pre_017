import { serverInstance } from ".";

/** 2023/04/12 - 유저 생성 요청 - by 1-blue */
const apiFetchUsers = async () => serverInstance.get(`/users`);

/** 2023/04/12 - 유저 api 요청 메서드들을 갖는 객체 - by 1-blue */
export const userApiService = {
  apiFetchUsers,
};
