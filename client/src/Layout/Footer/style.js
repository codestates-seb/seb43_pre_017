import styled from "styled-components";

/** 2023/04/12 - 푸터를 감싸는 컴포넌트의 스타일 - by 1-blue */
const StyledFoorter = styled.footer`
  position: relative;
  z-index: 1;

  width: 100%;
  background-color: ${({ theme }) => theme.colors.zinc800};
`;
export const StyledFoorterWrapper = styled.div`
  max-width: 1280px;
  margin: 0 auto;
  height: 320px;

  display: flex;
  @media (max-width: 980px) {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    margin-top: 0;
    padding-left: 16px;
  }
  @media (max-width: 610px) {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    margin-top: 0;
    padding-left: 16px;
  }
`;
/** 2023/04/16 - 푸터 스택오버플로우로고 스타일 - by JHH0906 */
export const StyledLogo = styled.div`
  width: 10%;
  height: 100%;

  > img {
    width: 60px;
    float: right;
    margin-right: 8px;
  }
  @media (max-width: 980px) {
    > img {
      float: left;
    }
  }
  @media (max-width: 610px) {
    > img {
      display: none;
    }
  }
`;
/** 2023/04/16 - 푸터 스택오버플로우카테고리 스타일 - by JHH0906 */
export const StyledStackoverflow = styled.div`
  width: 15%;
  display: flex;
  flex-direction: column;
  margin-top: 32px;
  @media (max-width: 980px) {
    width: 100%;
    margin-top: 20px;
  }
`;
/** 2023/04/16 - 푸터 스택오버플로우카테고리Title 스타일 - by JHH0906 */
export const StyledTitle = styled.div`
  color: ${({ theme }) => theme.colors.stone300};
  font-size: 14px;
  font-weight: 700;
  @media (max-width: 980px) {
    width: 100%;
    padding-left: 8px;
  }
`;
/** 2023/04/16 - 푸터 스택오버플로우카테고리Ul 스타일 - by JHH0906 */
export const StyledUl = styled.ul`
  margin-top: 12px;
  color: ${({ theme }) => theme.colors.gray400};
  font-size: 14px;
  font-weight: 500;
  @media (max-width: 980px) {
    display: flex;
    margin-top: 0;
  }
  @media (max-width: 720px) {
    display: flex;
    flex-wrap: wrap;
  }
`;
/** 2023/04/16 - 푸터 스택오버플로우카테고리Li 스타일 - by JHH0906 */
export const StyledLi = styled.li`
  padding-top: 12px;
  @media (max-width: 980px) {
    float: left;
    margin-top: 0;
    padding-left: 8px;
  }
`;
/** 2023/04/16 - 푸터 프로덕트카테고리 스타일 - by JHH0906 */
export const StyledProducts = styled.div`
  width: 10%;
  display: flex;
  flex-direction: column;
  margin-top: 32px;
  @media (max-width: 980px) {
    width: 100%;
  }
`;
/** 2023/04/16 - 푸터 컴퍼니카테고리 스타일 - by JHH0906 */
export const StyledCompnay = styled.div`
  width: 15%;
  display: flex;
  flex-direction: column;
  margin-top: 32px;
  @media (max-width: 980px) {
    width: 100%;
  }
`;
/** 2023/04/16 - 푸터 네트워크카테고리 스타일 - by JHH0906 */
export const StyledNetwork = styled.div`
  width: 20%;
  display: flex;
  flex-direction: column;
  margin-top: 32px;

  @media (max-width: 980px) {
    width: 100%;
  }
`;
/** 2023/04/16 - 푸터 기타카테고리컨테이너 스타일 - by JHH0906 */
export const StyledEtcContainer = styled.div`
  width: 30%;
  display: flex;
  flex-direction: column;
  margin-top: 32px;
  @media (max-width: 980px) {
    width: 100%;
  }
`;
/** 2023/04/16 - 푸터 SNS카테고리 스타일 - by JHH0906 */
export const StyledSnsContainer = styled.div`
  width: 100%;
  display: flex;
`;
/** 2023/04/16 - 푸터 SNS 스타일 - by JHH0906 */
export const StyledSns = styled.div`
  font-size: 12px;
  color: ${({ theme }) => theme.colors.gray400};
  display: flex;
  margin-left: 8px;
`;
/** 2023/04/16 - 푸터 라이센스카테고리 스타일 - by JHH0906 */
export const StyledLicenseContainer = styled.div`
  height: 30%;
  margin-left: 8px;
  padding-top: 200px;
  @media (max-width: 980px) {
    padding-top: 8px;
    padding-bottom: 20px;
    display: flex;
  }
  @media (max-width: 600px) {
    display: inline;
  }
`;
/** 2023/04/16 - 푸터 라이센스 스타일 - by JHH0906 */
export const StyledLicense = styled.div`
  font-size: 12px;
  color: ${({ theme }) => theme.colors.gray400};
  @media (max-width: 980px) {
    padding-top: 8px;
    display: flex;
  }
  @media (max-width: 600px) {
    display: inline;
  }
`;
export default StyledFoorter;
