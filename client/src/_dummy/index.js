export const fetchDummyArticle = async () =>
  new Promise((resolve) =>
    setTimeout(() => {
      resolve({
        message: "질문글 조회를 완료했습니다.",
        article: {
          id: 1,
          title:
            "reactjs: get the sub-categories of each category by react query",
          content: `
<h1>제목</h1><p><strong>굵은 글씨</strong></p><p><br></p><p><em>이텔릭</em></p><p><br></p><p><del>가운데 라인</del></p><ul><li><p>순서</p></li><li><p>없는</p></li><li><p>리스트</p></li></ul><div contenteditable="false"><hr></div><ol><li><p>간지나게 숨쉬기</p></li><li><p>작살나게 밥 먹기</p></li><li><p>대충 잠자기</p></li></ol><ul><li class="task-list-item" data-task="true"><p>오늘</p></li><li class="task-list-item" data-task="true"><p>할 일</p></li></ul><table><thead><tr><th><p>제목</p></th><th><p>내용</p></th></tr></thead><tbody><tr><td><p>사과</p></td><td><p>apple</p></td></tr></tbody></table><p><a href="https://1-blue.github.io/">1-blue 블로그</a></p><p><br></p><p><code data-backticks="1">코드 블록</code></p><div data-language="js" class="toastui-editor-ww-code-block-highlighting"><pre class="language-js"><code data-language="js" class="language-js"><span class="token keyword">import</span> <span class="token punctuation">{</span> configureStore <span class="token punctuation">}</span> <span class="token keyword">from</span> <span class="token string">"@reduxjs/toolkit"</span><span class="token punctuation">;</span>
      
<span class="token comment">// root reduecer</span>
<span class="token keyword">import</span> rootReducer <span class="token keyword">from</span> <span class="token string">"./reducers"</span><span class="token punctuation">;</span>

<span class="token comment">/** 2023/04/15 - store 생성 함수 - by 1-blue */</span>
<span class="token keyword">const</span> <span class="token function-variable function">createStore</span> <span class="token operator">=</span> <span class="token punctuation">(</span><span class="token punctuation">)</span> <span class="token operator">=&gt;</span> <span class="token punctuation">{</span>
  <span class="token keyword">const</span> store <span class="token operator">=</span> <span class="token function">configureStore</span><span class="token punctuation">(</span><span class="token punctuation">{</span>
    <span class="token literal-property property">reducer</span><span class="token operator">:</span> rootReducer<span class="token punctuation">,</span>
    <span class="token function-variable function">middleware</span><span class="token operator">:</span> <span class="token punctuation">(</span><span class="token parameter">getDefaultMiddleware</span><span class="token punctuation">)</span> <span class="token operator">=&gt;</span> <span class="token function">getDefaultMiddleware</span><span class="token punctuation">(</span><span class="token punctuation">)</span><span class="token punctuation">.</span><span class="token function">concat</span><span class="token punctuation">(</span><span class="token punctuation">[</span><span class="token punctuation">]</span><span class="token punctuation">)</span><span class="token punctuation">,</span>
    <span class="token literal-property property">devTools</span><span class="token operator">:</span> process<span class="token punctuation">.</span>env<span class="token punctuation">.</span><span class="token constant">NODE_ENV</span> <span class="token operator">===</span> <span class="token string">"development"</span><span class="token punctuation">,</span>
  <span class="token punctuation">}</span><span class="token punctuation">)</span><span class="token punctuation">;</span>

  <span class="token keyword">return</span> store<span class="token punctuation">;</span>
<span class="token punctuation">}</span><span class="token punctuation">;</span>

<span class="token comment">/** 2023/04/15 - redux의 store - by 1-blue */</span>
<span class="token keyword">const</span> store <span class="token operator">=</span> <span class="token function">createStore</span><span class="token punctuation">(</span><span class="token punctuation">)</span><span class="token punctuation">;</span>

<span class="token keyword">export</span> <span class="token keyword">default</span> store<span class="token punctuation">;</span></code></pre></div>`,
          evaluationScore: 54321,
          createdAt: "2023-04-19T21:00:00",
          updatedAt: "2023-04-19T21:00:00",
        },
        member: {
          id: 1,
          name: "유저",
        },
        count: {
          answer: 2,
          comment: 4,
        },
      });
    }, 500),
  );

export const deleteDummyArticle = async ({ articleId }) =>
  new Promise((resolve) =>
    setTimeout(() => {
      resolve({
        articleId,
        message: "질문을 삭제했습니다.",
      });
    }, 500),
  );

