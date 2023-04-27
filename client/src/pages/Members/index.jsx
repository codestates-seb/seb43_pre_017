//style
import { StyledMembers, StyledSearchFrom } from "./style";

//icon
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";

//component
import SortTab from "./SortTab";
import MemberBrowser from "./MemberBrowser";

import { useState } from "react";

import dummy from "./Data/data.json";
/** 20230421 - 멤버 조회 페이지 - by sinyaenok */

const Members = () => {
  const data = dummy.members;
  const [searchQuery, setSearchQuery] = useState("");
  const filteredMembers = data.filter((member) =>
    member.name.toLowerCase().includes(searchQuery.toLowerCase()),
  );
  return (
    <StyledMembers>
      <div id="main-bar">
        {/* 멤버 타이틀 */}
        <h1>Members</h1>
        <div id="sub-bar" className="d-flex ai-center js-between">
          {/* 멤버 검색창 */}
          <StyledSearchFrom>
            <div className="search-bar d-flex">
              <FontAwesomeIcon
                icon={faMagnifyingGlass}
                className="search-icon"
              />
              <input
                className="search-bar-input"
                type="text"
                placeholder="Filter by member"
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
              />
            </div>
          </StyledSearchFrom>

          {/* 멤버 정렬 탭 */}
          <SortTab />
        </div>
        <MemberBrowser filteredMembers={filteredMembers} />
      </div>
    </StyledMembers>
  );
};

export default Members;
