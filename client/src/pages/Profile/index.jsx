//style
import StyledProfile from "./style";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCakeCandles } from "@fortawesome/free-solid-svg-icons";
/** 2023/04/18 - 유저 프로필  - by sinyaenok*/
const Profile = () => {
  return (
    <StyledProfile>
      <div id="profile-info">
        <div className="mb16 ps-relative">
          <div id="info-head" className="d-flex ai-center fw-wrap gs16">
            {/* 이미지 */}
            <a id="user-img" className=" flex--item" href="/profile">
              <div className="imgbox">
                <img
                  className="userimg"
                  src="https://images.unsplash.com/photo-1543466835-00a7907e9de1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1974&q=80"
                  alt="user123"
                  width="128"
                  height="128"
                />
              </div>
            </a>
            {/* 이름, 날짜 등  */}
            <div className="flex--item">
              <div id="info-detail" className="d-flex ai-center fw-wrap gs8">
                {/* 이름 */}
                <div
                  id="user-name"
                  className="flex--item mb12 fs-headline2 lh-xs"
                >
                  갱얼쥐
                </div>
                {/* 날짜 */}
                <ul className="list-reset s-anchors s-anchors__inherit d-flex fc-light gs8 mln4 fw-wrap">
                  <li className="flex--item">
                    <div className="d-flex gs4 gsx ai-center">
                      <div className="flex--item fc-black-350">
                        <FontAwesomeIcon
                          className="cake"
                          icon={faCakeCandles}
                        />
                      </div>
                      <div className="flex--item">
                        Member for
                        <span>6 days</span>
                      </div>
                    </div>
                  </li>
                </ul>
              </div>
            </div>
            {/* 수정버튼 */}
            <div
              id="info-edit"
              className="ps-absolute t0 r0 d-flex gs6 fw-wrap"
            >
              <a className="flex--item edit-btn" href="/profile">
                Edit profile
              </a>
            </div>
          </div>
        </div>

        {/* 질문/답변 창 */}
        <div className="d-flex mb48">
          <section className="flex--item fl-grow1 wmx100">
            <div className="d-grid grid__2 g24 lg:grid__1">
              <div
                id="user-panel-answers"
                className="d-flex fd-column js-user-panel h100"
              >
                <div className="d-flex ai-end jc-space-between mb9 fw-wrap">
                  <div className="flex-item fl-grow1">
                    <div className="d-flex fd-column">
                      <h3 className="flex-item fs-title mb0">Answers</h3>
                    </div>
                  </div>
                </div>
                <div className="fl-grow1 ba bc-black-100 bar-md">
                  <div className="d-flex flex__center h100 s-empty-state p24">
                    <p className="wmx4 m0">
                      You have not
                      <a href="/profile">answered</a>
                      any questions
                    </p>
                  </div>
                </div>
              </div>
              <div
                id="user-panel-questions"
                className="d-flex fd-column js-user-panel h100"
              >
                <div className="d-flex ai-end jc-space-between mb9 fw-wrap">
                  <div className="flex-item fl-grow1">
                    <div className="d-flex fd-column">
                      <h3 className="flex-item fs-title mb0">Questions</h3>
                    </div>
                  </div>
                </div>
                <div className="fl-grow1 ba bc-black-100 bar-md">
                  <div className="d-flex flex__center h100 s-empty-state p24">
                    <p className="wmx4 m0">
                      You have not
                      <a href="/profile">asked</a>
                      any questions
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </section>
        </div>
      </div>
    </StyledProfile>
  );
};

export default Profile;
