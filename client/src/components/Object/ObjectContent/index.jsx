// style
import StyledObjectContent from "./style";

/** 2023/04/19 -  컨텐츠 - by 1-blue */
const ObjectContent = ({ content }) => (
  <StyledObjectContent dangerouslySetInnerHTML={{ __html: content }} />
);

export default ObjectContent;