export const fetchDummyAnswersOfArticle = async () =>
  new Promise((resolve) =>
    setTimeout(() => {
      resolve({
        message: "답변글 조회를 완료했습니다.",
        articleId: 1,
        answers: [
          {
            id: 1,
            content: `
            <div data-language="javascript" class="toastui-editor-ww-code-block-highlighting"><pre class="language-javascript"><code data-language="javascript" class="language-javascript"><span class="token keyword">export</span> <span class="token keyword">const</span> <span class="token function-variable function">fetchDummyAnswersOfArticle</span> <span class="token operator">=</span> <span class="token keyword">async</span> <span class="token punctuation">(</span><span class="token punctuation">)</span> <span class="token operator">=&gt;</span>
 <span class="token keyword">new</span> <span class="token class-name">Promise</span><span class="token punctuation">(</span><span class="token punctuation">(</span><span class="token parameter">resolve</span><span class="token punctuation">)</span> <span class="token operator">=&gt;</span>
   <span class="token function">setTimeout</span><span class="token punctuation">(</span><span class="token punctuation">(</span><span class="token punctuation">)</span> <span class="token operator">=&gt;</span> <span class="token punctuation">{</span>
     <span class="token function">resolve</span><span class="token punctuation">(</span><span class="token punctuation">{</span>
       <span class="token literal-property property">message</span><span class="token operator">:</span> <span class="token string">"답변글 조회를 완료했습니다."</span><span class="token punctuation">,</span>
       <span class="token literal-property property">articleId</span><span class="token operator">:</span> <span class="token number">1</span><span class="token punctuation">,</span>
       <span class="token literal-property property">answers</span><span class="token operator">:</span> <span class="token punctuation">[</span>
         <span class="token punctuation">{</span>
           <span class="token literal-property property">id</span><span class="token operator">:</span> <span class="token number">1</span><span class="token punctuation">,</span>
           <span class="token literal-property property">content</span><span class="token operator">:</span> <span class="token string">"답변글 내용 - 강아지"</span><span class="token punctuation">,</span>
           <span class="token literal-property property">user</span><span class="token operator">:</span> <span class="token punctuation">{</span>
             <span class="token literal-property property">id</span><span class="token operator">:</span> <span class="token number">1</span><span class="token punctuation">,</span>
             <span class="token literal-property property">name</span><span class="token operator">:</span> <span class="token string">"강아지"</span><span class="token punctuation">,</span>
           <span class="token punctuation">}</span><span class="token punctuation">,</span>
           <span class="token literal-property property">count</span><span class="token operator">:</span> <span class="token punctuation">{</span>
             <span class="token literal-property property">comments</span><span class="token operator">:</span> <span class="token number">5</span><span class="token punctuation">,</span>
           <span class="token punctuation">}</span><span class="token punctuation">,</span>
           <span class="token literal-property property">evaluationScore</span><span class="token operator">:</span> <span class="token number">111</span><span class="token punctuation">,</span>
           <span class="token literal-property property">createdAt</span><span class="token operator">:</span> <span class="token string">"2023-04-19T21:00:00"</span><span class="token punctuation">,</span>
           <span class="token literal-property property">updatedAt</span><span class="token operator">:</span> <span class="token string">"2023-04-19T21:00:00"</span><span class="token punctuation">,</span>
         <span class="token punctuation">}</span><span class="token punctuation">,</span>
         <span class="token punctuation">{</span>
           <span class="token literal-property property">id</span><span class="token operator">:</span> <span class="token number">2</span><span class="token punctuation">,</span>
           <span class="token literal-property property">content</span><span class="token operator">:</span> <span class="token string">"답변글 내용 - 고양이"</span><span class="token punctuation">,</span>
           <span class="token literal-property property">user</span><span class="token operator">:</span> <span class="token punctuation">{</span>
             <span class="token literal-property property">id</span><span class="token operator">:</span> <span class="token number">2</span><span class="token punctuation">,</span>
             <span class="token literal-property property">name</span><span class="token operator">:</span> <span class="token string">"고양이"</span><span class="token punctuation">,</span>
           <span class="token punctuation">}</span><span class="token punctuation">,</span>
           <span class="token literal-property property">count</span><span class="token operator">:</span> <span class="token punctuation">{</span>
             <span class="token literal-property property">comments</span><span class="token operator">:</span> <span class="token number">999</span><span class="token punctuation">,</span>
           <span class="token punctuation">}</span><span class="token punctuation">,</span>
           <span class="token literal-property property">evaluationScore</span><span class="token operator">:</span> <span class="token number">222</span><span class="token punctuation">,</span>
           <span class="token literal-property property">createdAt</span><span class="token operator">:</span> <span class="token string">"2023-04-19T21:00:00"</span><span class="token punctuation">,</span>
           <span class="token literal-property property">updatedAt</span><span class="token operator">:</span> <span class="token string">"2023-04-19T21:00:00"</span><span class="token punctuation">,</span>
         <span class="token punctuation">}</span><span class="token punctuation">,</span>
       <span class="token punctuation">]</span><span class="token punctuation">,</span>
     <span class="token punctuation">}</span><span class="token punctuation">)</span><span class="token punctuation">;</span>
   <span class="token punctuation">}</span><span class="token punctuation">,</span> <span class="token number">500</span><span class="token punctuation">)</span><span class="token punctuation">,</span>
 <span class="token punctuation">)</span><span class="token punctuation">;</span></code></pre></div>
            `,
            member: {
              id: 1,
              name: "강아지",
            },
            count: {
              comment: 5,
            },
            evaluationScore: 111,
            createdAt: "2023-04-19T21:00:00",
            updatedAt: "2023-04-19T21:00:00",
          },
          {
            id: 2,
            content: "답변글 내용 - 고양이",
            member: {
              id: 2,
              name: "고양이",
            },
            count: {
              comment: 999,
            },
            evaluationScore: 222,
            createdAt: "2023-04-19T21:00:00",
            updatedAt: "2023-04-19T21:00:00",
          },
        ],
      });
    }, 500),
  );

