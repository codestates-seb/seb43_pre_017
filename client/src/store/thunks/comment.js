import { createAsyncThunk } from "@reduxjs/toolkit";
import { AxiosError } from "axios";

// api
// import { commentApiService } from "../apis";
import {
  createDummyCommentOfAnswer,
  createDummyCommentOfArticle,
  deleteDummyCommentOfAnswer,
  deleteDummyCommentOfArticle,
  updateDummyCommentOfAnswer,
  updateDummyCommentOfArticle,
} from "../../_dummy";

/** 2023/04/20 - 특정 article의 댓글 패치 요청 thunk - by 1-blue */
const createCommentOfArticleThunk = createAsyncThunk(
  // 액션 타입 결정
  "create/comment/article",

  // promise를 반환하는 액션 작성
  async (body, { rejectWithValue }) => {
    try {
      // FIXME:
      // const { data } = await commentApiService.apiCreateCommentOfArticle(body);
      const data = await createDummyCommentOfArticle();

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
/** 2023/04/20 - 특정 article의 댓글 수정 요청 thunk - by 1-blue */
const updateCommentOfArticleThunk = createAsyncThunk(
  // 액션 타입 결정
  "update/comment/article",

  // promise를 반환하는 액션 작성
  async (body, { rejectWithValue }) => {
    try {
      // const { data } = await commentApiService.apiUpdateCommentOfArticle(body);
      const data = await updateDummyCommentOfArticle();

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
/** 2023/04/20 - 특정 article의 댓글 삭제 요청 thunk - by 1-blue */
const deleteCommentOfArticleThunk = createAsyncThunk(
  // 액션 타입 결정
  "delete/comment/article",

  // promise를 반환하는 액션 작성
  async (body, { rejectWithValue }) => {
    try {
      // const { data } = await commentApiService.apiDeleteCommentOfArticle(body);
      const data = await deleteDummyCommentOfArticle();

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

/** 2023/04/20 - 특정 answer의 댓글 패치 요청 thunk - by 1-blue */
const createCommentOfAnswerThunk = createAsyncThunk(
  // 액션 타입 결정
  "create/comment/answer",

  // promise를 반환하는 액션 작성
  async (body, { rejectWithValue }) => {
    try {
      // const { data } = await commentApiService.apiCreateCommentOfAnswer(body);
      const data = await createDummyCommentOfAnswer();

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
/** 2023/04/20 - 특정 answer의 댓글 수정 요청 thunk - by 1-blue */
const updateCommentOfAnswerThunk = createAsyncThunk(
  // 액션 타입 결정
  "update/comment/answer",

  // promise를 반환하는 액션 작성
  async (body, { rejectWithValue }) => {
    try {
      // const { data } = await commentApiService.apiUpdateCommentOfAnswer(body);
      const data = await updateDummyCommentOfAnswer();

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
/** 2023/04/20 - 특정 answer의 댓글 삭제 요청 thunk - by 1-blue */
const deleteCommentOfAnswerThunk = createAsyncThunk(
  // 액션 타입 결정
  "delete/comment/answer",

  // promise를 반환하는 액션 작성
  async (body, { rejectWithValue }) => {
    try {
      // const { data } = await commentApiService.apiDeleteCommentOfAnswer(body);
      const data = await deleteDummyCommentOfAnswer();

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

/** 2023/04/20 - 댓글 thunk 메서드들을 갖는 객체 - by 1-blue */
export const commentThunkService = {
  createCommentOfArticleThunk,
  updateCommentOfArticleThunk,
  deleteCommentOfArticleThunk,
  createCommentOfAnswerThunk,
  updateCommentOfAnswerThunk,
  deleteCommentOfAnswerThunk,
};
