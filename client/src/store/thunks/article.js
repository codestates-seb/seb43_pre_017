import { createAsyncThunk } from "@reduxjs/toolkit";
import { AxiosError } from "axios";

// api
import { articleApiService } from "../apis";

/** 2023/04/19 - 특정 article 패치 요청 thunk - by 1-blue */
const fetchArticleThunk = createAsyncThunk(
  // 액션 타입 결정
  "fetch/article",

  // promise를 반환하는 액션 작성
  async (body, { rejectWithValue }) => {
    try {
      const { data } = await articleApiService.apiFetchArticle(body);

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

/** 2023/04/21 - 특정 article 제거 요청 thunk - by 1-blue */
const deleteArticleThunk = createAsyncThunk(
  // 액션 타입 결정
  "delete/article",

  // promise를 반환하는 액션 작성
  async (body, { rejectWithValue }) => {
    try {
      const { data } = await articleApiService.apiDeleteArticle(body);

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

/** 2023/04/19 - article thunk 메서드들을 갖는 객체 - by 1-blue */
export const articleThunkService = {
  fetchArticleThunk,
  deleteArticleThunk,
};
