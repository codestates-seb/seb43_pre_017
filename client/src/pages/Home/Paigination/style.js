import styled from "styled-components";

/** 2023/04/26 - 페이지네이션 전체 컨테이너 - by JHH0906 */
const StyledNav = styled.nav`
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 4px;
  margin: 70px 16px 35px 16px;
`;

/** 2023/04/26 - 페이지네이션 페이지를 이동하는 버튼 컨테이너- by JHH0906 */
export const StyledMoveBtnContainer = styled.div`
  display: flex;
  margin: 20px 0;
`;

/** 2023/04/26 - 페이지네이션 한 페이지의 contents limit 버튼을 담는 컨테이너 - by JHH0906 */
export const StyledLimitBtnContainer = styled.div`
  display: flex;
  align-items: center;
  margin: 20px 0;
  div {
    margin-left: 10px;
  }
  &[disabled] {
    display: none;
  }
`;

/** 2023/04/26 - 페이지네이션 버튼 - by JHH0906 */
export const StyledButton = styled.button`
  padding: 0 8px;
  margin: 0 2px;
  border: 1px solid ${({ theme }) => theme.colors.slate300};
  border-radius: 3px;
  font-size: 13px;
  line-height: 1.9;
  background: white;
  &:hover {
    background: ${({ theme }) => theme.colors.slate300};
    cursor: pointer;
  }
  &[disabled] {
    display: none;
    background: grey;
    cursor: revert;
    transform: revert;
  }
  &[aria-current] {
    background: ${({ theme }) => theme.colors.main500};
    color: white;
    border: none;
    font-weight: bold;
    cursor: revert;
    transform: revert;
  }
`;

export default StyledNav;
