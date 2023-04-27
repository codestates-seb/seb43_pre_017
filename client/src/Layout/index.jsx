// component
import { useEffect } from "react";
import { serverInstance } from "../store/apis";
import Header from "./Header";
import SideBar from "./SideBar";
import Main from "./Main";
import Footer from "./Footer";

/** 2023/03/23 - 레이아웃을 적용하는 컴포넌트 - by 1-blue */
const Layout = ({ children }) => {
  useEffect(() => {
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
  });
  return (
    <>
      <Header />

      <Main>
        <SideBar />
        {children}
      </Main>
      <Footer />
    </>
  );
};

export default Layout;
