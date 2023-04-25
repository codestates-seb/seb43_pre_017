import { StyledSortTab, StyledNewsetTab, StyledOldTab } from "./style";

/** 20230422 - 멤버 정렬 탭 - by sinyaenok */
const SortTab = () => {
  return (
    <StyledSortTab>
      <NewTab />
      <OldTab />
    </StyledSortTab>
  );
};

/** 20230422 - 신규 멤버 정렬 탭  - by sinyaenok*/
const NewTab = () => {
  return <StyledNewsetTab>New Members</StyledNewsetTab>;
};

/** 20230422 - 예전 멤버 정렬 탭  - by sinyaenok*/
const OldTab = () => {
  return <StyledOldTab>Old Members</StyledOldTab>;
};

export default SortTab;
