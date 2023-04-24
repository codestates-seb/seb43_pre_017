import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { ThemeProvider } from "styled-components";
import { Provider } from "react-redux";

// store
import store from "./store";

// style
import theme from "./style/theme";
import { GlobalStyle } from "./style/global";

// react-toastify
import { Flip, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

// layout
import Layout from "./Layout";

// component
import Home from "./pages/Home";
import ArticlePage from "./pages/Article";
import NotFound from "./pages/NotFound";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    {/* redux-toolkit */}
    <Provider store={store}>
      {/* styled-components */}
      <ThemeProvider theme={theme}>
        {/* global */}
        <GlobalStyle />

        {/* toast message */}
        <ToastContainer
          theme="dark"
          hideProgressBar
          autoClose={1500}
          position="top-center"
          transition={Flip}
          pauseOnFocusLoss={false}
        />

        {/* 라우팅 ( react-router ) */}
        <BrowserRouter>
          {/* 전체 레이아웃 ( 네비게이션, 컨텐츠, 푸터 ) */}
          <Layout>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/questions/:questionId" element={<ArticlePage />} />
              <Route path="*" element={<NotFound />} />
            </Routes>
          </Layout>
        </BrowserRouter>
      </ThemeProvider>
    </Provider>
  </React.StrictMode>,
);
