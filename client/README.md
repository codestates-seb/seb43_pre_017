# 🐉 코드스테이츠 Pre-Project client

> 나중에 수정

## 💻 제작환경
1. `Window 11` & `Mac`
1. `Git` & `GitHub`
1. `Node` & `npm`
1. `VSCode`

## ⚒️ 기술 스택
1. `eslint`, `prettier`
1. `React.js`
1. `styled-components`
1. `redux-toolkit`
1. `axios`

## 🫗 폴더 구조

+ `public`: 정적 파일
+ `eslintrc.json`: 코드 규칙 명시
+ `prettierrc.json`: 코드 작성 규칙 명시
+ `package.json`: 핵심 파일
+ `src`: 소스 코드
  + `components`: 컴포넌트들
  + `hooks`: 사용자 정의 훅들
  + `Layout`: 전체 레이아웃 ( `Header`, `Main`, `Footer`, `NavBar`, `SideBar` )
  + `pages`: 페이지 컴포넌트들
  + `store`: `redux-toolkit`을 정의를 위한 폴더
  + `style`: `styled-components` 세팅 및 `css`를 위한 폴더
  + `utils`: 코드의 흐름에 영향을 받지 않고 독립적으로 존재할 수 있는 헬퍼 함수들

```
├─client
│  ├─public
│  ├─eslintrc.json
│  ├─prettierrc.json
│  ├─package.json
│  └─src
│      ├─components
│      ├─hooks
│      └─Layout
│          ├─Footer
│          ├─Header
│          ├─Main
│          ├─SideBar
│          └─NavBar
│      ├─pages
│      └─store
│          ├─apis
│          ├─reducers
│          └─thunks
│      ├─style
│      └─utils
```

## 🕹️ 실행 & 빌드

+ 실행

```bash
# 폴더 진입
cd client

# 패키지 설치 ( "node" 필요 )
npm install

# 개발용 실행
npm start
```

+ 빌드

```bash
# 폴더 진입
cd client

# 패키지 설치 ( "node" 필요 )
npm install

# 개발용 실행
npm run build

# "build"폴더가 생성되고 빌드된 파일이 들어가 있음
```

## 📜 코드 작성 규칙

### 0️⃣ JSDoc
특정 함수나 변수에 `JSDoc` 작성

```jsx
/** 2023/04/13 - 로그인한 유저 데이터 - by 1-blue */
const [user, setUser] = useState(null);

/** 2023/04/13 - 로그인 핸들러 - by 1-blue */
const onLogIn = () => {
  // ... 대충 로그인 처리
}
```

### 1️⃣ styled-components

+ 규칙
  1. 컴포넌트(`index.jsx`)와 스타일(`style.js`) 파일 분리
  1. `styled-components`에 접두사로 `Styled` 사용

```js
// "/src/components/Input/style.js"
import styled from "styled-components";

/** 2023/04/13 - input 컴포넌트 스타일 - by 1-blue */
const StyledInput = styled.input``;

export default StyledInput;

// "/src/components/Input/index.jsx"
import StyledInput from "./style.js";

/** 2023/04/13 - input 컴포넌트 - by 1-blue */
const Input = () => {
  return (
    <StyledInput />
  )
}
```


12. 폴더구조
13. 레이아웃


14. 리덕스 세팅 완료
15. 칸반 생성 및 규칙

### 2️⃣ branch
> 브랜치는 항상 `PR`을 통해 병합하고, 각 팀의 모든 인원이 허용해야합니다.<br />

+ `main`: 배포 브랜치
+ `dev`: `fe`/`be` 작업 테스트 병합 브랜치
+ `fe`/`be`: 개발 브랜치
+ `fe-feat/기능명` `|` `fe-feat/페이지명`: 상세 개발 브랜치


TODO: Question -> Article