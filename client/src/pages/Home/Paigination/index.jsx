import StyledNav, {
  StyledButton,
  StyledLimitBtnContainer,
  StyledMoveBtnContainer,
} from "./style";

function Pagination({ total, limit, page, setPage, setLimit, disable }) {
  const numPages = Math.ceil(total / limit); // limit: 한 페이지에 나타낼 개수
  const limitNums = [15, 30, 50];

  return (
    <StyledNav>
      <StyledMoveBtnContainer>
        <StyledButton onClick={() => setPage(page - 1)} disabled={page === 1}>
          Prev
        </StyledButton>
        {Array(numPages)
          .fill()
          .map((_, i) => (
            <StyledButton
              key={`${i.toString()}-${page}`}
              onClick={() => setPage(i + 1)}
              aria-current={page === i + 1 ? "page" : null}
            >
              {i + 1}
            </StyledButton>
          ))}
        <StyledButton
          onClick={() => setPage(page + 1)}
          disabled={page === numPages}
        >
          Next
        </StyledButton>
      </StyledMoveBtnContainer>
      <StyledLimitBtnContainer disabled={disable}>
        {!disable &&
          limitNums.map((num, i) => (
            <StyledButton
              key={`${i.toString()}-${num}`}
              onClick={({ target: { innerText } }) => {
                setLimit(Number(innerText));
              }}
              aria-current={limit === num ? "page" : null}
            >
              {num}
            </StyledButton>
          ))}
      </StyledLimitBtnContainer>
    </StyledNav>
  );
}

export default Pagination;
