import styled from "styled-components";

const StyledCheckbox = styled.div`
  width: 280px;
  margin-left: 8px;
`;

const StyledForm = styled.form`
  display: flex;
`;

const StyledInput = styled.input`
  margin: 0;
  width: 1em;
  height: 1em;
`;

const StyledTextArea = styled.div`
  margin-right: 4px;
  font-size: 12px;
`;

/** 2023/04/23 로그아웃 페이지 체크 -by JHH0906 */
const Checkbox = ({ text }) => {
  return (
    <StyledCheckbox>
      <StyledForm>
        <StyledInput type="checkbox" />
        <StyledTextArea>{text}</StyledTextArea>
      </StyledForm>
    </StyledCheckbox>
  );
};

export default Checkbox;
