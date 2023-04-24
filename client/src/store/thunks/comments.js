import { createAsyncThunk } from "@reduxjs/toolkit";
import { AxiosError } from "axios";

// api
// import { commentsApiService } from "../apis";
import {
  fetchDummyCommentOfAnswer,
  fetchDummyCommentOfArticle,
} from "../../_dummy";

/** 2023/04/20 - 특정 article의 댓글들 패치 요청 thunk - by 1-blue */
const fetchCommentsOfArticleThunk = createAsyncThunk(
  // 액션 타입 결정
  "fetch/comments/article",

  // promise를 반환하는 액션 작성
  async (body, { rejectWithValue }) => {
    try {
      // FIXME: 가짜 데이터
      // const { data } = await commentsApiService.apiFetchCommentsOfArticle(body);
      const data = await fetchDummyCommentOfArticle();

      return data;
    } catch (error) {
      console.error("error >> ", error);

      if (error instanceof AxiosError) {
        return rejectWithValue({ message: error.response?.data.data.message });
      }

      return rejectWithValue({
        message: "알 수 없는 이유로 실패했습니다.",
      });
    }
  },
);

/** 2023/04/20 - 특정 answer의 댓글들 패치 요청 thunk - by 1-blue */
const fetchCommentsOfAnswerThunk = createAsyncThunk(
  // 액션 타입 결정
  "fetch/comments/answer",

  // promise를 반환하는 액션 작성
  async (body, { rejectWithValue }) => {
    try {
      // FIXME: 가짜 데이터
      // const { data } = await commentsApiService.apiFetchCommentsOfAnswer(body);
      const data = await fetchDummyCommentOfAnswer();

      return data;
    } catch (error) {
      console.error("error >> ", error);

      if (error instanceof AxiosError) {
        return rejectWithValue({ message: error.response?.data.data.message });
      }

      return rejectWithValue({
        message: "알 수 없는 이유로 실패했습니다.",
      });
    }
  },
);

/** 2023/04/20 - 댓글들 thunk 메서드들을 갖는 객체 - by 1-blue */
export const commentsThunkService = {
  fetchCommentsOfArticleThunk,
  fetchCommentsOfAnswerThunk,
};
