import { Editor } from "@toast-ui/react-editor";
import colorSyntax from "@toast-ui/editor-plugin-color-syntax";
import codeSyntaxHighlight from "@toast-ui/editor-plugin-code-syntax-highlight";
import Prism from "prismjs";

// css
import "@toast-ui/editor/dist/toastui-editor.css";
import "@toast-ui/editor/dist/theme/toastui-editor-dark.css";
import "prismjs/themes/prism.css";

/** 2023/04/17 - 설정 - by 1-blue */
const toolbarItems = [
  ["heading", "bold", "italic", "strike"],
  ["hr", "ul", "ol", "task"],
  ["table", "link", "image"],
  ["code", "quote", "codeblock"],
  ["scrollSync"],
];

/** 2023/04/17 - WYSIWYG 에디터 - by 1-blue */
const Wysiwyg = ({ wysiwygRef, initialValue = " ", height = "50vh" }) => (
  <Editor
    ref={wysiwygRef}
    initialValue={initialValue} // 글 수정 시 사용
    initialEditType="markdown" // wysiwyg || markdown
    previewStyle="tab" // tab || vertical
    hideModeSwitch={false} // true || false
    height={height}
    usageStatistics={false}
    toolbarItems={toolbarItems}
    useCommandShortcut={true}
    plugins={[colorSyntax, [codeSyntaxHighlight, { highlighter: Prism }]]}
    placeholder="내용을 입력해주세요!"
  />
);

export default Wysiwyg;
