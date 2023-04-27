import styled from "styled-components";

export const StyledMemberInfo = styled.div`
  /* height: calc(100% - 600px); */
`;

/** 20230420 - 유저 이미지 - by sinyaenok*/
export const StyledImgBox = styled.div`
  margin: 16px;
  img {
    border-radius: 3px;
  }
`;

/** 20230420 - 유저 이름, 생성일 - by sinyaenok*/
export const StyledInfoBox = styled.div`
  #member-name {
    font-size: 30px;
    flex-grow: 1;
  }
  #member-info {
    flex-grow: 1;
  }
  #member-info > div {
    color: ${({ theme }) => theme.colors.gray500};
  }
  .icon-cack {
    padding: 0 5px 0 3px;
    color: ${({ theme }) => theme.colors.gray400};
  }
  .icon-clock {
    padding: 0 5px 0 5px;
    color: ${({ theme }) => theme.colors.gray400};
    margin-left: 5px;
  }
`;

/** 20230420 - 유저 정보 수정 버튼 - by sinyaenok*/
export const StyledEditBtn = styled.div`
  padding-right: 16px;
  justify-content: end;
  flex: 1;
  white-space: nowrap;
  .editBtn {
    border: 1px solid ${({ theme }) => theme.colors.gray400};
    color: ${({ theme }) => theme.colors.gray500};
    font-size: 14px;
    border-radius: 3px;
    padding: 10px 16px 10px;
    :hover {
      background-color: ${({ theme }) => theme.colors.gray200};
    }
  }
`;
