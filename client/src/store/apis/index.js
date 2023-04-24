import axios from "axios";

const BASE_URL = "http://";

/** 2023/04/12 - 백엔드에 API 요청하는 axios 인스턴스 - by 1-blue */
export const serverInstance = axios.create({
  baseURL: BASE_URL + "/api",
  withCredentials: true,
  timeout: 4000,
});

export * from "./article";
export * from "./comments";
export * from "./comment";
export * from "./answers";
export * from "./answer";
export * from "./evaluation";
