import axios from "axios";

/** 2023/04/12 - 백엔드에 API 요청하는 axios 인스턴스 - by 1-blue */
export const serverInstance = axios.create({
  baseURL: "https://jsonplaceholder.typicode.com",
  withCredentials: true,
  timeout: 4000,
});

export * from "./user";
