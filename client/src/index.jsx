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

// layout
import Layout from "./Layout";

// component
import Home from "./pages/Home";
import Profile from "./pages/Profile";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    {/* redux-toolkit */}
    <Provider store={store}>
      {/* styled-components */}
      <ThemeProvider theme={theme}>
        {/* global */}
        <GlobalStyle />

        {/* 라우팅 ( react-router ) */}
        <BrowserRouter>
          {/* 전체 레이아웃 ( 네비게이션, 컨텐츠, 푸터 ) */}
          <Layout>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/profile" element={<Profile />} />
            </Routes>
          </Layout>
        </BrowserRouter>
      </ThemeProvider>
    </Provider>
  </React.StrictMode>,
);
