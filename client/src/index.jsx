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
import ToastMessage from "./components/ToastMessage";
import Home from "./pages/Home";
import ArticlePage from "./pages/Article";
import Profile from "./pages/Profile";
import Members from "./pages/Members";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Logout from "./pages/logout";
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

        <ToastMessage />

        {/* 라우팅 ( react-router ) */}
        <BrowserRouter>
          {/* 전체 레이아웃 ( 네비게이션, 컨텐츠, 푸터 ) */}

          <Layout>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/members/:name/:id" element={<Profile />} />
              <Route path="/questions/:questionId" element={<ArticlePage />} />
              <Route path="/members/:name/:id" element={<Profile />} />
              <Route path="/members" element={<Members />} />
              <Route path="/login" element={<Login />} />
              <Route path="/signup" element={<Signup />} />
              <Route path="/logout" element={<Logout />} />
              <Route path="*" element={<NotFound />} />
            </Routes>
          </Layout>
        </BrowserRouter>
      </ThemeProvider>
    </Provider>
  </React.StrictMode>,
);