export const fetchDummyCommentOfArticle = async () =>
  new Promise((resolve) =>
    setTimeout(() => {
      resolve({
        message: "댓글들 조회를 완료했습니다.",
        articleId: 1,
        comments: [
          {
            id: 1,
            content: "댓글 내용 - 1",
            member: {
              id: 1,
              name: "유저1",
            },
            createdAt: "2023-04-19T21:00:00",
            updatedAt: "2023-04-19T21:00:00",
          },
          {
            id: 2,
            content: "댓글 내용 - 2",
            member: {
              id: 2,
              name: "유저2",
            },
            createdAt: "2023-04-19T21:00:00",
            updatedAt: "2023-04-19T21:00:00",
          },
        ],
      });
    }, 500),
  );

export const fetchDummyCommentOfAnswer = async () =>
  new Promise((resolve) =>
    setTimeout(() => {
      resolve({
        message: "댓글들 조회를 완료했습니다.",
        answerId: 1,
        comments: [
          {
            id: 1,
            content: "댓글 내용1",
            member: {
              id: 1,
              name: "유저1",
            },
            createdAt: "2023-04-19T21:00:00",
            updatedAt: "2023-04-19T21:00:00",
          },
          {
            id: 2,
            content: "댓글 내용2",
            member: {
              id: 2,
              name: "유저2",
            },
            createdAt: "2023-04-19T21:00:00",
            updatedAt: "2023-04-19T21:00:00",
          },
        ],
      });
    }, 500),
  );

export const createDummyAnswerOfArticle = async () =>
  new Promise((resolve) =>
    setTimeout(() => {
      resolve({
        articleId: Date.now(),
        answerId: Date.now(),
        message: "답변을 등록했습니다.",
      });
    }, 500),
  );

export const deleteDummyAnswerOfArticle = async () =>
  new Promise((resolve) =>
    setTimeout(() => {
      resolve({
        articleId: 1,
        answerId: 1,
        message: "답변을 삭제했습니다.",
      });
    }, 500),
  );

export const createDummyCommentOfArticle = async () =>
  new Promise((resolve) =>
    setTimeout(() => {
      resolve({
        articleId: Date.now(),
        commentId: Date.now(),
        message: "댓글을 등록했습니다.",
      });
    }, 500),
  );

export const updateDummyCommentOfArticle = async () =>
  new Promise((resolve) =>
    setTimeout(() => {
      resolve({
        articleId: 1,
        commentId: 1,
        message: "댓글을 수정했습니다.",
      });
    }, 500),
  );

export const deleteDummyCommentOfArticle = async () =>
  new Promise((resolve) =>
    setTimeout(() => {
      resolve({
        message: "댓글을 삭제했습니다.",
      });
    }, 500),
  );

export const createDummyCommentOfAnswer = async () =>
  new Promise((resolve) =>
    setTimeout(() => {
      resolve({
        answerId: Date.now(),
        commentId: Date.now(),
        message: "댓글을 등록했습니다.",
      });
    }, 500),
  );

export const updateDummyCommentOfAnswer = async () =>
  new Promise((resolve) =>
    setTimeout(() => {
      resolve({
        message: "댓글을 수정했습니다.",
      });
    }, 500),
  );

export const deleteDummyCommentOfAnswer = async () =>
  new Promise((resolve) =>
    setTimeout(() => {
      resolve({
        message: "댓글을 삭제했습니다.",
      });
    }, 500),
  );

export const uploadDummyEvalutionOfArticle = async () =>
  new Promise((resolve) =>
    setTimeout(() => {
      resolve({
        articleId: 1,
        evaluationId: 1,
        message: "평가를 등록했습니다.",
      });
    }, 500),
  );

export const uploadDummyEvalutionOfAnswer = async () =>
  new Promise((resolve) =>
    setTimeout(() => {
      resolve({
        evaluationId: 1,
        message: "평가를 등록했습니다.",
        answerId: 1,
      });
    }, 500),
  );
