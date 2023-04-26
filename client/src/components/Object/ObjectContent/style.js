import styled from "styled-components";

/** 2023/04/19 - 컨텐츠 스타일 - by 1-blue */
const StyledObjectContent = styled.section`
  h1 {
    font-size: 1.6rem;
    margin-bottom: 0.6em;
  }
  h2 {
    font-size: 1.5rem;
    margin-bottom: 0.5em;
  }
  h3 {
    font-size: 1.4rem;
    margin-bottom: 0.4em;
  }
  h4 {
    font-size: 1.3rem;
    margin-bottom: 0.3em;
  }
  h5 {
    font-size: 1.2rem;
    margin-bottom: 0.2em;
  }
  h6 {
    font-size: 1.1rem;
    margin-bottom: 0.1em;
  }
  p {
    font-size: 1rem;
    white-space: pre-wrap;
    line-height: 1.2;
  }
  em {
    font-style: italic;
  }
  strong {
    font-weight: bold;
  }
  ul {
    padding: 0.6em 1.2em;
    list-style: disc;
  }
  ol {
    padding: 0.6em 1.1em;
    list-style: decimal;
  }
  a {
    color: ${({ theme }) => theme.colors.blue600};
    text-decoration: underline;
    text-underline-offset: 2px;
  }
  blockquote {
    padding: 1em 1.2em;
    margin: 0 1em 1em;

    border-left: 4px solid ${({ theme }) => theme.colors.gray400};
  }
  pre {
    code {
      padding: 0;
      background-color: transparent;
    }
  }
  code {
    padding: 0.2em 0.4em;

    font-size: 0.9rem;
    font-weight: bold;
    border-radius: 0.2em;
    background-color: ${({ theme }) => theme.colors.gray300};
  }
  table {
    min-width: 40%;
    max-width: 100%;
    border: 1px solid ${({ theme }) => theme.colors.gray400};
    border-collapse: collapse;
    font-size: 0.875rem;
    margin: 0.6em 0;

    * > th + th,
    * > td + td {
      border-left: 1px solid ${({ theme }) => theme.colors.gray400};
    }

    th {
      word-break: break-word;
      padding: 0.5rem;

      border-bottom: 4px solid ${({ theme }) => theme.colors.gray400};
    }

    td {
      word-break: break-word;
      padding: 0.5rem;
    }
  }
`;

export default StyledObjectContent;
