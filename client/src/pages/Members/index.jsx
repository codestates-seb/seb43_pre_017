import { StyledMembers, StyledSearchFrom } from "./style";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";
//component
import SortTeb from "./SortTeb";
import MemberBrowser from "./MemberBrowser";

/** 20230421 - 유저 목록 페이지 - by sinyaenok */
const Members = () => {
  return (
    <StyledMembers>
      <div id="main-bar">
        <h1>Members</h1>
        <div className="d-flex ai-center js-between">
          {/* 유저 검색창 */}
          <StyledSearchFrom>
            <div className="search-bar">
              <FontAwesomeIcon
                icon={faMagnifyingGlass}
                className="search-icon"
              />
              <input
                className="search-bar-input"
                type="text"
                placeholder="Filter by member"
              />
            </div>
          </StyledSearchFrom>

          {/* 유저 정렬 탭 */}
          <SortTeb />
        </div>
        <MemberBrowser></MemberBrowser>
      </div>
    </StyledMembers>
  );
};

export default Members;
