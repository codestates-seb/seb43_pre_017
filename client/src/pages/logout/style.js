import styled from "styled-components";

/** 2023/04/23 로그아웃 페이지 전체 -by JHH0906 */
const StyledLogoutContainer = styled.div`
  width: 2000px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  cursor: pointer;
`;

/** 2023/04/23 로그아웃 페이지 로그아웃 폼 -by JHH0906 */
export const StyledLogoutForm = styled.div`
  width: 520px;
  height: 520px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  cursor: pointer;
`;

/** 2023/04/23 로그아웃 페이지 로그아웃 폼 상단 텍스트 -by JHH0906 */
export const StyledLogoutText = styled.div`
  width: 520px;
  height: 54px;
  display: flex;
  text-align: center;
  cursor: pointer;
  font-size: 24px;
  margin-bottom: 24px;
`;

/** 2023/04/23 로그아웃 페이지 로그아웃 컨텐츠폼 -by JHH0906 */
export const StyledLogoutContent = styled.div`
  width: 316px;
  height: 420px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  cursor: pointer;
`;

/** 2023/04/23 로그아웃 페이지 로그아웃박스 -by JHH0906 */
export const StyledLogoutBox = styled.div`
  border: 1px solid ${({ theme }) => theme.colors.gray300};
  border-radius: 5px;
  margin: 5px;
  width: 100%;
  padding: 20px;
`;

/** 2023/04/23 로그아웃 페이지 체크박스 폼 -by JHH0906 */
export const StyledCheckBoxForm = styled.div`
  width: 272px;
  display: flex;
`;

/** 2023/04/23 로그아웃 페이지 체크박스 폼안의 텍스트 -by JHH0906 */
export const StyledCheckBoxText = styled.div`
  width: 200px;
  font-size: 12px;
  position: fixed;
  margin-left: 24px;
  margin-top: 2px;
`;

/** 2023/04/23 로그아웃 페이지 버튼폼 -by JHH0906 */
export const StyledBtnForm = styled.button`
  width: 200px;
  display: flex;
  margin-left: 10px;
  margin-top: 12px;
`;

/** 2023/04/23 로그아웃 페이지 로그아웃 버튼 -by JHH0906 */
export const StyledLogOutBtn = styled.button`
  width: 68px;
  height: 37px;
  background: ${({ theme }) => theme.colors.sky500};
  color: white;
  border-radius: 4px;
  &:hover {
    background-color: ${({ theme }) => theme.colors.sky600};
  }
`;

/** 2023/04/23 로그아웃 페이지 취소 버튼 -by JHH0906 */
export const StyledLogCancelBtn = styled.button`
  width: 68px;
  height: 37px;
  border-radius: 4px;
  background: white;
  &:hover {
    background-color: ${({ theme }) => theme.colors.sky50};
  }
  color: ${({ theme }) => theme.colors.sky600};
`;

/** 2023/04/23 로그아웃 페이지 하단 텍스트 -by JHH0906 */
export const StyledBottomText = styled.div`
  width: 268px;
  height: 47px;
  font-size: 14px;
  margin-top: 28px;
  margin-left: 8px;
  color: ${({ theme }) => theme.colors.gray400};
`;
export default StyledLogoutContainer;
