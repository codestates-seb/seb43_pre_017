// component
import Header from "./Header";
import SideBar from "./SideBar";
import Main from "./Main";
import Footer from "./Footer";

/** 2023/03/23 - 레이아웃을 적용하는 컴포넌트 - by 1-blue */
const Layout = ({ children }) => (
  <>
    <Header />

    <Main>
      <SideBar />
      {children}
    </Main>

    <Footer />
  </>
);

export default Layout;
