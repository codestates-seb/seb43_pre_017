import axios from "axios";

const BASE_URL = process.env.REACT_APP_BASE_URL;

/** 2023/04/12 - 백엔드에 API 요청하는 axios 인스턴스 - by 1-blue */
export const serverInstance = axios.create({
  baseURL: BASE_URL + "/api",
  withCredentials: false,
  timeout: 4000,
});

serverInstance.interceptors.request.use(
  (config) => {
    if (!config.headers) return config;

    let accessToken = localStorage.getItem("Authorization");
    let refreshToken = localStorage.getItem("refresh");

    if (accessToken !== null) {
      config.headers.Authorization = `Bearer ${accessToken}`;
      config.headers.Refresh = refreshToken;
    }

    return config;
  },
  (error) => {
    console.error("axios interceptors >> ", error);
  },
);

export * from "./article";
export * from "./comments";
export * from "./comment";
export * from "./answers";
export * from "./answer";
export * from "./evaluation";
